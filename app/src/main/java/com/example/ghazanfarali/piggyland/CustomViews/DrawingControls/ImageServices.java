package com.example.ghazanfarali.piggyland.CustomViews.DrawingControls;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageServices {
    private static String getTempDirectoryPath(Context ctx) {
        File cache;

        // SD Card Mounted
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            cache = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/Android/data/"
                    + ctx.getPackageName() + "/cache/");
        }
        // Use internal storage
        else {
            cache = ctx.getCacheDir();
        }

        // Create the cache directory if it doesn't exist
        if (!cache.exists()) {
            cache.mkdirs();
        }

        return cache.getAbsolutePath();
    }

    public static Uri getOutputImageFileUri(Context ctx) {
        // TODO: check the presence of SDCard

        String tstamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH)
                .format(new Date());
        File file = new File(getTempDirectoryPath(ctx), "IMG_" + tstamp
                + ".jpg");

        return Uri.fromFile(file);

    }
}
