package com.iquiz.entities;

import java.util.HashMap;
import java.util.Map;

public class PlayerResponse {
	private String questionCode;	
	private Map<String,Integer> pointTable = new HashMap<String,Integer>(2);
	private String answer;
	
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public Map<String,Integer> getPointTable() {
		return pointTable;
	}
	public void setPointTable(Map<String,Integer> pointTable) {
		this.pointTable = pointTable;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
