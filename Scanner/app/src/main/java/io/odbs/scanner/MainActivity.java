package io.odbs.scanner;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static io.odbs.scanner.Utils.getOutputMediaFile;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private Button mCaptureButton;
    private FrameLayout mFrameLayout;
    private AlertDialog mAlertDialog;
    private boolean showAlert = false;
    Camera.PictureCallback mPictureCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCaptureButton = findViewById(R.id.capture);
        mFrameLayout = findViewById(R.id.fragment_holder);
        checkPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isCameraPermissionGranted() && isStoragePermissionGranted()){
            startCameraPreview();
        } else if(showAlert){
            showExplanationAlert();
        }
    }

    private void startCameraPreview(){
        final CameraView camera = new CameraView(this);
        mFrameLayout.addView(camera);
        setPictureCallBack();
        mCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.captureImage(mPictureCallBack);
            }
        });
    }

    private void setPictureCallBack(){
        mPictureCallBack = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                if (pictureFile == null){
                    Log.d(TAG, "Error creating media file, check storage permissions");
                    return;
                }

                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                    Log.d(TAG, "File Write Successful "+pictureFile.getAbsolutePath());
                    sendToNextActivity(pictureFile.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    Log.d(TAG, "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d(TAG, "Error accessing file: " + e.getMessage());
                }
            }
        };
    }

    private void sendToNextActivity(String path){
        Intent intent = new Intent(this, CropActivity.class);
        intent.putExtra(Utils.FILE_PATH, path);
        startActivity(intent);
    }

    //----------------Permissions--------------------------//
    private void checkPermission(){
        showAlert = false;
        if (!isCameraPermissionGranted() || !isStoragePermissionGranted()) {
            if (shouldShowRationale()) {
                showAlert = true;
                showExplanationAlert();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        Utils.CAMERA_PERMISSION_REQUEST_CODE);
            }
        } else {
            startCameraPreview();
        }
    }


    private void showExplanationAlert(){
        if(mAlertDialog != null && mAlertDialog.isShowing()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.alert_msg))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        if (shouldShowRationale()) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    Utils.CAMERA_PERMISSION_REQUEST_CODE);
                        } else {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                })
                .setTitle(getString(R.string.permission_reqd_title));
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    private boolean isCameraPermissionGranted(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isStoragePermissionGranted(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldShowRationale(){
        return ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Utils.CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)){
                startCameraPreview();
            } else {
                showExplanationAlert();
            }
        }
    }
}
