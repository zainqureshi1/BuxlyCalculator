package com.e2esp.buxlypaints.calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.e2esp.buxlypaints.calculator.R;
import com.e2esp.buxlypaints.calculator.models.Area;
import com.e2esp.buxlypaints.calculator.utils.Statics;

import java.math.BigDecimal;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Statics.Calculat();
        Statics.price_of_paint();
        Statics.price_of_s_f();
        Statics.price_of_s_m();

        setupViews();
    }

    private void setupViews() {
        TextView textViewPaint = findViewById(R.id.text_view_paint);
        TextView textViewArea = findViewById(R.id.text_view_area);
        TextView textViewLabelLength = findViewById(R.id.text_view_label_length);
        TextView textViewLength = findViewById(R.id.text_view_length);
        TextView textViewWidth = findViewById(R.id.text_view_width);
        TextView textViewCoats = findViewById(R.id.text_view_coats);
        TextView textViewExtraPart = findViewById(R.id.text_view_extra_part);
        TextView textViewExtraPartLabel = findViewById(R.id.text_view_extra_part_label);
        TextView textViewResult = findViewById(R.id.text_view_result);
        TextView textViewPrice = findViewById(R.id.text_view_price);
        TextView textViewUnitPrice = findViewById(R.id.text_view_unit_price);

        View viewContainerWidth = findViewById(R.id.view_container_width);
        View viewContainerExtraPart = findViewById(R.id.view_container_extra_part);
        View viewReset = findViewById(R.id.view_next);
        View viewBack = findViewById(R.id.view_back);

        String paintName = Statics.getPaint().getName();
        boolean isPaintInKg = paintName.equals("INTERIOR TEXTURE") || paintName.equals("SAND FINISH") || paintName.equals("EXTERIOR TEXTURE");
        Area area = Statics.getArea();
        textViewPaint.setText(paintName);
        textViewArea.setText(area.getString(this));
        textViewLength.setText(Statics.getLength());
        textViewWidth.setText(Statics.getWidth());
        textViewCoats.setText(Statics.getCoats());
        textViewExtraPart.setText(Statics.getWindow_door());
        BigDecimal price = new BigDecimal(Statics.getPaint_price());
        String priceWithCurrency = "Rs: " + price.toPlainString();
        textViewPrice.setText(priceWithCurrency);

        if (Statics.getMeasurement_s().equals("0")) {
            if (isPaintInKg) {
                textViewUnitPrice.setText(Html.fromHtml("Rs: " + Statics.getPaint_price_s_f() + "ft<sup>2</sup>/kg/Coat"));
            } else {
                textViewUnitPrice.setText(Html.fromHtml("Rs: " + Statics.getPaint_price_s_f() + "ft<sup>2</sup>/Ltr/Coat "));
            }
        } else {
            if (isPaintInKg) {
                textViewUnitPrice.setText(Html.fromHtml("Rs: " + Statics.getPaint_price_s_m() + "m<sup>2</sup>/kg/Coat"));
            } else {
                textViewUnitPrice.setText(Html.fromHtml("Rs: " + Statics.getPaint_price_s_m() + "m<sup>2</sup>/Ltr/Coat"));
            }
        }

        if (Statics.total_area_working.equals("0")) {
            switch (area) {
                case DOORS:
                    viewContainerExtraPart.setVisibility(View.VISIBLE);
                    textViewExtraPartLabel.setText(R.string.no_of_doors);
                    break;
                case WINDOWS:
                    viewContainerExtraPart.setVisibility(View.VISIBLE);
                    textViewExtraPartLabel.setText(R.string.no_of_windows);
                    break;
                case INTERIOR_WALLS:
                    viewContainerExtraPart.setVisibility(View.VISIBLE);
                    textViewExtraPartLabel.setText(R.string.walls_sides);
                    break;
            }
        } else {
            viewContainerExtraPart.setVisibility(View.GONE);
            viewContainerWidth.setVisibility(View.GONE);
            textViewLabelLength.setText(R.string.total_area);
        }

        price = new BigDecimal(Statics.getResult());
        if (isPaintInKg) {
            String priceWithUnit = price.toPlainString() + "Kg";
            textViewResult.setText(priceWithUnit);
        } else {
            String priceWithUnit = price.toPlainString() + "Liter(s)";
            textViewResult.setText(priceWithUnit);
        }

        viewReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetClicked();
            }
        });

        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backClicked();
            }
        });
    }

    private void resetClicked() {
        Statics.reset();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    private void backClicked() {
        startActivity(new Intent(getBaseContext(), CalculatorActivity.class));
        finish();
    }

}
