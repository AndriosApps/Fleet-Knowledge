package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDatabaseHelper;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FleetKnowledgeActivity extends Activity {
	

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	Button recruitersBTN;
	Button recceBTN;
	Button musicBTN;
	Button knowledgeBTN;
	Button toolsBTN;
	Button aboutBTN;
	AndriosDatabaseHelper helper;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        helper = new AndriosDatabaseHelper(this);
        try{
        	helper.createDataBase();
        }catch(Exception e){
        	e.printStackTrace();
        }
        AppRater.app_launched(this);
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
   		tracker.trackPageView("/" + this.getLocalClassName());
   	}

   	@Override
   	public void onPause() {
   		super.onPause();
   		tracker.dispatch();
   	}

	private void setConnections() {
		recruitersBTN = (Button) findViewById(R.id.mainViewRecruiterBTN);
		recceBTN = (Button) findViewById(R.id.mainViewRecceBTN);
		musicBTN = (Button) findViewById(R.id.mainViewMusicBTN);
		knowledgeBTN = (Button) findViewById(R.id.mainViewKnowledgeBTN);
		toolsBTN = (Button) findViewById(R.id.mainViewToolsBTN);
		aboutBTN = (Button) findViewById(R.id.mainViewAboutBTN);
		
	}

	private void setOnClickListeners() {
		recruitersBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecruitersController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		recceBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecceController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		musicBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MusicController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		knowledgeBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), KnowledgeController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		toolsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ToolsController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		aboutBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), AboutActivity.class);
				
				startActivity(intent);
				
			}
			
		});
		
	}
}