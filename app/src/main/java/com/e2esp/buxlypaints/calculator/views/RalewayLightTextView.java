package com.e2esp.buxlypaints.calculator.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.e2esp.buxlypaints.calculator.utils.Consts;

/**
 *
 * Created by PAPPU on 12/29/2016.
 */

public  class RalewayLightTextView extends AppCompatTextView {

    public RalewayLightTextView(Context context, AttributeSet attrs){
        super(context,attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), Consts.FONT_RALEWAY_LIGHT));
    }

}