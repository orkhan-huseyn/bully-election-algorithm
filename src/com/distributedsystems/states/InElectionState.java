package com.distributedsystems.states;

import com.distributedsystems.main.BullyElection;
import com.distributedsystems.obj.Message;
import com.distributedsystems.obj.Node;

public class InElectionState implements State {
	Node node;

	public InElectionState(Node node) {
		this.node = node;
	}

	public void handleMessage() {
		int ok = 0;
		for (int remoteId : BullyElection.nodes.keySet()) {
			if (remoteId > node.getId()) {
				if (BullyElection.nodes.get(remoteId).isOnline()) {
					node.sendMessage(node.getId(), remoteId, Message.ELECTION_MESSAGE);
					ok++;
				} else {
					node.setState(node.getDeadState());
				}
			}
		}
		if (ok == 0) {
			node.setState(node.getCoordinatorState());
			System.out.println("Node [" + node.getId() + "] is new COORDINATOR!");
		} else {
			node.setState(node.getWaitingState());
		}
	}

}
