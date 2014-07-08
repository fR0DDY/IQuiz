package com.iquiz.socket.encoders;


import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.iquiz.socket.messages.SocketMessage;

public class SocketMessageEncoder implements Encoder.Text<SocketMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final SocketMessage chatMessage) throws EncodeException {
		return Json.createObjectBuilder()
				.add("message", chatMessage.getMessage())
				.add("questions", chatMessage.getQuestions())
				.toString();
	}
}
