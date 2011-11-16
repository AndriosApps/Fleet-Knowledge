package com.andrios.fleetknowledge.Controllers;

import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Adapters.ExpandableMissileAdapter;
import com.andrios.fleetknowledge.Adapters.MyCreedsViewBinder;
import com.andrios.fleetknowledge.Adapters.MyViewBinder;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.andrios.fleetknowledge.Models.Question;
import com.andrios.fleetknowledge.Models.Missile;
import com.andrios.fleetknowledge.R.layout;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

public class RecceMissilesController extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ExpandableListView listView;
	ExpandableMissileAdapter listAdapter;
	ArrayList<Missile> missileList;
	Cursor cursor;
	Button newBTN;
	GoogleAnalyticsTracker tracker;
	
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recceshipsview);
        mDbAdapter = new AndriosDbAdapter(this);
		mDbAdapter.open();
		

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
		TextView titleLBL = (TextView) findViewById(R.id.recceShipsViewTitleLBL);
		titleLBL.setText("US Navy Missiles");
		listView = (ExpandableListView) findViewById(R.id.recceShipViewExpandableListView);
		fillData();
		listAdapter = new ExpandableMissileAdapter(this, new ArrayList<String>(), new ArrayList<ArrayList<Missile>>());
		listView.setAdapter(listAdapter);
		addMissilesToAdapter();
	}
	
	private void addMissilesToAdapter(){
		
		for(int i = 0; i < missileList.size(); i++){
			listAdapter.addItem(missileList.remove(0));
			i--;
		}
	}

	private void setOnClickListeners() {
		listView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(v.getContext(), RecceMissilesDetailsController.class);
				intent.putExtra("missile", (Missile) listAdapter.getChild(groupPosition, childPosition));
				startActivity(intent);
				
				return false;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllMissiles();
	    	startManagingCursor(cursor);
	    	
	    	
		    	cursor.moveToFirst();
		    	missileList = new ArrayList<Missile>();
		        while (cursor.isAfterLast() == false) {
		            missileList.add(new Missile(cursor, RecceMissilesController.this));
		       	    cursor.moveToNext();
		        }
		        cursor.close();
	    	
	    	
	    }
	 
	    /**
	     * Called with the result of the other activity requestCode was the origin
	     * request code sent to the activity resultCode is the return code, 0
	     * if everything is ok intent can be used to get some data from the caller
	     */
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
	    	super.onActivityResult(requestCode, resultCode, intent);
	    	fillData();
	    }
	 

	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}