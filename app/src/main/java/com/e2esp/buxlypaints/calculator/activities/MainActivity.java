package com.e2esp.buxlypaints.calculator.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.crashlytics.android.Crashlytics;
import com.e2esp.buxlypaints.calculator.R;
import com.e2esp.buxlypaints.calculator.models.Area;
import com.e2esp.buxlypaints.calculator.utils.Statics;
import com.e2esp.buxlypaints.calculator.utils.Consts;
import io.fabric.sdk.android.Fabric;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public class MainActivity extends AppCompatActivity {

    private AppCompatButton buttonCeiling;
    private AppCompatButton buttonExteriorWalls;
    private AppCompatButton buttonInteriorWalls;
    private AppCompatButton buttonDoors;
    private AppCompatButton buttonWindows;
    private AppCompatButton buttonWoodFinishes;

    private View viewNext;

    private Typeface fontSelected, fontNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        viewNext = findViewById(R.id.view_next);
        buttonCeiling = findViewById(R.id.button_ceiling);
        buttonExteriorWalls = findViewById(R.id.button_exterior_walls);
        buttonInteriorWalls = findViewById(R.id.button_interior_walls);
        buttonDoors = findViewById(R.id.button_doors);
        buttonWindows = findViewById(R.id.button_windows);
        buttonWoodFinishes = findViewById(R.id.button_wood_finishes);

        fontSelected = Typeface.createFromAsset(getAssets(), Consts.FONT_RALEWAY_SEMI_BOLD);
        fontNormal = Typeface.createFromAsset(getAssets(), Consts.FONT_RALEWAY_LIGHT);

        viewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextClicked();
            }
        });

        buttonCeiling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSelected(buttonCeiling);
                Statics.setArea(Area.CEILING);
            }
        });
        buttonExteriorWalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSelected(buttonExteriorWalls);
                Statics.setArea(Area.EXTERIOR_WALLS);
            }
        });
        buttonInteriorWalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSelected(buttonInteriorWalls);
                Statics.setArea(Area.INTERIOR_WALLS);
            }
        });
        buttonDoors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSelected(buttonDoors);
                Statics.setArea(Area.DOORS);
            }
        });
        buttonWindows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSelected(buttonWindows);
                Statics.setArea(Area.WINDOWS);
            }
        });
        buttonWoodFinishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSelected(buttonWoodFinishes);
                Statics.setArea(Area.WOOD_FINISHES);
            }
        });
    }

    private void setButtonSelected(AppCompatButton button) {
        resetAllButtons();

        button.setBackgroundResource(R.drawable.select_button_shape);
        button.setTypeface(fontSelected);

        viewNext.setVisibility(View.VISIBLE);
        Animation set = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in);
        viewNext.startAnimation(set);
    }

    private void resetAllButtons() {
        buttonCeiling.setBackgroundResource(R.drawable.button_shape);
        buttonExteriorWalls.setBackgroundResource(R.drawable.button_shape);
        buttonInteriorWalls.setBackgroundResource(R.drawable.button_shape);
        buttonDoors.setBackgroundResource(R.drawable.button_shape);
        buttonWindows.setBackgroundResource(R.drawable.button_shape);
        buttonWoodFinishes.setBackgroundResource(R.drawable.button_shape);

        buttonCeiling.setTypeface(fontNormal);
        buttonExteriorWalls.setTypeface(fontNormal);
        buttonInteriorWalls.setTypeface(fontNormal);
        buttonDoors.setTypeface(fontNormal);
        buttonWindows.setTypeface(fontNormal);
        buttonWoodFinishes.setTypeface(fontNormal);
    }

    private void nextClicked() {
        startActivity(new Intent(getBaseContext(), SelectPaintActivity.class));
        finish();
    }

}
