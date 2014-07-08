package com.iquiz.socket.messages;

import java.util.List;

import com.iquiz.entities.Question;

public class SocketMessage {
	private String message;
	private List<Question> questions;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
