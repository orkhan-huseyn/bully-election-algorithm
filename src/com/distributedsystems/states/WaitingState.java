package com.distributedsystems.states;

import com.distributedsystems.obj.Message;
import com.distributedsystems.obj.Node;

public class WaitingState implements State {

	Node node;
	
	public WaitingState(Node node) {
		this.node = node;
	}
	
	public void handleMessage() {
		if(!node.getQueue().isEmpty()) {
			Message message = node.getQueue().getFirst();
			System.out.println(message.toString());
			node.getQueue().removeFirst();
			
			if(message.getMessage().equals(Message.OK_MESSAGE)) {
				node.setState(node.getNormalState());
			}
		}
	}

}
