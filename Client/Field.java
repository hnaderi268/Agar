package Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import Common.Ball;

public class Field extends JPanel {

	App app;
	ArrayList<Ball> balls;

	public Field(App app) {
		this.app = app;
	}

	public synchronized void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 1400, 900);

		if (balls != null)
			for (Ball ball : balls)
				ball.draw(g2);
	}
}
