package com.iquiz.socket.encoders;


import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iquiz.socket.messages.SocketMessage;

public class SocketMessageEncoder implements Encoder.Text<SocketMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final SocketMessage socketMessage) throws EncodeException {
		System.out.println("From Encoder : "+ socketMessage);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(socketMessage);
		}catch (Exception e) {
			return null;
		}
	}
}
