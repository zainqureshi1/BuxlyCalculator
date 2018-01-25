package com.e2esp.buxlypaints.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.e2esp.buxlypaints.calculator.adapters.PaintsGridAdapter;
import com.e2esp.buxlypaints.calculator.R;
import com.e2esp.buxlypaints.calculator.models.Area;
import com.e2esp.buxlypaints.calculator.utils.Statics;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public class SelectPaintActivity extends AppCompatActivity {

    private TextView textViewPaintName;
    private GridView gridViewPaints;
    private View viewNext;

    private PaintsGridAdapter customGridAdapter;

    private Area selectedArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_paint);

        Statics.paint_list();

        setupViews();
    }

    private void setupViews() {
        textViewPaintName = findViewById(R.id.text_view_paint_name);
        gridViewPaints = findViewById(R.id.grid_view_paints);
        viewNext = findViewById(R.id.view_next);
        View viewBack = findViewById(R.id.view_back);

        selectedArea = Statics.getArea();
        switch (selectedArea) {
            case CEILING:
            case INTERIOR_WALLS:
                customGridAdapter = new PaintsGridAdapter(this, Statics.paint_Image_arry);
                break;
            case EXTERIOR_WALLS:
                customGridAdapter = new PaintsGridAdapter(this, Statics.exterior);
                break;
            case DOORS:
            case WINDOWS:
                customGridAdapter = new PaintsGridAdapter(this, Statics.doors_and_windows);
                break;
            case WOOD_FINISHES:
                customGridAdapter = new PaintsGridAdapter(this, Statics.wood_finishes);
                break;
        }

        gridViewPaints.setAdapter(customGridAdapter);
        gridViewPaints.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paintClicked(position);
            }
        });

        viewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextClicked();
            }
        });

        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backClicked();
            }
        });

    }

    private void paintClicked(int position) {
        gridViewPaints.setSelection(position);
        customGridAdapter.setSelectedPosition(position);
        customGridAdapter.notifyDataSetChanged();

        switch (selectedArea) {
            case CEILING:
            case INTERIOR_WALLS:
                Statics.setPaint(Statics.paint_Image_arry[position]);
                break;
            case EXTERIOR_WALLS:
                Statics.setPaint(Statics.exterior[position]);
                break;
            case DOORS:
            case WINDOWS:
                Statics.setPaint(Statics.doors_and_windows[position]);
                break;
            case WOOD_FINISHES:
                Statics.setPaint(Statics.wood_finishes[position]);
                break;
        }

        textViewPaintName.setText(Statics.getPaint().getName());
        viewNext.setVisibility(View.VISIBLE);
        Animation set = AnimationUtils.loadAnimation(SelectPaintActivity.this, R.anim.slide_in);
        viewNext.startAnimation(set);
    }

    private void nextClicked() {
        startActivity(new Intent(getBaseContext(), CalculatorActivity.class));
        finish();
    }

    private void backClicked() {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

}
