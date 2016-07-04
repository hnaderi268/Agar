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
	private Socket socket;

	Controller controller;
	private double speed;
	double x, y;
	int mouseX, mouseY;
	ArrayList<Ball> balls = new ArrayList();
	public UserInfo userInfo;

	long lastRead;

	Timer move, send, read, scoreUpdater;

	public Player(Socket socket, ObjectInputStream input, ObjectOutputStream output, UserInfo userInfo,
			Controller controller) {
		this.socket = socket;
		this.input = input;
		this.output = output;
		this.controller = controller;
		controller.players.add(this);

		this.x = Math.random() * controller.getMapWidth();
		this.y = Math.random() * controller.getMapHeight();

		this.userInfo = userInfo;

		balls.add(new Ball(x, y, 50, userInfo.name, userInfo.imgAdress, userInfo.color));
		balls.add(new Ball(x - 20, y - 30, 50, userInfo.name, userInfo.imgAdress, userInfo.color));

		read();
		send();
		move();
		scoreUpdate();
		ballPlacer();
		ballMerger();
	}

	public void move() {
		// 10
		move = new Timer(10, new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				double difx = x;
				x += (getSpeed() / getSurface()) * (mouseX - 1440 / 2) / (Math
						.sqrt((mouseX - 1440 / 2) * (mouseX - 1440 / 2) + (mouseY - 1440 / 2) * (mouseY - 1440 / 2)));
				difx = x - difx;
				double dify = y;
				y += (getSpeed() / getSurface()) * (mouseY - 410)
						/ (Math.sqrt((mouseX - 410) * (mouseX - 410) + (mouseY - 410) * (mouseY - 410)));
				dify = y - dify;
				for (Ball ball : balls) {
					ball.setX(ball.getX() + difx);
					ball.setY(ball.getY() + dify);
				}
			}

			private double getSurface() {
				double sum = 0;
				for (Ball ball : balls)
					sum += ball.getRadius();
				return sum;
			}

			private int getSpeed() {
				return controller.speed;
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
					output.writeObject(new Point((int) x, (int) y));
					output.writeObject(controller.getAllBalls());
					output.writeObject(playersList());

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
					Point pointer = (Point) point;
					mouseX = pointer.x;
					mouseY = pointer.y;
					
					Object mouseClicked = input.readObject();
					boolean mouseClicked2 = (boolean) mouseClicked;
					if(mouseClicked2)
						divide();

					lastRead = System.currentTimeMillis();
				} catch (Exception e1) {

				}
			}
		});
	}

	private void scoreUpdate() {
		scoreUpdater = new Timer(10, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				userInfo.score = getScore();
			}
		});
		scoreUpdater.start();
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
		return (int) Math.PI * score;
	}

	public void divide() {
		ArrayList<Ball> temp = (ArrayList<Ball>) balls.clone();
		for (Ball ball : temp) {
			if (temp.size() < 4) {
				int rad = (int) (ball.getRadius() / 2);
				if (rad < 50)
					rad = 50;
				balls.add(new Ball(x + (int) (Math.random() * 100), y + (int) (Math.random() * ball.getRadius() / 2),
						rad, userInfo.name, userInfo.imgAdress, userInfo.color));
				balls.add(new Ball(x - (int) (Math.random() * 100), y - (int) (Math.random() * ball.getRadius() / 2),
						rad, userInfo.name, userInfo.imgAdress, userInfo.color));
				balls.remove(ball);
			}
		}
	}

	private void ballMerger() {
		Timer ballMerger = new Timer(2000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int cnt = 0;
				ArrayList<Ball> temp = (ArrayList<Ball>) balls.clone();
				for (Ball ball1 : temp) {
					if (cnt != 0 && ball1.getRadius() > 40) {
						ball1.setRadius(ball1.getRadius() - 3);
						balls.get(0).setRadius(balls.get(0).getRadius() + 2);
					} else if (cnt != 0 && ball1.getRadius() <= 40) {
						balls.get(0).setRadius(balls.get(0).getRadius() + ball1.getRadius()/2);
						balls.remove(ball1);
					}
					cnt++;
				}

			}
		});
		ballMerger.start();
	}

	public void ballPlacer() {
		Timer ballPlacer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Ball ball1 : balls) {
					for (Ball ball2 : balls) {
						if (ball1.hit(ball2) && ball1 != ball2 && ball1 != balls.get(0)) {
							ball1.setX(ball1.getX()
									+ (int) ((ball1.getRadius() + ball2.getRadius() - (ball2.getX() - ball1.getX()))
											* .1));
							ball1.setY(ball1.getY()
									+ (int) ((ball1.getRadius() + ball2.getRadius() - (ball2.getY() - ball1.getY()))
											* .1));
						}
					}
					Ball b1 = balls.get(0);
					b1.setX(x);
					b1.setY(y);
				}
			}
		});
		ballPlacer.start();
	}

}
