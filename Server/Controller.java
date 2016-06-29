package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.Timer;

import Common.Ball;

public class Controller extends Thread {

	public static ArrayList<Player> players = new ArrayList();
	public static ArrayList<Ball> scoreBalls = new ArrayList();
	public int mapWidth = 2000, mapHeight = 1000;
	public int scoreBallsCount = (mapWidth * mapHeight) / 40000;
	public App app;
	public Timer dance;

	public Controller(App app) {
		this.app = app;
		makeScoreBalls();
//		dance();
	}

	private void dance() {
		dance = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Ball ball : scoreBalls) {
					ball.setX(ball.getX() + Math.random() * 4 - 2);
					ball.setY(ball.getY() + Math.random() * 4 - 2);
				}
			}
		});
		dance.start();

	}

	private void makeScoreBalls() {
		for (int i = 0; i < scoreBallsCount; i++) {
			Ball B = new Ball(Math.random() * mapWidth, Math.random() * mapHeight, 10 + Math.random() * 25);
			scoreBalls.add(B);
		}
	}

	public void run() {

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
