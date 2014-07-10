package com.iquiz.socket.server;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.iquiz.entities.Match;
import com.iquiz.helpers.QuestionHelper;
import com.iquiz.socket.decoders.SocketMessageDecoder;
import com.iquiz.socket.encoders.SocketMessageEncoder;
import com.iquiz.socket.messages.MessageCode;
import com.iquiz.socket.messages.SocketMessage;

@ServerEndpoint(value = "/iquiz/{userid}", encoders = SocketMessageEncoder.class, decoders = SocketMessageDecoder.class)
public class SocketEndpoint {

	private final Logger log = Logger.getLogger(getClass().getName());
	static {
		System.out.println("Initializing");

	}
	static Deque<String> queue = new ArrayDeque<>();

	@OnOpen
	public void open(final Session session,
			@PathParam("userid") final String userid) throws IOException {
		System.out.println("Opening with userid " + userid);
		session.getUserProperties().put("userid", userid);
		queue.add(userid);
		checkmadi(session);
	}

	
	
	
	
	private void checkmadi(Session session) throws IOException {
		while (session.isOpen() && queue.size() > 1) {
			String userId1 = queue.poll();
			String userId2 = (String) session.getUserProperties().get("userid");
			queue.remove(userId2);
			System.out.println("userid1  is "+userId1);
			System.out.println("userid2  is "+userId2);
			boolean b1 = false;
			Session s1 = null;
			System.out.println(session.getOpenSessions().size());
			for (Session s : session.getOpenSessions()) {				
				if (s.getUserProperties().get("userid").equals(userId1)) {
					System.out.println("Got userid1 "+ userId1);
					b1 = true;
					s1 = s;
				}
			}
			if (b1) {
				SocketMessage msg = new SocketMessage();
				msg.setMessageCode(MessageCode.PlayerFound);
				msg.setQuestions(QuestionHelper.getRandomQuestions());
				String matchId = UUID.randomUUID().toString();
				msg.setMatchId(matchId);
				GameEngine.getEngine().getMatchCache().put(matchId, new Match(userId1,userId2,matchId));
				try {
					msg.setMessage(userId2);
					s1.getBasicRemote().sendObject(msg);
					msg.setMessage(userId1);
					session.getBasicRemote().sendObject(msg);
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					s1.getBasicRemote().sendText("Error on Encoding");
					session.getBasicRemote().sendText("Error on Encoding");
					e.printStackTrace();
				}
				return;
			}
			queue.add(userId2);
		}
	}


	@OnMessage
	public void onMessage(final Session session, final SocketMessage socketMessage) {
		System.out.println("chatMessage: " + socketMessage.toString());
		
	
		switch(socketMessage.getMessageCode()){
		case Error:
			break;
		case PlayerFound:
			break;
		case PlayerReady:
			System.out.println("Starting Game");			
			GameEngine.getEngine().startGame(session,socketMessage);
			break;
		case StartGame:
			break;
		default:
			break;
		}
		
	}
}
