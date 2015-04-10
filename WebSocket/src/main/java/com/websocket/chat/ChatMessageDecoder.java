package com.websocket.chat;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.websocket.chat.pojo.ChatMessage;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage>{

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public ChatMessage decode(final String message) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		
		JsonReader jr = Json.createReader(new StringReader(message));
		JsonObject json = jr.readObject();
		
		chatMessage.setMessage(json.getString("message"));
		chatMessage.setSender(json.getString("sender"));
		chatMessage.setReceived(new Date());
		
		return chatMessage;
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}
}
