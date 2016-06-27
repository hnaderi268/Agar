package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import Common.Data;
import Common.*;

/**
 * Created by Majid Vaghari on 6/26/2016.
 */
public class Server {

	public static ArrayList<PlayerSocket> players = new ArrayList();
	public static Window window = new Window();

	public static void main(String[] args) throws IOException {

		window.write("initializing server...");
		window.write("server created");
		int connections = 1;
		ServerSocket serverSocket=new ServerSocket(3379);
		
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

	private static void createUser(Socket socket) {
		try {
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Object o = input.readObject();
			UserInfo o2 = (UserInfo) o;
			PlayerSocket p = new PlayerSocket(socket, o2.name);
			players.add(p);
			window.write("Player " + o2.name + " created & joined the game.");
			window.write("");
			p.start();
		} catch (IOException | ClassNotFoundException e) {

		}
	}
}
