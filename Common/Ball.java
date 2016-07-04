package Common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Ball implements Serializable {
	private double radius;
	private double x, y;
	private double difx = 0, dify = 0;
	private Color color;
	private String name = "";
	public String address = "";
	private static int mapWidth;

	public Ball(double x, double y, double r) {
		this.x = x;
		this.y = y;
		radius = r;
		color = giveColor();
	}

	public Ball(double x, double y, double r, String name) {
		this.x = x;
		this.y = y;
		radius = r;
		this.name = name;
		color = giveColor();
	}

	public Ball(double x, double y, double r, String name, String address, Color color) {
		this.x = x;
		this.y = y;
		radius = r;
		this.address = address;
		this.name = name;
		this.color = color;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double r) {
		radius = r;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x + difx;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y + dify;
	}

	public Color giveColor() {
		Color[] colors = new Color[20];
		colors[0] = new Color(241, 196, 15);
		colors[1] = new Color(26, 188, 156);
		colors[2] = new Color(22, 160, 133);
		colors[3] = new Color(46, 204, 113);
		colors[4] = new Color(39, 174, 96);
		colors[5] = new Color(52, 152, 219);
		colors[6] = new Color(41, 128, 185);
		colors[7] = new Color(142, 68, 173);
		colors[8] = new Color(52, 73, 94);
		colors[9] = new Color(243, 156, 18);
		colors[10] = new Color(230, 126, 34);
		colors[11] = new Color(211, 84, 0);
		colors[12] = new Color(231, 76, 60);
		colors[13] = new Color(231, 76, 60);
		colors[14] = new Color(192, 57, 43);
		return colors[(int) (Math.random() * 15)];
	}

	public synchronized boolean hit(Ball B) {
		if (((this.x - B.getX()) * (this.x - B.getX()) + (this.y - B.getY()) * (this.y - B.getY())) < (Math
				.pow((this.radius + B.getRadius()) / 2 + 6, 2.0)))
			return true;
		else
			return false;
	}

	public String toString() {
		return (x + ", " + y + ", " + radius);
	}

	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color.darker());
		g.fillOval((int) x - (int) ((radius + 6) / 2), (int) y - (int) ((radius + 6) / 2), (int) radius + 6,
				(int) radius + 6);
		g.setColor(color);
		g.fillOval((int) x - (int) (radius / 2), (int) y - (int) (radius / 2), (int) radius, (int) radius);
		g.setColor(color.darker().darker());
		if (name != null)
			g.drawString(name, (int) x - 5, (int) y + 5);
	}

	public void draw(Graphics2D g, double x2, double y2) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color.darker());
		g.fillOval((int) x - (int) ((radius + 6) / 2) - (int) x2 + 1440 / 2,
				(int) y - (int) ((radius + 6) / 2) - (int) y2 + 825 / 2, (int) radius + 6, (int) radius + 6);
		g.setColor(color);
		g.fillOval((int) x - (int) (radius / 2) - (int) x2 + 1440 / 2,
				(int) y - (int) (radius / 2) - (int) y2 + 825 / 2, (int) radius, (int) radius);
		g.setColor(color.darker().darker());
		Font font = new Font("Helvetica", Font.PLAIN, 22);
		g.setFont(font);
		g.drawString(name, (int) x - (int) x2 + 1440 / 2 - 32, (int) y - (int) y2 + 825 / 2 + 51);
		if (!address.equals("")) {
			Image icon = new ImageIcon(address).getImage();
			g.drawImage(icon, (int) x - (int) x2 + 1440 / 2 - 20, (int) y - (int) y2 + 825 / 2 - 20, null);
		}
	}

}
