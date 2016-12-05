package com.distributedsystems.states;

import com.distributedsystems.obj.*;

public class DeadState implements State {
	
	Node node;
	
	public DeadState(Node node) {
		this.node = node;
	}
	
	public void handleMessage() {
		node.isOnline(false);
		if(!node.getQueue().isEmpty()) {
			Message message = node.getQueue().getFirst();
			System.out.println(message.toString());
			node.getQueue().removeFirst();
			
			node.sendMessage(node.getId(), message.getSenderId(), Message.DEAD_MESSAGE);
		}
	}

}
