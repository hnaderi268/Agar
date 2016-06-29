package Client;

import javax.swing.JOptionPane;

public class App {

	static Client client;
	public static Window window;

	public static void main(String[] args) {
		App app = new App();
		client = new Client(app);
		// int port=Integer.parseInt(JOptionPane.showInputDialog("Hi.Welcome to
		// the world's best game ever\n" +
		// "First of all,write server's Port:"));
		
		JOptionPane.showMessageDialog(null, "Available servers are:\n"+client.availableServers(1469));
		
		String state = client.connectToServer(JOptionPane.showInputDialog("Type IP of the server you want to join: "),1469);
		System.out.println(state);
		if (!state.equals("Can not find the Server")) {
			client.sendUserInfo(JOptionPane.showInputDialog(
					"From this precious moment you are connected\n" + "to Server. Please Enter your name to play:"));
			window = new Window(app);
			client.read();
			client.send();
		}
//		System.out.println("Client closed.");
	}
}
