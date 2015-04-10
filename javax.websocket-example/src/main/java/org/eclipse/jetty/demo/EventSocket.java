package org.eclipse.jetty.demo;

import java.io.IOException;
import java.util.logging.Level;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ClientEndpoint
@ServerEndpoint(value="/events/{room}")
public class EventSocket
{
    @OnOpen
    public void onWebSocketConnect(final Session session,  @PathParam("room") final String room)
    {
	session.getUserProperties().put("room", room);
	System.out.println("Socket Connected: " + session);
    }

    @OnMessage
    public void onWebSocketText(final Session session, String message)
    {
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
	    System.out.println("onMessage failed");
	}
	System.out.println("Received TEXT message: " + message);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason)
    {
	System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause)
    {
	cause.printStackTrace(System.err);
    }
}
