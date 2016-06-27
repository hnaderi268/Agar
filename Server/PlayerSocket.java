package Server;

import java.net.Socket;

import javax.swing.JOptionPane;

public class PlayerSocket extends Thread {

	private Socket socket;
	private String name;

	public PlayerSocket(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}

	public void run() {
		try {
			int cnt=1;
			while (true) {
//				JOptionPane.showMessageDialog(null, "Hello! It is"+(cnt++)+"time i'm saying hello to you.");
				sleep(500);
			}
		} catch (InterruptedException e) {

		}
	}
}
