package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.Timer;

import Common.Ball;

public class Controller {

	public static ArrayList<Player> players = new ArrayList();
	public static ArrayList<Ball> scoreBalls = new ArrayList();
	public int mapWidth = 4000, mapHeight = 4000;
	public int scoreBallsCount = (mapWidth * mapHeight) / 100000;
	public App app;
	public int speed = 250;
	public Timer dance, hitTest, checkLose;
	public Ball gear, accelerator, god, invader;

	public Controller(App app) {
		this.app = app;
		makeScoreBalls();
		dance();
		hitTest();
		checkLose();
	}

	private void dance() {
		dance = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Player player : players)
					for (Ball ball : player.balls) {
						ball.setX(ball.getX() + Math.random() * 2 - 1);
						ball.setY(ball.getY() + Math.random() * 2 - 1);
					}
			}
		});
		dance.start();
	}

	private void checkLose() {
		checkLose = new Timer(70, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Player player : players)
					if (player.balls.size() == 0) {
						app.server.window.write(player.userInfo.name + " lost.");
						players.remove(player);
					}
			}
		});
		checkLose.start();
	}

	public synchronized void hitTest() {
		hitTest = new Timer(70, new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				ArrayList<Ball> balltemp = (ArrayList<Ball>) scoreBalls.clone();
				ArrayList<Player> playertemp = (ArrayList<Player>) players.clone();
				for (Player player : playertemp) {
					ArrayList<Ball> playerBallsTemp = (ArrayList<Ball>) player.balls.clone();
					for (Ball ball : playerBallsTemp)
						for (Ball scoreBall : balltemp)
							if (ball.hit(scoreBall))
								if (scoreBall == gear) {
									player.divide();
								} else if (scoreBall == accelerator) {
									new Thread(new Runnable() {
										public void run() {
											try {
												scoreBalls.remove(scoreBall);
												player.speedPower = true;
												long past = System.currentTimeMillis();
												while (System.currentTimeMillis() - past < 1500)
													System.err.println("");
												player.speedPower = false;
												accelerator = new Ball(Math.random() * getMapWidth(),
														Math.random() * getMapHeight(), 10 + Math.random() * 15,
														"Speed");
												scoreBalls.add(accelerator);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}).start();
								} else if (scoreBall == god) {
									new Thread(new Runnable() {
										public void run() {
											try {
												scoreBalls.remove(scoreBall);
												player.godPower();
												god = new Ball(Math.random() * getMapWidth(),
														Math.random() * getMapHeight(), 10 + Math.random() * 15, "God");
												scoreBalls.add(god);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}).start();
								} else if (scoreBall == invader) {
									for (Ball invball : player.balls)
										invball.setRadius(invball.getRadius() * .99);
									long past = System.currentTimeMillis();
									while (System.currentTimeMillis() - past < 100)
										System.err.println("");
								} else if (ball.getRadius() > scoreBall.getRadius())
									eat(ball, scoreBall);
				}

				int p1index = 0;
				for (Player player1 : playertemp) {
					int p2index = 0;
					for (Player player2 : playertemp) {
						if (p1index != p2index)
							for (Ball ball1 : player1.balls)
								for (Ball ball2 : player2.balls)
									if (ball1 != ball2 && ball1.hit(ball2)) {
										if (ball1.getRadius() > ball2.getRadius())
											players.get(p2index).balls.remove(ball2);
										else if (ball1.getRadius() < ball2.getRadius())
											players.get(p1index).balls.remove(ball1);
									}
						p2index++;
					}
					p1index++;
				}
			}

			private void eat(Ball ball, Ball scoreBall) {
				ball.setRadius(Math.sqrt((Math.PI * ball.getRadius() * ball.getRadius()
						+ Math.PI * scoreBall.getRadius() * scoreBall.getRadius()) / Math.PI));
				scoreBalls.remove(scoreBall);
				Ball B = new Ball(Math.random() * mapWidth, Math.random() * mapHeight, 10 + Math.random() * 25);
				scoreBalls.add(B);
			}
		});
		hitTest.start();
	}

	private void makeScoreBalls() {
		for (int i = 0; i < scoreBallsCount; i++) {
			Ball B = new Ball(Math.random() * mapWidth, Math.random() * mapHeight, 10 + Math.random() * 25);
			scoreBalls.add(B);
		}
		accelerator = new Ball(Math.random() * getMapWidth(), Math.random() * getMapHeight(), 10 + Math.random() * 15,
				"Speed");
		god = new Ball(Math.random() * getMapWidth(), Math.random() * getMapHeight(), 10 + Math.random() * 15, "God");
		gear = new Ball(Math.random() * getMapWidth(), Math.random() * getMapHeight(), 20 + Math.random() * 45, "Gear");
		invader = new Ball(Math.random() * getMapWidth(), Math.random() * getMapHeight(), 10 + Math.random() * 15,
				"Invader");
		scoreBalls.add(accelerator);
		scoreBalls.add(god);
		scoreBalls.add(gear);
		scoreBalls.add(invader);
	}

	public double getMapWidth() {
		return mapWidth;
	}

	public double getMapHeight() {
		return mapHeight;
	}

	public ArrayList<Ball> getAllBalls() {
		ArrayList<Ball> allBalls = new ArrayList();
		allBalls.addAll(scoreBalls);
		for (Player player : players)
			allBalls.addAll(player.balls);
		return allBalls;
	}
}
