package com.andrios.fleetknowledge.Controllers;

import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Adapters.MyViewBinder;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.andrios.fleetknowledge.Models.Question;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class RecruitersQuestionsController extends Activity {
	

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ListView listView;
	SimpleCursorAdapter notes;
	ArrayList<Question> questionList;
	Cursor cursor;
	Button newBTN;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recruitersquestionsview);
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
		newBTN = (Button) findViewById(R.id.recruiterQuestionsNewBTN);
		listView = (ListView) findViewById(R.id.recruiterQuestionsListview);
		fillData();
	}

	private void setOnClickListeners() {
		newBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), RecruitersQuestionsDetailsController.class);
		    	//Activity returns a result id called with startActivityForResult
		    	startActivityForResult(i, ACTIVITY_CREATE);
				
			}
			
		});
		
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View v, int id,
					long arg3) {
				Cursor info = mDbAdapter.fetchQuestion(id + 1);
				startManagingCursor(info);
				String question = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_QUESTION));
				String answer = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_ANSWER));
				int isAsked = info.getInt(info.getColumnIndex(AndriosDbAdapter.KEY_ASKED));

				ImageView img = (ImageView) v.findViewById(R.id.question_list_item_askedIMG);
				if(isAsked == 0){
					isAsked = 1;
					img.setImageResource(R.drawable.icon_checked);
				}else{
					isAsked = 0;
					img.setImageResource(R.drawable.icon_unchecked);
				}
				mDbAdapter.updateQuestion(id + 1, question, isAsked, answer);
				
			}
			
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){

			

			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int id, long arg3) {
				Intent i = new Intent(v.getContext(), RecruitersQuestionsDetailsController.class);
		    	i.putExtra(AndriosDbAdapter.KEY_ROWID, id+1);
		    	//Activity returns a result id called with startActivityForResult
		    	startActivityForResult(i, ACTIVITY_EDIT);
				return false;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllQuestions();
	    	startManagingCursor(cursor);
	    	
	    	if(cursor.getCount() < 5){
	    		buildBaseQuestions();
	    		fillData();
	    		
	    	}else{
	    		String[] from = new String[] {AndriosDbAdapter.KEY_QUESTION, AndriosDbAdapter.KEY_ASKED};
		    	int[] to = new int[] {R.id.question_list_item_questionLBL, R.id.question_list_item_askedIMG};
		    	//String[] categories = new String[]{CrisisDbAdapter.KEY_CATEGORY};
		    	/*
		    	 * COME BACK HERE
		    	 */
		    	//TODO COME BACK HERE
		    	notes = new SimpleCursorAdapter(this, R.layout.list_item_question, cursor, from, to);
		    	notes.setViewBinder(new MyViewBinder());
		    	listView.setAdapter(notes);
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
	 
	 private void buildBaseQuestions(){
		 mDbAdapter.createQuestion("How long do I have to enlist for? What's the minimum commitment? ", 0, "");
		 mDbAdapter.createQuestion("Am I eligible for any special enlistment programs or bonuses?", 0, "");
		 mDbAdapter.createQuestion("What do I have to score on the Armed Services Vocational Aptitude Battery (ASVAB) test to qualify?", 0, "");
		 mDbAdapter.createQuestion("What are the major differences in pay, benefits and job opportunities between services? ", 0, "");
		 mDbAdapter.createQuestion("Do you have films or literature about military life and particular jobs? ", 0, "");
		 mDbAdapter.createQuestion("How long is basic training? Where is it? What is it like?", 0, "");
		 mDbAdapter.createQuestion("What physical fitness requirements must I meet to enter the military and succeed in basic training?", 0, "");
		 mDbAdapter.createQuestion("What jobs are available? ", 0, "");
		 mDbAdapter.createQuestion("What are the possibilities for remote or overseas duty stations?", 0, "");
		 mDbAdapter.createQuestion("What are the training and advancement opportunities for jobs that I'm eligible for?", 0, "");
		 mDbAdapter.createQuestion("What would pay be like?", 0, "");
		 mDbAdapter.createQuestion("Do I get paid while in training?", 0, "");
		 mDbAdapter.createQuestion("How much money can I get for college?", 0, "");
		 mDbAdapter.createQuestion("Can I take college courses or other training programs while in the military?", 0, "");
		 mDbAdapter.createQuestion("Are there any upcoming military events in the area, such as airshows, fleet weeks, etc.? ", 0, "");
		 mDbAdapter.createQuestion("Can a friend and I go to basic training together?", 0, "");
		 mDbAdapter.createQuestion("What are the haircut or other appearance standards that will apply to me?", 0, "");
		 mDbAdapter.createQuestion("What's the delayed entry program?", 0, "");
		 mDbAdapter.createQuestion("What are the next steps?", 0, "");
		 mDbAdapter.createQuestion("How can I get more information?", 1, "");
	 }
	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}