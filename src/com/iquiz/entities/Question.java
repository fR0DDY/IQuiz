package com.iquiz.entities;

import java.util.ArrayList;
import java.util.List;

public class Question {
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	private  List<String> options = new ArrayList<String>(4);
	private String answer;
	private  String question;
	
}
