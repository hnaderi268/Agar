package Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import Common.Ball;
import Common.UserInfo;

public class Field extends JPanel {

	App app;
	ArrayList<Ball> balls;
	public ArrayList<UserInfo> usersInfo = new ArrayList();
	public MouseMotionListener mouse_move;
	public int mouseX, mouseY;
	public int playerX, playerY;

	public Field(App app) {
		this.app = app;

		MouseMotionListener mouse_move = new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}

			public void mouseDragged(MouseEvent e) {
			}
		};
		addMouseMotionListener(mouse_move);

		MouseListener mouse_click = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				app.client.mouseClicked=true;
			}
		};
		addMouseListener(mouse_click);

	}

	public synchronized void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 2000, 1000);
		g2.setColor(Color.black);
		int spaceH = 0;
		if (usersInfo != null)
			for (UserInfo user : usersInfo)
				g2.drawString(user.name + ": " + user.score, 10, (spaceH += 20));

		if (balls != null)
			for (Ball ball : balls)
				ball.draw(g2, playerX, playerY);
	}
}
