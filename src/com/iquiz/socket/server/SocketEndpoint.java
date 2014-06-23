package com.iquiz.socket.server;

import java.util.logging.Logger;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/iquiz")
public class SocketEndpoint {
	private final Logger log = Logger.getLogger(getClass().getName());
 
	@OnOpen
	public void open(final Session session) {
		System.out.println("Opening...");
	}
 
	@OnMessage
	public void onMessage(final Session session, final String chatMessage) {
		System.out.println("chatMessage: " + chatMessage);
	}
}
