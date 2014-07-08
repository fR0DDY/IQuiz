package com.iquiz.socket.decoders;


import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.iquiz.socket.messages.SocketMessage;

public class SocketMessageDecoder implements Decoder.Text<SocketMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public SocketMessage decode(final String textMessage) throws DecodeException {
		SocketMessage chatMessage = new SocketMessage();
		JsonObject obj = Json.createReader(new StringReader(textMessage))
				.readObject();
		chatMessage.setMessage(obj.getString("message"));
		chatMessage.setSender(obj.getString("sender"));
		chatMessage.setReceived(new Date());
		return chatMessage;
	}

	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
