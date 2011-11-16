package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Models.Aircraft;
import com.andrios.fleetknowledge.Models.Ship;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class RecceAircraftDetailsController extends Activity {
	

	Aircraft aircraft;
	Button newBTN;
	TextView functionLBL;
	TextView typeLBL;
	TextView propulsionLBL;
	TextView performanceLBL;
	TextView sizeLBL;
	TextView crewLBL;
	TextView sensorsLBL;
	TextView moreInfoLBL;
	LinearLayout header;
	ViewFlipper flipper;
	GoogleAnalyticsTracker tracker;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recceaircraftdetailsview);
      
		
        getExtras();
		setConnections();
		setOnClickListeners();
	    setTracker();
	    }
	  
	   	private void setTracker() {
	   		tracker = GoogleAnalyticsTracker.getInstance();
	   		tracker.start(this.getString(R.string.ga_api_key),
	   				getApplicationContext());
	   	}

	   	@Override
	   	public void onResume() {
	   		super.onResume();
	   		tracker.trackPageView("/" + this.getLocalClassName()+"/"+aircraft.getAc_type());
	   	}

	   	@Override
	   	public void onPause() {
	   		super.onPause();
	   		tracker.dispatch();
	   	}

	

	private void getExtras() {
		Intent intent = this.getIntent();
		aircraft = (Aircraft) intent.getSerializableExtra("aircraft");
		
	}



	private void setConnections() {
		moreInfoLBL = (TextView) findViewById(R.id.recceAircraftDetailViewMoreInfoLBL);
		functionLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewFunctionLBL);
		functionLBL.setText(aircraft.getFunction());
		typeLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewTypeLBL);
		typeLBL.setText(aircraft.getAc_type());
		propulsionLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewPropulsionLBL);
		propulsionLBL.setText(aircraft.getPropulsion());
		performanceLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewPerformanceLBL);
		performanceLBL.setText(aircraft.getPerformance());
		sizeLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewDimensionsLBL);
		sizeLBL.setText(aircraft.getSize());
		crewLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewCrewLBL);
		crewLBL.setText(aircraft.getCrew());
		sensorsLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewSensorsLBL);
		sensorsLBL.setText(aircraft.getSensors());
		TextView armamentLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewArmamentLBL);
		armamentLBL.setText(aircraft.getArmament());
		TextView currentLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewCurrentLBL);
		currentLBL.setText(aircraft.getCurrent());
		TextView missionLBL = (TextView) findViewById(R.id.recceAircraftDetailsViewMissionLBL);
		missionLBL.setText(aircraft.getMission());
		
		
		ImageView image = (ImageView) findViewById(R.id.recceAircraftDetailsViewImageView);
		image.setImageResource(aircraft.getImage());
		
		header = (LinearLayout) findViewById(R.id.recceAircraftDetailsViewHeader);
		
		flipper = (ViewFlipper) findViewById(R.id.viewFlipper1); 
		flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
	    flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));  
	}
	
	
	

	private void setOnClickListeners() {
		header.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				flipper.showNext();
				
			}
			
		});
		
		moreInfoLBL.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(aircraft.getLink()));
				startActivity(browserIntent);
				
			}
			
		});
		
	}
	
	

}