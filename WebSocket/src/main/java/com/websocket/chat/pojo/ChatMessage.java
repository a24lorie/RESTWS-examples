package com.websocket.chat.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cchaponan
 *
 */
public class ChatMessage implements Serializable{
	
	private static final long serialVersionUID = -5251467687111661947L;
	
	private String message;
	private String sender;
	private Date received;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getReceived() {
		return received;
	}
	public void setReceived(Date received) {
		this.received = received;
	}
	
	@Override
	public String toString() {
		return "ChatMessage [message=" + message + ", sender=" + sender
				+ ", received=" + received + "]";
	}
}
