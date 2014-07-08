package com.iquiz.socket.decoders;


import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iquiz.socket.messages.SocketMessage;

public class SocketMessageDecoder implements Decoder.Text<SocketMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public SocketMessage decode(final String socketMessage) throws DecodeException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(socketMessage, SocketMessage.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
