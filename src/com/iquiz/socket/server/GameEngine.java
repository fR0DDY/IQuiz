package com.iquiz.socket.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.iquiz.entities.Match;
import com.iquiz.socket.messages.MessageCode;
import com.iquiz.socket.messages.SocketMessage;

public class GameEngine {

	private  Map<String, Match> matchCache = new HashMap<String, Match>();
	
	private GameEngine() {
		if (InnerClass._instance != null)
			throw new RuntimeException();
	}
	
	public void startGame(Session s,SocketMessage socketMessage){
		
		String playerName = socketMessage.getMessage();
		Match match = GameEngine.getEngine().getMatchCache().get(socketMessage.getMatchId());
		match.getPlayerByName(playerName).setReady(true);
		if(match.canBeStarted()){
			Session s2 = SessionHelper.getOpponentSession(s, match.getId());
			SocketMessage sm = new SocketMessage();
			sm.setMessageCode(MessageCode.StartGame);
			sm.setMatchId(match.getId());
			try {
				SessionHelper.broadcastToMatchPlayers(s,s2, sm, sm);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
	}

	public static GameEngine getEngine() {
		return InnerClass._instance;
	}

	public Map<String, Match> getMatchCache() {
		return this.matchCache;
	}

	public void setMatchCache(Map<String, Match> matchCache) {
		this.matchCache = matchCache;
	}

	public static class InnerClass {
		public static GameEngine _instance = new GameEngine();
	}
}
