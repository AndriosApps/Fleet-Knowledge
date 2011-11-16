package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Models.Missile;
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

public class RecceMissilesDetailsController extends Activity {
	

	Missile missile;
	
	TextView nameLBL;
	TextView functionLBL;
	TextView priceLBL;
	TextView dateLBL;
	TextView dimensionsLBL;
	TextView performanceLBL;
	TextView warheadLBL;
	TextView descriptionLBL;
	TextView backgroundLBL;
	TextView featuresLBL;
	TextView propulsionLBL;
	TextView platformsLBL;
	
	TextView moreInfoLBL;
	
	LinearLayout header;
	ViewFlipper flipper;
	GoogleAnalyticsTracker tracker;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reccemissiledetailsview);
      
		
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
	   		tracker.trackPageView("/" + this.getLocalClassName()+"/"+missile.getName());
	   	}

	   	@Override
	   	public void onPause() {
	   		super.onPause();
	   		tracker.dispatch();
	   	}

	

	private void getExtras() {
		Intent intent = this.getIntent();
		missile = (Missile) intent.getSerializableExtra("missile");
		
	}



	private void setConnections() {
		nameLBL = (TextView) findViewById(R.id.recceMissileDetailsViewNameLBL);
		nameLBL.setText(missile.getName());

		functionLBL = (TextView) findViewById(R.id.recceMissileDetailsViewFunctionLBL);
		functionLBL.setText(missile.getFunction());

		priceLBL = (TextView) findViewById(R.id.recceMissileDetailsViewPriceLBL);
		priceLBL.setText(missile.getPrice());

		dateLBL = (TextView) findViewById(R.id.recceMissileDetailsViewDateLBL);
		dateLBL.setText(missile.getDeploy_date());

		dimensionsLBL = (TextView) findViewById(R.id.recceMissileDetailsViewDimensionsLBL);
		dimensionsLBL.setText(missile.getDimensions());

		performanceLBL = (TextView) findViewById(R.id.recceMissileDetailsViewPerformanceLBL);
		performanceLBL.setText(missile.getPerformance());

		warheadLBL = (TextView) findViewById(R.id.recceMissileDetailsViewWarheadLBL);
		warheadLBL.setText(missile.getWarhead());

		descriptionLBL = (TextView) findViewById(R.id.recceMissileDetailsViewDescriptionLBL);
		descriptionLBL.setText(missile.getDescription());

		backgroundLBL = (TextView) findViewById(R.id.recceMissileDetailsViewBackgroundLBL);
		backgroundLBL.setText(missile.getBackground());

		featuresLBL = (TextView) findViewById(R.id.recceMissileDetailsViewFeaturesLBL);
		featuresLBL.setText(missile.getFeatures());

		propulsionLBL = (TextView) findViewById(R.id.recceMissileDetailsViewPropulsionLBL);
		propulsionLBL.setText(missile.getPropulsion());

		platformsLBL = (TextView) findViewById(R.id.recceMissileDetailsViewPlatformsLBL);
		platformsLBL.setText(missile.getPlatforms());
		

		
		
		
		moreInfoLBL = (TextView) findViewById(R.id.recceMissileDetailViewMoreInfoLBL);
		
		ImageView image = (ImageView) findViewById(R.id.recceMissilesDetailsViewImageView);
		image.setImageResource(missile.getImage());
		
		header = (LinearLayout) findViewById(R.id.recceMissileDetailsViewHeader);
		
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
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(missile.getLink()));
				startActivity(browserIntent);
				
			}
			
		});
		
	}
	
	

}