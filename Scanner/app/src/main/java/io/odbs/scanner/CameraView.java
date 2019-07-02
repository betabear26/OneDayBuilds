package io.odbs.scanner;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CameraView";
    Camera camera;
    SurfaceHolder holder;


    public CameraView(final Context context) {
        super(context);
        camera = Camera.open();
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Camera.Parameters params = camera.getParameters();

        if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }

        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //Empty method
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
    }

    public void captureImage(Camera.PictureCallback mPictureCallBack){
        camera.takePicture(null, null, mPictureCallBack);
    }
}
