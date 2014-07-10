package com.iquiz.socket.server;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.iquiz.socket.messages.SocketMessage;

public class SessionHelper {

	public static void broadcastToMatchPlayers(Session session1,Session session2,SocketMessage sm1, SocketMessage sm2) throws IOException, EncodeException {
//		Session oppSession = getOpponentSession(session,matchId);
		session1.getBasicRemote().sendObject(sm1);
		session2.getBasicRemote().sendObject(sm2);
	}

	public static Session getOpponentSession(Session session, String matchId) {
		String userId1 = (String) session.getUserProperties().get("userid");
		String userId2 = GameEngine.getEngine().getMatchCache().get(matchId)
				.getOponentPlayer(userId1).getName();
		for (Session s : session.getOpenSessions()) {
			if (s.getUserProperties().get("userid").equals(userId2)) {
				System.out.println("Got userid2 " + userId2);
				return s;
			}
		}
		return null;
	}
	

}
