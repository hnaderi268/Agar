package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Common.Data;
import Common.*;

/**
 * Created by Majid Vaghari on 6/26/2016.
 */
public class Server extends Thread {

	
	public static Window window = new Window();
	private App app;
	private int port;

	public Server(App app, int port) {
		this.app = app;
		this.port = port;
	}

	public void run() {

		window.write("initializing server...");
		int connections = 1;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			window.write("server created. Port:" + port);
			window.write("");
		} catch (IOException e1) {
			window.write("There are problems with creating server.");
			window.write("");
		}
		while (true) {
			try {
				window.write("waiting for player " + connections++);
				Socket socket = serverSocket.accept();
				window.write("connection established.");
				window.write("remote address: " + socket.getRemoteSocketAddress());
				createUser(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createUser(Socket socket) {
		try {
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Object o = input.readObject();
			UserInfo o2 = (UserInfo) o;
			Player ps = new Player(socket, o2.name, app.controller);
			ps.start();
			window.write("Player " + o2.name + " joined the game.");
			window.write("");
		} catch (IOException | ClassNotFoundException e) {

		}
	}
}
