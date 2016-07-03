package Common;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class UserInfo implements Serializable {
	public String name;
	public int score = 0;
	public String imgAdress = new String("1.jpg");
	public String passCode;

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
	}

	@Override
	public String toString() {
		return "user{" + ", name='" + name + '\'' + '}';
	}

	public String getName() {
		return name;
	}
}
