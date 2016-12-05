package com.distributedsystems.main;

import com.distributedsystems.obj.Message;
import com.distributedsystems.obj.Node;

import java.util.Map;
import java.util.HashMap;

public class BullyElection
{			
	static Object[][] objects = new Object[][]
			{
		{1, new Node(1, true)}, {2, new Node(2, true)}, {4, new Node(4, true)}, {0, new Node(0, true)},
		{7, new Node(7, true)}, {3, new Node(3, true)}, {6, new Node(6, true)}, {5, new Node(5, true)}
			};
	public static Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	
	public static void main(String[] args)
	{	
		for (Object[] obj : objects) {
			nodes.put((int)obj[0], (Node)obj[1]);
		}
		for(int id : nodes.keySet()) {
			Thread thread = new Thread(nodes.get(id));
			nodes.get(id).setState(nodes.get(id).getNormalState());
			thread.start();
		}
		
		//at first node #7 is coordinator
		nodes.get(7).setState(nodes.get(7).getCoordinatorState());
		
		//then node #0 sends check message to everyone
		for (Node remoteNode : nodes.values()) {
			nodes.get(0).sendMessage(0, remoteNode.getId(), Message.NORMAL_MESSAGE);
			//till now everything is ok
		}
		
		//then the coordinator goes down
		nodes.get(7).setState(nodes.get(7).getDeadState());
		//new coordinator must be choosen
		//who will be the new coordinator?!
		//just follow us!
		//<<Bully Algorithm!>> in 2016 December, in iMAX 3D
		//get the tickets now!!!
	}
}
