package com.andrios.fleetknowledge.Controllers;

import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Adapters.ExpandableShipAdapter;
import com.andrios.fleetknowledge.Adapters.MyCreedsViewBinder;
import com.andrios.fleetknowledge.Adapters.MyViewBinder;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.andrios.fleetknowledge.Models.Question;
import com.andrios.fleetknowledge.Models.Ship;
import com.andrios.fleetknowledge.R.layout;

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

public class RecceSubsController extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ExpandableListView listView;
	ExpandableShipAdapter listAdapter;
	ArrayList<Ship> subList;
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
		listAdapter = new ExpandableShipAdapter(this, new ArrayList<String>(), new ArrayList<ArrayList<Ship>>());
		listView.setAdapter(listAdapter);
		addSubsToAdapter();
	}
	
	private void addSubsToAdapter(){
		
		for(int i = 0; i < subList.size(); i++){
			listAdapter.addItem(subList.remove(0));
			i--;
		}
	}

	private void setOnClickListeners() {
		listView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(v.getContext(), RecceSubsDetailsController.class);
				intent.putExtra("sub", (Ship) listAdapter.getChild(groupPosition, childPosition));
				startActivity(intent);
				
				return false;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllSubs();
	    	startManagingCursor(cursor);
	    	
	    	if(cursor.getCount() < 1){
	    		buildBaseSubs();
	    		fillData();
	    		
	    	}else{
	    	
		    	cursor.moveToFirst();
		    	subList = new ArrayList<Ship>();
		        while (cursor.isAfterLast() == false) {
		            subList.add(new Ship(cursor, RecceSubsController.this));
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
	 
	 private void buildBaseSubs(){
		 final Resources r = Resources.getSystem();
		 mDbAdapter.createSub("Ballistic Missile Submarine (SSBN)", 
				 "Ohio Class", 
				 getResources().getString(R.string.ohio_dimensions), 
				 getResources().getString(R.string.ohio_crew), 

				 getResources().getString(R.string.ohio_ssbn_weapons), 
				 getResources().getString(R.string.ohio_performance), 
				 getResources().getString(R.string.ohio_propulsion),
				 getResources().getString(R.string.ohio_aircraft), 
				 getResources().getString(R.string.ohio_ew), 
				 getResources().getString(R.string.ohio_sensors),
				 getResources().getString(R.string.ohio_boats),     
				 getResources().getString(R.string.ohio_about),
				 "ohio",
				 "http://en.wikipedia.org/wiki/Ohio_class");
		 
		 mDbAdapter.createSub("Cruise Missile Submarine (SSGN)", 
				 "Ohio Class", 
				 getResources().getString(R.string.ohio_dimensions), 
				 getResources().getString(R.string.ohio_crew), 

				 getResources().getString(R.string.ohio_ssgn_weapons), 
				 getResources().getString(R.string.ohio_performance), 
				 getResources().getString(R.string.ohio_propulsion),
				 getResources().getString(R.string.ohio_aircraft), 
				 getResources().getString(R.string.ohio_ew), 
				 getResources().getString(R.string.ohio_sensors),
				 getResources().getString(R.string.ohio_boats),     
				 getResources().getString(R.string.ohio_about),
				 "ohio",
				 "http://en.wikipedia.org/wiki/Ohio_class");
		
		 mDbAdapter.createSub("Attack Submarine (SSN)", 
				 "Virginia Class", 
				 getResources().getString(R.string.virginia_dimensions), 
				 getResources().getString(R.string.virginia_crew), 

				 getResources().getString(R.string.virginia_weapons), 
				 getResources().getString(R.string.virginia_performance), 
				 getResources().getString(R.string.virginia_propulsion),
				 getResources().getString(R.string.virginia_aircraft), 
				 getResources().getString(R.string.virginia_ew), 
				 getResources().getString(R.string.virginia_sensors),
				 getResources().getString(R.string.virginia_boats),     
				 getResources().getString(R.string.virginia_about),
				 "virginia",
				 "http://en.wikipedia.org/wiki/Virginia_class_submarine");
		 
		 mDbAdapter.createSub("Attack Submarine (SSN)", 
				 "Seawolf Class", 
				 getResources().getString(R.string.seawolf_dimensions), 
				 getResources().getString(R.string.seawolf_crew), 

				 getResources().getString(R.string.seawolf_weapons), 
				 getResources().getString(R.string.seawolf_performance), 
				 getResources().getString(R.string.seawolf_propulsion),
				 getResources().getString(R.string.seawolf_aircraft), 
				 getResources().getString(R.string.seawolf_ew), 
				 getResources().getString(R.string.seawolf_sensors),
				 getResources().getString(R.string.seawolf_boats),     
				 getResources().getString(R.string.seawolf_about),
				 "seawolf",
				 "http://en.wikipedia.org/wiki/Seawolf_class_submarine");
		
		 mDbAdapter.createSub("Attack Submarine (SSN)", 
				 "Los Angeles Class", 
				 getResources().getString(R.string.los_angeles_dimensions), 
				 getResources().getString(R.string.los_angeles_crew), 

				 getResources().getString(R.string.los_angeles_weapons), 
				 getResources().getString(R.string.los_angeles_performance), 
				 getResources().getString(R.string.los_angeles_propulsion),
				 getResources().getString(R.string.los_angeles_aircraft), 
				 getResources().getString(R.string.los_angeles_ew), 
				 getResources().getString(R.string.los_angeles_sensors),
				 getResources().getString(R.string.los_angeles_boats),     
				 getResources().getString(R.string.los_angeles_about),
				 "los_angeles",
				 "http://en.wikipedia.org/wiki/Los_Angeles_class_submarine");
	 }
	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}