package com.distributedsystems.obj;

public class Message 
{
	private int senderId;
	private int receiverId;
	private String message;
	
	public static final String OK_MESSAGE = "OK";
	public static final String ELECTION_MESSAGE = "ELECTION";
	public static final String COORDINATOR_MESSAGE = "COORDINATOR";
	
	public static final String NORMAL_MESSAGE = "CHECK";
	public static final String ANSWER_MESSAGE = "ALIVE";
	public static final String DEAD_MESSAGE = "DEAD";
	
	public Message(int senderId, int receiverId, String message)
	{
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
	}
	
	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	
	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "Node [" + senderId + "] sent " + message + " to [" + receiverId + "]";
	}
}
