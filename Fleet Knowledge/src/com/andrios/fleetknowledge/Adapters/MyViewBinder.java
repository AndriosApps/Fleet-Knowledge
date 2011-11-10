package com.andrios.fleetknowledge.Adapters;

import com.andrios.fleetknowledge.R;

import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyViewBinder implements SimpleCursorAdapter.ViewBinder{

	public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
		int viewId = view.getId();
		switch(viewId){
		case R.id.question_list_item_askedIMG:
			ImageView typeIcon = (ImageView) view;
			String asked = cursor.getString(columnIndex);
			if(asked.equals("1")){
				System.out.println("asked " + asked);
				typeIcon.setImageResource(R.drawable.icon_checked);
			}else{

				System.out.println("not asked " + asked);
				typeIcon.setImageResource(R.drawable.icon_unchecked);
			}
		break;
		case R.id.question_list_item_questionLBL:
			TextView textLabel = (TextView) view;
			textLabel.setText(cursor.getString(columnIndex));
			break;
		}
		return true;
	}

}
