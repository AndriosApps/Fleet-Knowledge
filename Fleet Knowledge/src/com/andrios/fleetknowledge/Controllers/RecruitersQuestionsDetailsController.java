package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RecruitersQuestionsDetailsController extends Activity {
	
	AndriosDbAdapter mDbAdapter;
	
	Cursor cursor;
	
	EditText questionTXT;
	EditText answerTXT;
	long mRowId;
	Button saveBTN;
	int isAsked;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recruitersquestionsdetailsview);
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
    }

	private void getExtras(){
		Intent intent = this.getIntent();
		mRowId = intent.getIntExtra(AndriosDbAdapter.KEY_ROWID, -1);
	}

	private void setConnections() {
		saveBTN = (Button) findViewById(R.id.recruitersQuestionsDetailsSaveBTN);
		questionTXT = (EditText) findViewById(R.id.recruiterQuestionsDetailsQuestionLBL);
		answerTXT = (EditText) findViewById(R.id.recruiterQuestionsDetailsAnswerLBL);
	}

	private void setOnClickListeners() {
		saveBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				saveState();
				setResult(RESULT_OK);
				finish();
				
			}
			
		});
		
	}
	
	 private void fillData(){
	    	
		 if(mRowId != -1){
				Cursor info = mDbAdapter.fetchQuestion(mRowId);
				startManagingCursor(info);
				String question = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_QUESTION));
				String answer = info.getString(info.getColumnIndex(AndriosDbAdapter.KEY_ANSWER));
				isAsked = info.getInt(info.getColumnIndex(AndriosDbAdapter.KEY_ASKED));
				questionTXT.setText(question);
				answerTXT.setText(answer);
			}
	    }
	 
	 private void saveState(){
			String question = questionTXT.getText().toString();
			String answer = answerTXT.getText().toString();
			
			if(mRowId == -1){
				long id = mDbAdapter.createQuestion(question, 0, answer);
				if(id > 0){
					mRowId = id;
				}
			}else {
				mDbAdapter.updateQuestion(mRowId, question, isAsked, answer);
			}
			
		}
	 
	 
	
}