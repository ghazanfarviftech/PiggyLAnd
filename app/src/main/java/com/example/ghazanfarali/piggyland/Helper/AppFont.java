package com.example.ghazanfarali.piggyland.Helper;

import android.content.Context;
import android.graphics.Typeface;

import com.example.ghazanfarali.piggyland.R;


/**
 * Created by Amir.jehangir on 1/2/2017.
 */
public class AppFont {
    private static AppFont _appFont = null;

    private Typeface _boldTypeFace = null;
    private Typeface _lightTypeFace = null;
    private Typeface _mediumTypeFace = null;
    private Typeface _open_sans_TypeFace = null;
    private Typeface _open_sans_TypeFace_light = null;

    private AppFont(Context context) {
        loadFont(context);
    }

    public static AppFont getInstance(Context context) {
        if (_appFont == null) {
            _appFont = new AppFont(context);
        }
        return _appFont;
    }

    public Typeface getTypeFace() {
        return _lightTypeFace;
    }

    public static AppFont get_appFont() {
        return _appFont;
    }

    public Typeface get_boldTypeFace() {
        return _boldTypeFace;
    }

    public Typeface get_lightTypeFace() {
        return _lightTypeFace;
    }

    public Typeface get_mediumTypeFace() {
        return _mediumTypeFace;
    }

    public Typeface get_open_sans_TypeFace() {
        return _open_sans_TypeFace;
    }

    public void loadFont(Context context) {
        _lightTypeFace = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", context.getString(R.string.font_bold)));
        _mediumTypeFace = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", context.getString(R.string.font_light)));
        _boldTypeFace = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", context.getString(R.string.font_bold)));
        _open_sans_TypeFace = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", context.getString(R.string.open_sans_TypeFace)));
        _open_sans_TypeFace_light = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", context.getString(R.string.open_sans_TypeFace_light)));
    }
}
