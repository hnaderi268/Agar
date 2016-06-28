package Server;

import java.util.ArrayList;

import Common.Ball;

public class Controller extends Thread {

	public static ArrayList<Player> players = new ArrayList();
	public static ArrayList<Ball> scoreBalls = new ArrayList();
	public int mapWidth = 2000, mapHeight = 1000;
	public int scoreBallsCount = (mapWidth * mapHeight) / 40000;

	public Controller(App app) {
		makeScoreBalls();
	}

	private void makeScoreBalls() {
		for (int i = 0; i < scoreBallsCount; i++) {
			Ball B = new Ball(Math.random() * mapWidth,Math.random() * mapHeight, 10 + Math.random() * 25);
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
}
