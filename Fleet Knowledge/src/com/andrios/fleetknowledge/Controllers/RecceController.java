package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class RecceController extends Activity {
	
	Button shipsBTN;
	Button subsBTN;
	Button acBTN;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recceview);
        
        setConnections();
        setOnClickListeners();
        
    }

	private void setConnections() {
		shipsBTN = (Button)findViewById(R.id.recceViewShipsBTN);
		subsBTN = (Button)findViewById(R.id.recceViewSubsBTN);
		acBTN = (Button)findViewById(R.id.recceViewAircraftBTN);
		
	}

	private void setOnClickListeners() {
		shipsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecceShipsController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		subsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecceSubsController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		acBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecceAircraftController.class);
				
				startActivity(intent);
				
			}
			
		});
	}
}