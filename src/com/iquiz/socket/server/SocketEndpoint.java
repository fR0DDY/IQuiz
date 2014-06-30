package com.iquiz.socket.server;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/iquiz/{userid}")
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
				s1.getBasicRemote().sendText("You are plaing with " + userId2);
				session.getBasicRemote().sendText("You are plaing with " + userId1);
				return;
			}
			queue.add(userId2);
		}
	}

	@OnMessage
	public void onMessage(final Session session, final String chatMessage) {
		System.out.println("chatMessage: " + chatMessage);

	}
}
