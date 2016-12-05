package com.distributedsystems.obj;

import java.util.LinkedList;

import com.distributedsystems.states.*;
import com.distributedsystems.main.BullyElection;

public class Node implements Runnable {
	private int id;
	private boolean online;
	private boolean coordinator;
	private LinkedList<Message> queue;
	private int coordinatorId;

	State normalState;
	State inElectionState;
	State waitingState;
	State coordinatorState;
	State deadState;

	State state = normalState;

	public Node() {
	}

	public Node(int id, boolean online) {
		queue = new LinkedList<Message>();

		normalState = new NormalState(this);
		inElectionState = new InElectionState(this);
		waitingState = new WaitingState(this);
		coordinatorState = new CoordinatorState(this);
		deadState = new DeadState(this);

		this.setId(id);
		this.isOnline(online);
		setState(this.getNormalState());
	}

	public void run() {
		while (true) {
			state.handleMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(int senderId, int receiverId, String messageText) {
		Message message = new Message(senderId, receiverId, messageText);
		System.out.println(message.toString());
		BullyElection.nodes.get(receiverId).getQueue().add(message);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void isOnline(boolean online) {
		this.online = online;
	}

	public boolean isOnline() {
		return this.online;
	}

	public LinkedList<Message> getQueue() {
		return queue;
	}

	public void setQueue(LinkedList<Message> queue) {
		this.queue = queue;
	}

	public boolean isCoordinator() {
		return coordinator;
	}

	public void setCoordinator(boolean coordinator) {
		this.coordinator = coordinator;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getNormalState() {
		return normalState;
	}

	public void setNormalState(State normalState) {
		this.normalState = normalState;
	}

	public State getInElectionState() {
		return inElectionState;
	}

	public void setInElectionState(State inElectionState) {
		this.inElectionState = inElectionState;
	}

	public State getWaitingState() {
		return waitingState;
	}

	public void setWaitingState(State waitingState) {
		this.waitingState = waitingState;
	}

	public State getCoordinatorState() {
		return coordinatorState;
	}

	public void setCoordinatorState(State coordinatorState) {
		this.coordinatorState = coordinatorState;
	}

	public State getDeadState() {
		return deadState;
	}

	public void setDeadState(State deadState) {
		this.deadState = deadState;
	}

	public int getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(int coordinatorId) {
		this.coordinatorId = coordinatorId;
	}
}