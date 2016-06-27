package Client;

import javax.swing.JOptionPane;

public class App {
	
	static Client client;
	
	public static void main(String[] args) {
		client=new Client();
//		JOptionPane.showInputDialog("Hi.Welcome to the world's best game ever\n" +
//				"First of all,write server's Port:");
		System.out.println(client.connectToServer(3379));
		client.sendUserInfo(JOptionPane.showInputDialog("From this precious moment you are connected\n" +
				"to Server. Please Enter your name to play:"));
		client.play();
	}
}
