package com.e2esp.buxlypaints.calculator.models;

import android.content.Context;

import com.e2esp.buxlypaints.calculator.R;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public enum Area {
    NA,
    CEILING,
    EXTERIOR_WALLS,
    INTERIOR_WALLS,
    DOORS,
    WINDOWS,
    WOOD_FINISHES;
    public String getString(Context context) {
        switch (this) {
            case CEILING:
                return context.getString(R.string.ceiling);
            case EXTERIOR_WALLS:
                return context.getString(R.string.exterior_walls);
            case INTERIOR_WALLS:
                return context.getString(R.string.interior_walls);
            case DOORS:
                return context.getString(R.string.doors);
            case WINDOWS:
                return context.getString(R.string.windows);
            case WOOD_FINISHES:
                return context.getString(R.string.wood_finishes);
        }
        return "";
    }
}
