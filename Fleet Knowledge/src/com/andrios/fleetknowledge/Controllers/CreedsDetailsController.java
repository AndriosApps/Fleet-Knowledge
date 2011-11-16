package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CreedsDetailsController extends Activity {
	
	AndriosDbAdapter mDbAdapter;
	
	Cursor cursor;
	
	TextView titleTXT;
	TextView bodyTXT;
	long mRowId;
	int isKnown;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creedsdetailsview);
        mDbAdapter = new AndriosDbAdapter(this);
		mDbAdapter.open();
		
//		long id = mDbAdapter.createQuestion("What is a Test Question?", 0);
//		System.out.println("ID " + id);
//		Cursor info = mDbAdapter.fetchQuestion(id);
//		System.out.println(info.getString(info.getColumnIndexOrThrow(AndriosDbAdapter.KEY_QUESTION)));
//		
		getExtras();
		setConnections();
		setOnClickListeners();
		fillData();
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

	private void getExtras(){
		Intent intent = this.getIntent();
		mRowId = intent.getIntExtra(AndriosDbAdapter.KEY_ROWID, -1);
	}

	private void setConnections() {
		titleTXT = (TextView) findViewById(R.id.creedsDetailsTitleLBL);
		bodyTXT = (TextView) findViewById(R.id.creedsDetailsBodyLBL);
	}

	private void setOnClickListeners() {
		
		
	}
	
	 private void fillData(){
	    	
		 if(mRowId != -1){
				Cursor info = mDbAdapter.fetchCreed(mRowId);
				startManagingCursor(info);
				String title = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_TITLE));
				String body = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_BODY));
				isKnown = info.getInt(info.getColumnIndex(AndriosDbAdapter.KEY_KNOWN));
				titleTXT.setText(title);
				bodyTXT.setText(body);
			}
	    }
	 
	 private void saveState(){
			String title = titleTXT.getText().toString();
			String body = bodyTXT.getText().toString();
			
			if(mRowId == -1){
				long id = mDbAdapter.createCreed(title, body, 0);
				if(id > 0){
					mRowId = id;
				}
			}else {
				mDbAdapter.updateCreed(mRowId, title, body, isKnown);
			}
			
		}
	 
	 
	
}