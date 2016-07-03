package Common;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class UserInfo implements Serializable {
	public String name;
	public int score = 0;
	public String imgAdress = new String("1.jpg");
	public String passCode;
	public Color color;

	public UserInfo(String name) {
		this.name = name;
	}

	public UserInfo(String name, String imgAdress) {
		this.name = name;
		this.imgAdress = imgAdress;
	}
	
	public UserInfo(String name, String imgAdress, String passCode) {
		this.name = name;
		this.imgAdress = imgAdress;
		this.passCode = passCode;
		this.color=giveColor();
	}

	public UserInfo(String name, String imgAdress, String passCode,Color color) {
		this.name = name;
		this.imgAdress = imgAdress;
		this.passCode = passCode;
		this.color=color;
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

	@Override
	public String toString() {
		return "user{" + ", name='" + name + '\'' + '}';
	}

	public String getName() {
		return name;
	}
}
