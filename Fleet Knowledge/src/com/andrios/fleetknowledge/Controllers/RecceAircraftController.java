package com.andrios.fleetknowledge.Controllers;

import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Adapters.ExpandableAircraftAdapter;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.andrios.fleetknowledge.Models.Aircraft;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class RecceAircraftController extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ExpandableListView listView;
	ExpandableAircraftAdapter listAdapter;
	ArrayList<Aircraft> shipList;
	Cursor cursor;
	Button newBTN;
	
	
	
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
    }

	

	private void setConnections() {
		newBTN = (Button) findViewById(R.id.creedsNewBTN);
		listView = (ExpandableListView) findViewById(R.id.recceShipViewExpandableListView);
		fillData();
		listAdapter = new ExpandableAircraftAdapter(this, new ArrayList<String>(), new ArrayList<ArrayList<Aircraft>>());
		listView.setAdapter(listAdapter);
		addAircraftsToAdapter();
	}
	
	private void addAircraftsToAdapter(){
		
		for(int i = 0; i < shipList.size(); i++){
			listAdapter.addItem(shipList.remove(0));
			i--;
		}
	}

	private void setOnClickListeners() {
		listView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(v.getContext(), RecceAircraftDetailsController.class);
				intent.putExtra("aircraft", (Aircraft) listAdapter.getChild(groupPosition, childPosition));
				startActivity(intent);
				
				return false;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllAircrafts();
	    	startManagingCursor(cursor);
	    	
	    	if(cursor.getCount() < 1){
	    		buildBaseAircraft();
	    		fillData();
	    		
	    	}else{
	    	
		    	cursor.moveToFirst();
		    	shipList = new ArrayList<Aircraft>();
		        while (cursor.isAfterLast() == false) {
		            shipList.add(new Aircraft(cursor, RecceAircraftController.this));
		       	    cursor.moveToNext();
		        }
		        cursor.close();
	    	}
	    	
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
	 
	 private void buildBaseAircraft(){
		 System.out.println("Base AC");
		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.ea6b_function), 
				 getResources().getString(R.string.ea6b_type), 
				 getResources().getString(R.string.ea6b_propulsion), 
				 getResources().getString(R.string.ea6b_performance), 
				 getResources().getString(R.string.ea6b_size), 
				 getResources().getString(R.string.ea6b_crew), 
				 getResources().getString(R.string.ea6b_sensors), 
				 getResources().getString(R.string.ea6b_armament), 
				 getResources().getString(R.string.ea6b_current), 
				 getResources().getString(R.string.ea6b_mission), 
				 "prowler",
				 getResources().getString(R.string.ea6b_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.e2c_function), 
				 getResources().getString(R.string.e2c_type), 
				 getResources().getString(R.string.e2c_propulsion), 
				 getResources().getString(R.string.e2c_performance), 
				 getResources().getString(R.string.e2c_size), 
				 getResources().getString(R.string.e2c_crew), 
				 getResources().getString(R.string.e2c_sensors), 
				 getResources().getString(R.string.e2c_armament), 
				 getResources().getString(R.string.e2c_current), 
				 getResources().getString(R.string.e2c_mission), 
				 "e2c",
				 getResources().getString(R.string.e2c_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.greyhound_function), 
				 getResources().getString(R.string.greyhound_type), 
				 getResources().getString(R.string.greyhound_propulsion), 
				 getResources().getString(R.string.greyhound_performance), 
				 getResources().getString(R.string.greyhound_size), 
				 getResources().getString(R.string.greyhound_crew), 
				 getResources().getString(R.string.greyhound_sensors), 
				 getResources().getString(R.string.greyhound_armament), 
				 getResources().getString(R.string.greyhound_current), 
				 getResources().getString(R.string.greyhound_mission), 
				 "greyhound",
				 getResources().getString(R.string.greyhound_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.gulfstream3_function), 
				 getResources().getString(R.string.gulfstream3_type), 
				 getResources().getString(R.string.gulfstream3_propulsion), 
				 getResources().getString(R.string.gulfstream3_performance), 
				 getResources().getString(R.string.gulfstream3_size), 
				 getResources().getString(R.string.gulfstream3_crew), 
				 getResources().getString(R.string.gulfstream3_sensors), 
				 getResources().getString(R.string.gulfstream3_armament), 
				 getResources().getString(R.string.gulfstream3_current), 
				 getResources().getString(R.string.gulfstream3_mission), 
				 "gulfstream3",
				 getResources().getString(R.string.gulfstream3_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.gulfstreamv_function), 
				 getResources().getString(R.string.gulfstreamv_type), 
				 getResources().getString(R.string.gulfstreamv_propulsion), 
				 getResources().getString(R.string.gulfstreamv_performance), 
				 getResources().getString(R.string.gulfstreamv_size), 
				 getResources().getString(R.string.gulfstreamv_crew), 
				 getResources().getString(R.string.gulfstreamv_sensors), 
				 getResources().getString(R.string.gulfstreamv_armament), 
				 getResources().getString(R.string.gulfstreamv_current), 
				 getResources().getString(R.string.gulfstreamv_mission), 
				 "gulfstreamv",
				 getResources().getString(R.string.gulfstreamv_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.clipper_function), 
				 getResources().getString(R.string.clipper_type), 
				 getResources().getString(R.string.clipper_propulsion), 
				 getResources().getString(R.string.clipper_performance), 
				 getResources().getString(R.string.clipper_size), 
				 getResources().getString(R.string.clipper_crew), 
				 getResources().getString(R.string.clipper_sensors), 
				 getResources().getString(R.string.clipper_armament), 
				 getResources().getString(R.string.clipper_current), 
				 getResources().getString(R.string.clipper_mission), 
				 "clipper",
				 getResources().getString(R.string.clipper_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.hercules_function), 
				 getResources().getString(R.string.hercules_type), 
				 getResources().getString(R.string.hercules_propulsion), 
				 getResources().getString(R.string.hercules_performance), 
				 getResources().getString(R.string.hercules_size), 
				 getResources().getString(R.string.hercules_crew), 
				 getResources().getString(R.string.hercules_sensors), 
				 getResources().getString(R.string.hercules_armament), 
				 getResources().getString(R.string.hercules_current), 
				 getResources().getString(R.string.hercules_mission), 
				 "hercules",
				 getResources().getString(R.string.hercules_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.sabreliner_function), 
				 getResources().getString(R.string.sabreliner_type), 
				 getResources().getString(R.string.sabreliner_propulsion), 
				 getResources().getString(R.string.sabreliner_performance), 
				 getResources().getString(R.string.sabreliner_size), 
				 getResources().getString(R.string.sabreliner_crew), 
				 getResources().getString(R.string.sabreliner_sensors), 
				 getResources().getString(R.string.sabreliner_armament), 
				 getResources().getString(R.string.sabreliner_current), 
				 getResources().getString(R.string.sabreliner_mission), 
				 "sabreliner",
				 getResources().getString(R.string.sabreliner_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.mercury_function), 
				 getResources().getString(R.string.mercury_type), 
				 getResources().getString(R.string.mercury_propulsion), 
				 getResources().getString(R.string.mercury_performance), 
				 getResources().getString(R.string.mercury_size), 
				 getResources().getString(R.string.mercury_crew), 
				 getResources().getString(R.string.mercury_sensors), 
				 getResources().getString(R.string.mercury_armament), 
				 getResources().getString(R.string.mercury_current), 
				 getResources().getString(R.string.mercury_mission), 
				 "mercury",
				 getResources().getString(R.string.mercury_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.growler_function), 
				 getResources().getString(R.string.growler_type), 
				 getResources().getString(R.string.growler_propulsion), 
				 getResources().getString(R.string.growler_performance), 
				 getResources().getString(R.string.growler_size), 
				 getResources().getString(R.string.growler_crew), 
				 getResources().getString(R.string.growler_sensors), 
				 getResources().getString(R.string.growler_armament), 
				 getResources().getString(R.string.growler_current), 
				 getResources().getString(R.string.growler_mission), 
				 "growler",
				 getResources().getString(R.string.growler_link));

		 mDbAdapter.createAircraft(
				 getResources().getString(R.string.aries2_function), 
				 getResources().getString(R.string.aries2_type), 
				 getResources().getString(R.string.aries2_propulsion), 
				 getResources().getString(R.string.aries2_performance), 
				 getResources().getString(R.string.aries2_size), 
				 getResources().getString(R.string.aries2_crew), 
				 getResources().getString(R.string.aries2_sensors), 
				 getResources().getString(R.string.aries2_armament), 
				 getResources().getString(R.string.aries2_current), 
				 getResources().getString(R.string.aries2_mission), 
				 "aries2",
				 getResources().getString(R.string.aries2_link));
		
		
		
		
	 }
	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}