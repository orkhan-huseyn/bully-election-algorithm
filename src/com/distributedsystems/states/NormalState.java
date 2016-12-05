package com.distributedsystems.states;

import com.distributedsystems.obj.*;

public class NormalState implements State {

	Node node;

	public NormalState(Node node) {
		this.node = node;
	}

	public void handleMessage() {
		if (!node.getQueue().isEmpty()) {

			Message message = node.getQueue().getFirst();
			System.out.println(message.toString());
			node.getQueue().removeFirst();

			if (message.getMessage().equals(Message.NORMAL_MESSAGE)) {
				if (node.isOnline()) {
					node.sendMessage(node.getId(), message.getSenderId(), Message.ANSWER_MESSAGE);
				} else {
					node.setState(node.getDeadState());
				}
			} else if (message.getMessage().equals(Message.ANSWER_MESSAGE)) {
				// life is good
			} else if (message.getMessage().equals(Message.DEAD_MESSAGE)) {
				
				node.setState(node.getInElectionState());
				
			} else if (message.getMessage().equals(Message.ELECTION_MESSAGE)) {
				
				node.sendMessage(node.getId(), message.getSenderId(), Message.OK_MESSAGE);
				node.setState(node.getInElectionState());
				
			} else if (message.getMessage().equals(Message.COORDINATOR_MESSAGE)) {
				node.setCoordinatorId(message.getSenderId());
			}
		}
	}

}
