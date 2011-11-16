package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
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

public class RecceSubsDetailsController extends Activity {
	

	Ship ship;
	Button newBTN;
	TextView typeLBL;
	TextView classLBL;
	TextView dimensionLBL;
	TextView crewLBL;
	TextView weaponsLBL;
	TextView performanceLBL;
	TextView propulsionLBL;
	TextView moreInfoLBL;
	LinearLayout header;
	ViewFlipper flipper;
	GoogleAnalyticsTracker tracker;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recceshipsdetailsview);
      
		
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
	   		tracker.trackPageView("/" + this.getLocalClassName()+"/"+ship.getShip_class());
	   	}

	   	@Override
	   	public void onPause() {
	   		super.onPause();
	   		tracker.dispatch();
	   	}

	

	private void getExtras() {
		Intent intent = this.getIntent();
		ship = (Ship) intent.getSerializableExtra("sub");
		
	}



	private void setConnections() {
		newBTN = (Button) findViewById(R.id.creedsNewBTN);
		typeLBL = (TextView) findViewById(R.id.recceShipsDetailsViewTypeLBL);
		typeLBL.setText(ship.getType());
		classLBL = (TextView) findViewById(R.id.recceShipsDetailsViewClassLBL);
		classLBL.setText(ship.getShip_class());
		dimensionLBL = (TextView) findViewById(R.id.recceShipsDetailsViewDimensionsLBL);
		dimensionLBL.setText(ship.getDimension());
		crewLBL = (TextView) findViewById(R.id.recceShipsDetailsViewCrewLBL);
		crewLBL.setText(ship.getCrew());
		weaponsLBL = (TextView) findViewById(R.id.recceShipsDetailsViewWeaponsLBL);
		weaponsLBL.setText(ship.getWeapons());
		performanceLBL = (TextView) findViewById(R.id.recceShipsDetailsViewPerformanceLBL);
		performanceLBL.setText(ship.getPerformance());
		propulsionLBL = (TextView) findViewById(R.id.recceShipsDetailsViewPropulsionLBL);
		propulsionLBL.setText(ship.getPropulsion());
		TextView sensorsLBL = (TextView) findViewById(R.id.recceShipsDetailsViewSensorsLBL);
		sensorsLBL.setText(ship.getSensors());
		TextView ewLBL = (TextView) findViewById(R.id.recceShipsDetailsViewEwLBL);
		ewLBL.setText(ship.getEw());
		TextView aircraftLBL = (TextView) findViewById(R.id.recceShipsDetailsViewAircraftLBL);
		aircraftLBL.setText(ship.getAircraft());
		TextView boatsLBL = (TextView) findViewById(R.id.recceShipsDetailsViewBoatsLBL);
		boatsLBL.setText(ship.getBoats());
		ImageView image = (ImageView) findViewById(R.id.recceShipsDetailsViewImageView);
		moreInfoLBL = (TextView) findViewById(R.id.recceShipsDetailViewMoreInfoLBL);
		
		image.setImageResource(ship.getImage());
		
		header = (LinearLayout) findViewById(R.id.recceShipDetailsViewHeader);
		
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
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(ship.getLink()));
				startActivity(browserIntent);
				
			}
			
		});
		
	}
	
	

}