package com.andrios.fleetknowledge.Models;

public class Question {

	String question;
	boolean isAsked;
	
	public Question(String question, boolean isAsked){
		this.question = question;
		this.isAsked = isAsked;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean isAsked() {
		return isAsked;
	}

	public void setAsked(boolean isAsked) {
		this.isAsked = isAsked;
	}
	
	
}
