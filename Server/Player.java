package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Common.Ball;
import Common.Point;
import Common.UserInfo;

public class Player {

	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String name;
	private Socket socket;

	Controller controller;
	private double speed;
	double x, y;
	int mouseX, mouseY;
	ArrayList<Ball> balls = new ArrayList();
	private UserInfo userInfo;

	Timer move;
	Timer send;
	Timer read;

	public Player(Socket socket, ObjectInputStream input, ObjectOutputStream output, String name,
			Controller controller) {
		this.socket = socket;
		this.input = input;
		this.output = output;
		this.name = name;
		this.controller = controller;
		controller.players.add(this);

		userInfo = new UserInfo(name);

		this.x = Math.random() * controller.getMapWidth();
		this.y = Math.random() * controller.getMapHeight();

		Ball b = new Ball(x, y, 50, name);
		balls.add(b);

		read();
		send();
		move();
	}

	public void move() {
		move = new Timer(10, new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				for (Ball ball : balls) {
					ball.setX(mouseX);
					ball.setY(mouseY);
				}
			}
		});
		move.start();
	}

	public void send() {
		send = new Timer(70, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int cnt = 1;

					output.reset();
					output.writeObject(controller.getAllBalls());
					output.writeObject(playersList());

//					PrintWriter writer;
//					try {
//						writer = new PrintWriter("serversend.txt", "UTF-8");
//						writer.println(cnt++);
//						writer.close();
//					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
//						e1.printStackTrace();
//					}

				} catch (Exception e1) {

				}
			}
		});
	}

	public void read() {
		read = new Timer(70, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int cnt = 1;

					Object point = input.readObject();
					System.out.println(point);
					Point pointer = (Point) point;
					mouseX = pointer.x;
					mouseY = pointer.y;
					System.out.println(pointer.x+" "+pointer.y);

//					PrintWriter writer;
//					try {
//						writer = new PrintWriter("serverread.txt", "UTF-8");
//						writer.println(cnt++);
//						writer.close();
//					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
//						e1.printStackTrace();
//					}

				} catch (Exception e1) {

				}
			}
		});
	}

	private ArrayList<UserInfo> playersList() {
		ArrayList usersInfo = new ArrayList();
		for (Player player : controller.players)
			usersInfo.add(player.userInfo);
		return usersInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	private int getScore() {
		int score = 0;
		for (Ball ball : balls)
			score += ball.getRadius();
		return score;
	}
}
