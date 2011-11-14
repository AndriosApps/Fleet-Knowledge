package com.andrios.fleetknowledge.Controllers;

import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Adapters.MyCreedsViewBinder;
import com.andrios.fleetknowledge.Adapters.MyViewBinder;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.andrios.fleetknowledge.Models.Question;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class CreedsController extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ListView listView;
	SimpleCursorAdapter notes;
	ArrayList<Question> questionList;
	Cursor cursor;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creedsview);
        mDbAdapter = new AndriosDbAdapter(this);
		mDbAdapter.open();
		
//		long id = mDbAdapter.createQuestion("What is a Test Question?", 0);
//		System.out.println("ID " + id);
//		Cursor info = mDbAdapter.fetchQuestion(id);
//		System.out.println(info.getString(info.getColumnIndexOrThrow(AndriosDbAdapter.KEY_QUESTION)));
//		

		setConnections();
		setOnClickListeners();
    }

	

	private void setConnections() {
		listView = (ListView) findViewById(R.id.creedsListView);
		fillData();
	}

	private void setOnClickListeners() {
		
			
		
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View v, int id,
					long arg3) {

				Intent i = new Intent(v.getContext(), CreedsDetailsController.class);
		    	i.putExtra(AndriosDbAdapter.KEY_ROWID, id+1);
		    	//Activity returns a result id called with startActivityForResult
		    	startActivityForResult(i, ACTIVITY_EDIT);
				
			}
			
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){

			

			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int id, long arg3) {
				
				Cursor info = mDbAdapter.fetchCreed(id + 1);
				startManagingCursor(info);
				String title = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_TITLE));
				String body = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_BODY));
				int isKnown = info.getInt(info.getColumnIndex(AndriosDbAdapter.KEY_KNOWN));

				ImageView img = (ImageView) v.findViewById(R.id.question_list_item_askedIMG);
				if(isKnown == 0){
					isKnown = 1;
					img.setImageResource(R.drawable.icon_checked);
				}else{
					isKnown = 0;
					img.setImageResource(R.drawable.icon_unchecked);
				}
				mDbAdapter.updateCreed(id + 1, title, body, isKnown);
				return true;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllCreeds();
	    	startManagingCursor(cursor);
	    	
	    	
	    		String[] from = new String[] {AndriosDbAdapter.KEY_TITLE, AndriosDbAdapter.KEY_KNOWN};
		    	int[] to = new int[] {R.id.question_list_item_questionLBL, R.id.question_list_item_askedIMG};
		    	//String[] categories = new String[]{CrisisDbAdapter.KEY_CATEGORY};
		    	/*
		    	 * COME BACK HERE
		    	 */
		    	//TODO COME BACK HERE
		    	notes = new SimpleCursorAdapter(this, R.layout.list_item_question, cursor, from, to);
		    	notes.setViewBinder(new MyCreedsViewBinder());
		    	listView.setAdapter(notes);
	    	
	    	
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