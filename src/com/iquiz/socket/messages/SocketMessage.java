package com.iquiz.socket.messages;

import java.util.List;
import java.util.UUID;

import com.iquiz.entities.PlayerResponse;
import com.iquiz.entities.Question;

public class SocketMessage {
	private String message;
	private List<Question> questions;
	private MessageCode messageCode;
	private PlayerResponse playerResponse;
	private String matchId;

	public MessageCode getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(MessageCode messageCode) {
		this.messageCode = messageCode;
	}

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

	public PlayerResponse getPlayerResponse() {
		return playerResponse;
	}

	public void setPlayerResponse(PlayerResponse playerResponse) {
		this.playerResponse = playerResponse;
	}

	@Override
	public String toString() {
		return "SocketMessage [message=" + message + ", questions=" + questions
				+ ", messageCode=" + messageCode + ", playerResponse="
				+ playerResponse + "]";
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	
	

}
