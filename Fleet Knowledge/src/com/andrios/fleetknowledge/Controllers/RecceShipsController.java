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
				 "arleigh_burke");
		 
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
				 "ticonderoga");

		
	 }
	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}