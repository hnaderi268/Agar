package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Common.*;

public class Server extends Thread {

	public Window window;
	private App app;
	private int port;
	Timer checkConnections;
	public static ArrayList<UserInfo> users = new ArrayList();

	public Server(App app, Window window, int port) {
		this.app = app;
		this.window = window;
		this.port = port;
		checkConnections();
	}

	private void checkConnections() {
		checkConnections = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Player> playertemp = (ArrayList<Player>) app.controller.players.clone();
				for (Player player : playertemp) {
					if (System.currentTimeMillis() - player.lastRead > 2000 && player.lastRead != 0) {
						window.write(player.userInfo.name + " has left the game.");
						app.controller.players.remove(player);
					}
				}
			}
		});
		checkConnections.start();
	}

	public void run() {

		window.write("initializing server...");
		int connections = 1;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			window.write("server created. Port:" + port);
			window.write(serverSocket.getInetAddress().toString());
		} catch (IOException e1) {
			window.write("There are problems with creating server.");
			window.write("");
		}
		while (true) {
			try {
				window.write("");
				window.write("waiting for request :" + connections++);
				Socket socket = serverSocket.accept();
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject("Agar");

				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				Object o = input.readObject();
				String s = (String) o;

				window.write("connection established.");
				window.write("remote address: " + socket.getRemoteSocketAddress());
				window.write(s + " request");

				if (s.equals("register")) {
					createUser(socket, output, input);

				}

				if (s.equals("login"))
					loginUser(socket, output, input);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void createUser(Socket socket, ObjectOutputStream output, ObjectInputStream input) {
		try {

			Object o = input.readObject();
			UserInfo o2 = (UserInfo) o;
			users.add(o2);

			Player ps = new Player(socket, input, output, o2, app.controller);
			ps.send.start();
			ps.read.start();
			window.write(o2.name + " joined the game.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(" " + e.getMessage());
		}
	}

	private void loginUser(Socket socket, ObjectOutputStream output, ObjectInputStream input) {
		try {
			Object o = input.readObject();
			String id = (String) o;

			o = input.readObject();
			String passCode = (String) o;

			boolean state = false;
			for (UserInfo user : users) {
				if (user.name.equals(id))
					if (user.passCode.equals(passCode)) {
						Player ps = new Player(socket, input, output, user, app.controller);
						ps.send.start();
						ps.read.start();
						window.write(id + " joined the game.");
						state = true;
						break;
					}
			}
			if (state)
				output.writeObject("Welcome back!");
			if (!state)
				output.writeObject("ID or Pass Code is wrong!");

		} catch (Exception e) {
			System.out.println(" " + e.getMessage());
		}
	}
}
