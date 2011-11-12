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

public class RecceShipsController extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ExpandableListView listView;
	ExpandableShipAdapter listAdapter;
	ArrayList<Ship> shipList;
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
		addShipsToAdapter();
	}
	
	private void addShipsToAdapter(){
		
		for(int i = 0; i < shipList.size(); i++){
			listAdapter.addItem(shipList.remove(0));
			i--;
		}
	}

	private void setOnClickListeners() {
		listView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(v.getContext(), RecceShipsDetailsController.class);
				intent.putExtra("ship", (Ship) listAdapter.getChild(groupPosition, childPosition));
				startActivity(intent);
				
				return false;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllShips();
	    	startManagingCursor(cursor);
	    	
	    	if(cursor.getCount() < 1){
	    		buildBaseShips();
	    		fillData();
	    		
	    	}else{
	    	
		    	cursor.moveToFirst();
		    	shipList = new ArrayList<Ship>();
		        while (cursor.isAfterLast() == false) {
		            shipList.add(new Ship(cursor, RecceShipsController.this));
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
	 
	 private void buildBaseShips(){
		 final Resources r = Resources.getSystem();
		 mDbAdapter.createShip("Guided Missile Destroyer (DDG)", 
				 "Arleigh Burke Class", 
				 getResources().getString(R.string.arleigh_burke_dimensions), 
				 getResources().getString(R.string.arleigh_burke_crew), 

				 getResources().getString(R.string.arleigh_burke_weapons), 
				 getResources().getString(R.string.arleigh_burke_performance), 
				 getResources().getString(R.string.arleigh_burke_propulsion),
				 getResources().getString(R.string.arleigh_burke_aircraft), 
				 getResources().getString(R.string.arleigh_burke_ew), 
				 getResources().getString(R.string.arleigh_burke_sensors),
				 getResources().getString(R.string.arleigh_burke_boats),     
				 getResources().getString(R.string.arleigh_burke_about),
				 "arleigh_burke",    
				 "http://en.wikipedia.org/wiki/Arleigh_Burke_class_destroyer");
		 
		 mDbAdapter.createShip("Guided Missile Cruiser (CG)", 
				 "Ticonderoga Class", 
				 getResources().getString(R.string.ticonderoga_dimensions), 
				 getResources().getString(R.string.ticonderoga_crew), 

				 getResources().getString(R.string.ticonderoga_weapons), 
				 getResources().getString(R.string.ticonderoga_performance), 
				 getResources().getString(R.string.ticonderoga_propulsion),
				 getResources().getString(R.string.ticonderoga_aircraft), 
				 getResources().getString(R.string.ticonderoga_ew), 
				 getResources().getString(R.string.ticonderoga_sensors),
				 getResources().getString(R.string.ticonderoga_boats),     
				 getResources().getString(R.string.ticonderoga_about),
				 "ticonderoga",    
				 "http://en.wikipedia.org/wiki/Ticonderoga_class_cruiser");
		 
		 mDbAdapter.createShip("Aircraft Carrier - Nuclear (CVN)", 
				 "Enterprise Class", 
				 getResources().getString(R.string.enterprise_dimensions), 
				 getResources().getString(R.string.enterprise_crew), 

				 getResources().getString(R.string.enterprise_weapons), 
				 getResources().getString(R.string.enterprise_performance), 
				 getResources().getString(R.string.enterprise_propulsion),
				 getResources().getString(R.string.enterprise_aircraft), 
				 getResources().getString(R.string.enterprise_ew), 
				 getResources().getString(R.string.enterprise_sensors),
				 getResources().getString(R.string.enterprise_boats),     
				 getResources().getString(R.string.enterprise_about),
				 "enterprise",    
				 "http://en.wikipedia.org/wiki/Enterprise_class_aircraft_carrier");
		 
		 mDbAdapter.createShip("Aircraft Carrier - Nuclear (CVN)", 
				 "Nimitz Class", 
				 getResources().getString(R.string.nimitz_dimensions), 
				 getResources().getString(R.string.nimitz_crew), 

				 getResources().getString(R.string.nimitz_weapons), 
				 getResources().getString(R.string.nimitz_performance), 
				 getResources().getString(R.string.nimitz_propulsion),
				 getResources().getString(R.string.nimitz_aircraft), 
				 getResources().getString(R.string.nimitz_ew), 
				 getResources().getString(R.string.nimitz_sensors),
				 getResources().getString(R.string.nimitz_boats),     
				 getResources().getString(R.string.nimitz_about),
				 "nimitz",    
				 "http://en.wikipedia.org/wiki/Nimitz_class_aircraft_carrier");
		 
		 mDbAdapter.createShip("Aircraft Carrier - Nuclear (CVN)", 
				 "Ford Class", 
				 getResources().getString(R.string.ford_dimensions), 
				 getResources().getString(R.string.ford_crew), 

				 getResources().getString(R.string.ford_weapons), 
				 getResources().getString(R.string.ford_performance), 
				 getResources().getString(R.string.ford_propulsion),
				 getResources().getString(R.string.ford_aircraft), 
				 getResources().getString(R.string.ford_ew), 
				 getResources().getString(R.string.ford_sensors),
				 getResources().getString(R.string.ford_boats),     
				 getResources().getString(R.string.ford_about),
				 "ford",    
				 "http://en.wikipedia.org/wiki/Gerald_R._Ford_class_aircraft_carrier");
		 
		 mDbAdapter.createShip("Landing Helicopter Assault (LHA)", 
				 "Tarawa Class", 
				 getResources().getString(R.string.tarawa_dimensions), 
				 getResources().getString(R.string.tarawa_crew), 

				 getResources().getString(R.string.tarawa_weapons), 
				 getResources().getString(R.string.tarawa_performance), 
				 getResources().getString(R.string.tarawa_propulsion),
				 getResources().getString(R.string.tarawa_aircraft), 
				 getResources().getString(R.string.tarawa_ew), 
				 getResources().getString(R.string.tarawa_sensors),
				 getResources().getString(R.string.tarawa_boats),     
				 getResources().getString(R.string.tarawa_about),
				 "tarawa",    
				 "http://en.wikipedia.org/wiki/Tarawa_class_amphibious_assault_ship");

		 mDbAdapter.createShip("Landing Helicopter Assault (LHA)", 
				 "America Class", 
				 getResources().getString(R.string.america_dimensions), 
				 getResources().getString(R.string.america_crew), 

				 getResources().getString(R.string.america_weapons), 
				 getResources().getString(R.string.america_performance), 
				 getResources().getString(R.string.america_propulsion),
				 getResources().getString(R.string.america_aircraft), 
				 getResources().getString(R.string.america_ew), 
				 getResources().getString(R.string.america_sensors),
				 getResources().getString(R.string.america_boats),     
				 getResources().getString(R.string.america_about),
				 "america",    
				 "http://en.wikipedia.org/wiki/America_class_amphibious_assault_ship");

		 mDbAdapter.createShip("Landing Helicopter Dock (LHD)", 
				 "Wasp Class", 
				 getResources().getString(R.string.wasp_dimensions), 
				 getResources().getString(R.string.wasp_crew), 

				 getResources().getString(R.string.wasp_weapons), 
				 getResources().getString(R.string.wasp_performance), 
				 getResources().getString(R.string.wasp_propulsion),
				 getResources().getString(R.string.wasp_aircraft), 
				 getResources().getString(R.string.wasp_ew), 
				 getResources().getString(R.string.wasp_sensors),
				 getResources().getString(R.string.wasp_boats),     
				 getResources().getString(R.string.wasp_about),
				 "wasp",    
				 "http://en.wikipedia.org/wiki/Wasp_class_amphibious_assault_ship");

		 mDbAdapter.createShip("Amphibious Transport Dock (LPD)", 
				 "San Antonio Class", 
				 getResources().getString(R.string.san_antonio_dimensions), 
				 getResources().getString(R.string.san_antonio_crew), 

				 getResources().getString(R.string.san_antonio_weapons), 
				 getResources().getString(R.string.san_antonio_performance), 
				 getResources().getString(R.string.san_antonio_propulsion),
				 getResources().getString(R.string.san_antonio_aircraft), 
				 getResources().getString(R.string.san_antonio_ew), 
				 getResources().getString(R.string.san_antonio_sensors),
				 getResources().getString(R.string.san_antonio_boats),     
				 getResources().getString(R.string.san_antonio_about),
				 "san_antonio",    
				 "http://en.wikipedia.org/wiki/San_Antonio_class_amphibious_transport_dock");

		 mDbAdapter.createShip("Amphibious Transport Dock (LPD)", 
				 "Austin Class", 
				 getResources().getString(R.string.austin_dimensions), 
				 getResources().getString(R.string.austin_crew), 

				 getResources().getString(R.string.austin_weapons), 
				 getResources().getString(R.string.austin_performance), 
				 getResources().getString(R.string.austin_propulsion),
				 getResources().getString(R.string.austin_aircraft), 
				 getResources().getString(R.string.austin_ew), 
				 getResources().getString(R.string.austin_sensors),
				 getResources().getString(R.string.austin_boats),     
				 getResources().getString(R.string.austin_about),
				 "austin",    
				 "http://en.wikipedia.org/wiki/Austin_class_amphibious_transport_dock");

		 mDbAdapter.createShip("Dock Landing Ship (LSD)", 
				 "Whidbey Island Class", 
				 getResources().getString(R.string.whidbey_island_dimensions), 
				 getResources().getString(R.string.whidbey_island_crew), 

				 getResources().getString(R.string.whidbey_island_weapons), 
				 getResources().getString(R.string.whidbey_island_performance), 
				 getResources().getString(R.string.whidbey_island_propulsion),
				 getResources().getString(R.string.whidbey_island_aircraft), 
				 getResources().getString(R.string.whidbey_island_ew), 
				 getResources().getString(R.string.whidbey_island_sensors),
				 getResources().getString(R.string.whidbey_island_boats),     
				 getResources().getString(R.string.whidbey_island_about),
				 "whidbey_island",    
				 "http://en.wikipedia.org/wiki/Whidbey_Island_class_dock_landing_ship");

		 mDbAdapter.createShip("Dock Landing Ship (LSD)", 
				 "Harpers Ferry Class", 
				 getResources().getString(R.string.harpers_ferry_dimensions), 
				 getResources().getString(R.string.harpers_ferry_crew), 

				 getResources().getString(R.string.harpers_ferry_weapons), 
				 getResources().getString(R.string.harpers_ferry_performance), 
				 getResources().getString(R.string.harpers_ferry_propulsion),
				 getResources().getString(R.string.harpers_ferry_aircraft), 
				 getResources().getString(R.string.harpers_ferry_ew), 
				 getResources().getString(R.string.harpers_ferry_sensors),
				 getResources().getString(R.string.harpers_ferry_boats),     
				 getResources().getString(R.string.harpers_ferry_about),
				 "harpers_ferry",    
				 "http://en.wikipedia.org/wiki/Harpers_Ferry_class_dock_landing_ship");
	 }
	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}