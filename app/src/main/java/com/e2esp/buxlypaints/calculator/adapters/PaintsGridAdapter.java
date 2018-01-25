package com.e2esp.buxlypaints.calculator.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.e2esp.buxlypaints.calculator.R;
import com.e2esp.buxlypaints.calculator.models.Paint;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public class PaintsGridAdapter extends ArrayAdapter<Paint> {

	private LayoutInflater inflater;
	private Resources resources;
	private Paint[] paints;
	private int selectedPosition = -1;

	public PaintsGridAdapter(Context context, Paint[] paints) {
		super(context, R.layout.color_box, paints);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resources = context.getResources();
		this.paints = paints;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;

		if (row == null) {
			row = inflater.inflate(R.layout.color_box, parent, false);
			holder = new ViewHolder();

			holder.textViewTitle = row.findViewById(R.id.item_text);
			holder.imageViewBackground = row.findViewById(R.id.backgrond);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		Paint paint = paints[position];
		holder.imageViewBackground.setImageDrawable(resources.getDrawable(paint.getImage_id()));
		holder.textViewTitle.setText(paint.getName());

		if(position == selectedPosition) {
			holder.imageViewBackground.setBackgroundResource(R.drawable.select_image_boder);
		} else {
			holder.imageViewBackground.setBackgroundColor(Color.TRANSPARENT);
		}

		return row;
	}

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    private class ViewHolder {
		TextView textViewTitle;
		ImageView imageViewBackground;
	}

}