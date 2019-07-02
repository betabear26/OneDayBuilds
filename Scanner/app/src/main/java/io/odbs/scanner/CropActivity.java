package io.odbs.scanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class CropActivity extends AppCompatActivity {

    private final String TAG = "CropActivity";

    private FrameLayout sourceFrame;
    private ImageView sourceImage;
    private PolygonLayout polygonLayout;
    private String filePath;
    private Bitmap original;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        sourceFrame = findViewById(R.id.sourceFrame);
        sourceImage = findViewById(R.id.sourceImageView);
        polygonLayout = findViewById(R.id.polygonView);
        readData();
        init();
    }

    private void readData() {
        Intent intent = getIntent();
        if (intent.hasExtra(Utils.FILE_PATH)) {
            filePath = intent.getStringExtra(Utils.FILE_PATH);
            Log.d(TAG, filePath);
        }
    }

    private void init(){
        sourceFrame.post(new Runnable() {
            @Override
            public void run() {
                original = getBitmap();
                if(original != null){
                    setBitmap(original);
                }
            }
        });
    }

    private Bitmap getBitmap(){
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, bmOptions);
        try {
            ExifInterface exif = new ExifInterface(filePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            }
            else if (orientation == 3) {
                matrix.postRotate(180);
            }
            else if (orientation == 8) {
                matrix.postRotate(270);
            }
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return bitmap;
    }

    private void setBitmap(Bitmap original){
        Bitmap scaledBitmap = scaledBitmap(original, sourceFrame.getWidth(), sourceFrame.getHeight());
        sourceImage.setImageBitmap(scaledBitmap);
        Bitmap tempBitmap = ((BitmapDrawable) sourceImage.getDrawable()).getBitmap();
        polygonLayout.setVisibility(View.VISIBLE);
        polygonLayout.setPoints(getEdgePoint(tempBitmap));
        int padding = (int) getResources().getDimension(R.dimen.scanPadding);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(tempBitmap.getWidth() + 2 * padding, tempBitmap.getHeight() + 2 * padding);
        layoutParams.gravity = Gravity.CENTER;
        polygonLayout.setLayoutParams(layoutParams);
    }

    private Bitmap scaledBitmap(Bitmap bitmap, int width, int height) {
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(0, 0, width, height), Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    }

    private Map<Integer, PointF> getEdgePoint(Bitmap tempBitmap) {
        Map<Integer, PointF> outlinePoints = new HashMap<>();
        outlinePoints.put(0, new PointF(0, 0));
        outlinePoints.put(1, new PointF(tempBitmap.getWidth(), 0));
        outlinePoints.put(2, new PointF(0, tempBitmap.getHeight()));
        outlinePoints.put(3, new PointF(tempBitmap.getWidth(), tempBitmap.getHeight()));
        return outlinePoints;
    }
}
