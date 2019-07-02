package io.odbs.scanner;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class Utils {

    private Utils(){
        //private constructor to hide the implicit one
    }

    static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
    static final String FILE_PATH = "filepath";

    static File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Scanner");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Scanner", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
}
