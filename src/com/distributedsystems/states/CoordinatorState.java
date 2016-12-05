package com.distributedsystems.states;

import com.distributedsystems.obj.Message;
import com.distributedsystems.obj.Node;
import com.distributedsystems.main.*;

public class CoordinatorState implements State 
{
	Node node;
	
	public CoordinatorState(Node node){
		this.node = node;
	}
	
	public void handleMessage() 
	{
		node.setCoordinator(true);
		for (int remoteId : BullyElection.nodes.keySet()) {
			node.sendMessage(node.getId(), remoteId, Message.COORDINATOR_MESSAGE);
		}
	}

}
