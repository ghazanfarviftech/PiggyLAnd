package com.example.ghazanfarali.piggyland.Helper;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Amir.jehangir on 1/2/2017.
 */
public class Fonts {
    public enum FontTypes {
        bold,
        light,
        medium,
        opensans,
        opensanslight
    }

    public static Typeface getFont(Context context, int resIdFontName) {
        return Typeface.createFromAsset(context.getAssets(), getFontPath(context, resIdFontName));
    }

    public static String getFontPath(Context context, int resIdFontName) {
        return String.format("fonts/%s", context.getString(resIdFontName));
    }

    public static Typeface getBoldFont(Context context) {
        return AppFont.getInstance(context).get_boldTypeFace();
    }

    public static Typeface getLightFont(Context context) {
        return AppFont.getInstance(context).get_lightTypeFace();
    }

    public static Typeface getMediumFont(Context context) {
        return AppFont.getInstance(context).get_mediumTypeFace();
    }

    public static Typeface getOpenSansSemiBold(Context context) {
        return AppFont.getInstance(context).get_open_sans_TypeFace();
    }
    public static Typeface getOpenSansLight(Context context) {
        return AppFont.getInstance(context).get_open_sans_TypeFace();
    }


    public static Typeface getFont(Context context, FontTypes fontType) {

        switch (fontType) {
            case bold:
                return getBoldFont(context);

            case light:
                return getLightFont(context);
            case medium:
                return getMediumFont(context);
            case opensans:
                return getOpenSansSemiBold(context);
            case opensanslight:
                return getOpenSansLight(context);

            default:
                return getLightFont(context);

        }


    }
}
