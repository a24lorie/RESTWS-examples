package com.websocket.chat;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.websocket.chat.pojo.ChatMessage;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>{

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
	}
		
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public String encode(ChatMessage message) throws EncodeException {
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("message", message.getMessage());
		job.add("sender", message.getSender());
		job.add("received", message.getReceived().toString());
		
		return job.build().toString();
	}
}
