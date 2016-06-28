package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.Ball;
import Common.UserInfo;

public class Player extends Thread {

	private Socket socket;
	private String name;

	Controller controller;
	private double speed;
	double x, y;
	ArrayList<Ball> balls = new ArrayList();

	public Player(Socket socket, String name, Controller controller) {
		this.socket = socket;
		this.name = name;
		this.controller = controller;
		controller.players.add(this);
		
		this.x = Math.random() * controller.getMapWidth();
		this.y = Math.random() * controller.getMapHeight();
		
		Ball b = new Ball(x, y, 50, "M");
		balls.add(b);
	}

	public void run() {
		try {
			int cnt = 1;
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				output.writeObject(controller.scoreBalls);
				sleep(10);
			}
		} catch (InterruptedException | IOException e) {

		}
	}
}
