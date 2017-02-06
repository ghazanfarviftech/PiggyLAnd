package com.example.ghazanfarali.piggyland.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.ghazanfarali.piggyland.Helper.Fonts;
import com.example.ghazanfarali.piggyland.R;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class CustomEditText extends EditText {
    int textStyle = 0;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Typeface face = Fonts.getLightFont(getContext());
        setTypeface(face);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FontStyle);
        textStyle = typedArray.getInt(R.styleable.FontStyle_fontType, 0);
        if (textStyle == 0) {//Light
            setTypeface(Fonts.getLightFont(getContext()));
        } else if (textStyle == 1) {//medium
            setTypeface(Fonts.getMediumFont(getContext()));
        } else if (textStyle == 2) {//bold
            setTypeface(Fonts.getBoldFont(getContext()));
//            setText(CharacterUtils.getArabicNumbers((getText().toString())));
        } else if (textStyle == 3) {//opensans semi bold
            setTypeface(Fonts.getOpenSansSemiBold(getContext()));
//            setText(CharacterUtils.getArabicNumbers((getText().toString())));

        } else if (textStyle == 4) {//opensans semi bold
            setTypeface(Fonts.getOpenSansLight(getContext()));
//            setText(CharacterUtils.getArabicNumbers((getText().toString())));

        } else {
            setTypeface(Fonts.getLightFont(getContext()));
        }
    }

}

