package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Common.*;

/**
 * Created by Majid Vaghari on 6/26/2016.
 */
public class Server extends Thread {

	public Window window;
	private App app;
	private int port;

	public Server(App app, Window window, int port) {
		this.app = app;
		this.window = window;
		this.port = port;
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

				if (s.equals("yes"))
					createUser(socket, output, input);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void createUser(Socket socket, ObjectOutputStream output, ObjectInputStream input) {
		try {
			// ObjectInputStream input = new
			// ObjectInputStream(socket.getInputStream());
			// ObjectOutputStream output = new
			// ObjectOutputStream(socket.getOutputStream());
			Object o = input.readObject();
			UserInfo o2 = (UserInfo) o;
			Player ps = new Player(socket, input, output, o2.name, app.controller);
			ps.send.start();
			ps.read.start();
			window.write("Player " + o2.name + " joined the game.");
		} catch (IOException | ClassNotFoundException e) {

		}
	}
}
