package com.websocket.chat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.websocket.chat.pojo.ChatMessage;


@ServerEndpoint(value="/chat/{room}", decoders=ChatMessageDecoder.class, encoders=ChatMessageEncoder.class)
public class ChatEndPoint {
	
	private final Logger log = Logger.getLogger(this.getClass().getName());

	@OnOpen
	public void open(final Session session,  @PathParam("room") final String room){
		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("room", room);
	}
	
	@OnMessage
	public void onMessage(final Session session, final ChatMessage message){
		String room = (String)session.getUserProperties().get("room");
		try {
			for(Session s : session.getOpenSessions()){
				if(s.isOpen()){
					if(room.equals(s.getUserProperties().get("room"))){
							s.getBasicRemote().sendObject(message);
					}
				}
			}
		} catch (IOException | EncodeException e) {
			log.log(Level.WARNING, "onMessage failed", e);
		}
	}
	
	@OnClose
	public void onClose(Session session, CloseReason reason){
		log.info("session in room: " + session.getUserProperties().get("room") +" is closed, reason : "+ reason.getReasonPhrase());
	}
	
	@OnError
	public void onError(Session session, Throwable execption){
		log.info("error session in room: " + session.getUserProperties().get("room") +" detail: "+ execption.getStackTrace());
	}
}
