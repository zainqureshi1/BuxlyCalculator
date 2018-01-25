package com.e2esp.buxlypaints.calculator.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.e2esp.buxlypaints.calculator.R;
import com.e2esp.buxlypaints.calculator.models.Area;
import com.e2esp.buxlypaints.calculator.utils.Statics;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public class CalculatorActivity extends AppCompatActivity {

    private TextView textViewLabelLength;
    private TextView textViewExtraPart;

    private EditText editTextLength;
    private EditText editTextWidth;

    private View viewContainerWidth;
    private View viewContainerExtraPart;

    private Spinner spinnerExtraPart;
    private Spinner spinnerCoats;

    private Area area;
    private String[] numbersArray = {"1", "2", "3", "4", "5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setupViews();
    }

    public void setupViews() {
        TextView textViewPaint = findViewById(R.id.text_view_paint);
        TextView textViewArea = findViewById(R.id.text_view_area);
        TextView textViewRecommendedCoats = findViewById(R.id.text_view_recommended_coats);
        textViewLabelLength = findViewById(R.id.text_view_label_length);
        textViewExtraPart = findViewById(R.id.text_view_extra_part);

        editTextLength = findViewById(R.id.edit_text_length);
        editTextWidth = findViewById(R.id.edit_text_width);

        viewContainerWidth = findViewById(R.id.view_container_width);
        viewContainerExtraPart = findViewById(R.id.view_container_extra_part);

        Button buttonCalculate = findViewById(R.id.button_calculate);
        View viewBack = findViewById(R.id.view_back);
        View viewReset = findViewById(R.id.view_reset);

        RadioGroup radioGroupMeasurementType = findViewById(R.id.radio_group_measurement_type);

        spinnerExtraPart = findViewById(R.id.spinner_extra_part);
        spinnerCoats = findViewById(R.id.spinner_coats);
        Spinner spinnerMeasurementSize = findViewById(R.id.spinner_measurement_size);

        ArrayAdapter<String> adapterNumbers = new ArrayAdapter<>(this, R.layout.spinner_item_text, numbersArray);
        ArrayAdapter<String> adapterMeasurementSize = new ArrayAdapter<>(this, R.layout.spinner_item_text, Statics.measurement);

        spinnerExtraPart.setAdapter(adapterNumbers);
        spinnerCoats.setAdapter(adapterNumbers);
        spinnerMeasurementSize.setAdapter(adapterMeasurementSize);

        area = Statics.getArea();
        textViewPaint.setText(Statics.getPaint().getName());
        textViewArea.setText(area.getString(this));
        String recomemdedCoatsString = getString(R.string.recommended_coats_c) + Statics.getPaint().getRecommend_cots();
        textViewRecommendedCoats.setText(recomemdedCoatsString);

        showExtraPart();

        editTextLength.setInputType(Configuration.KEYBOARD_12KEY);
        editTextWidth.setInputType(Configuration.KEYBOARD_12KEY);

        Statics.total_area_working = "0";
        radioGroupMeasurementType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                measurementTypeChanged(checkedId);
            }
        });

        spinnerMeasurementSize.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Statics.setMeasurement_s("" + pos);
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateClicked();
            }
        });

        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backClicked();
            }
        });

        viewReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetClicked();
            }
        });
    }

    private void showExtraPart() {
        switch (area) {
            case DOORS:
                viewContainerExtraPart.setVisibility(View.VISIBLE);
                textViewExtraPart.setText(R.string.doors);
                break;
            case WINDOWS:
                viewContainerExtraPart.setVisibility(View.VISIBLE);
                textViewExtraPart.setText(R.string.windows);
                break;
            case INTERIOR_WALLS:
                viewContainerExtraPart.setVisibility(View.VISIBLE);
                textViewExtraPart.setText(R.string.walls_sides);
                break;
        }
    }

    private void measurementTypeChanged(int checkedId) {
        switch (checkedId) {
            case R.id.radio_button_dimensions:
                String lengthString = getString(R.string.length) + " ";
                textViewLabelLength.setText(lengthString);

                showExtraPart();

                viewContainerWidth.setVisibility(View.VISIBLE);
                editTextWidth.setText("");

                editTextLength.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                editTextLength.setInputType(InputType.TYPE_CLASS_NUMBER);

                Statics.total_area_working = "0";
                break;
            case R.id.radio_button_total_area:
                String totalAreaString = getString(R.string.total_area) + " ";
                textViewLabelLength.setText(totalAreaString);

                viewContainerExtraPart.setVisibility(View.GONE);

                viewContainerWidth.setVisibility(View.GONE);
                editTextWidth.setText("1");

                editTextLength.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

                Statics.total_area_working = "1";
                break;
        }
    }

    private String getNoOfCoats() {
        int coatsSelectedIndex = spinnerCoats.getSelectedItemPosition();
        if (coatsSelectedIndex >= 0 && coatsSelectedIndex < numbersArray.length) {
            return numbersArray[coatsSelectedIndex];
        }
        return "";
    }

    private String getNoOfExtraParts() {
        int extraPartsSelectedIndex = spinnerExtraPart.getSelectedItemPosition();
        if (extraPartsSelectedIndex >= 0 && extraPartsSelectedIndex < numbersArray.length) {
            return numbersArray[extraPartsSelectedIndex];
        }
        return "";
    }

    private void calculateClicked() {
        String length = editTextLength.getText().toString();
        String width = editTextWidth.getText().toString();
        if (!width.isEmpty() && !length.isEmpty()) {
            if ((length.matches("[0-9]*$") || length.matches("^[0-9]*?(\\.[0-9]*?)?$")) && (width.matches("[0-9]*$") || width.matches("^[0-9]*?(\\.[0-9]*?)?$"))) {
                Statics.setLength(length);
                Statics.setWidth(width);
                Statics.setCoats(getNoOfCoats());
                Statics.setWindow_door(getNoOfExtraParts());
                startActivity(new Intent(getBaseContext(), ResultActivity.class));
                finish();
            } else if (!length.matches("[0-9]*$") && !length.matches("^[0-9]*?(\\.[0-9]*?)?$")) {
                editTextLength.setError(getString(R.string.please_enter_valid_value));
            } else if (!width.matches("[0-9]*$") && !width.matches("^[0-9]*?(\\.[0-9]*?)?$")) {
                editTextWidth.setError(getString(R.string.please_enter_valid_width));
            }
        } else if (length.isEmpty()) {
            editTextLength.setError(getString(R.string.please_enter_value));
        } else if (width.isEmpty()) {
            editTextWidth.setError(getString(R.string.please_enter_width));
        } else {
            editTextWidth.setError(getString(R.string.please_enter_value));
            editTextLength.setError(getString(R.string.please_enter_width));
        }
    }

    private void backClicked() {
        startActivity(new Intent(getBaseContext(), SelectPaintActivity.class));
        finish();
    }

    private void resetClicked() {
        Statics.reset();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

}
