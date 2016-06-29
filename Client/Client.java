package Client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.Ball;
import Common.Point;
import Common.UserInfo;

/**
 * Created by Majid Vaghari on 6/26/2016.
 */
public class Client {

	Socket socket;
	App app;
	ObjectOutputStream output;
	ObjectInputStream input;

	int ip_interval_st = 1;
	int ip_interval_en = 10;

	Thread send;
	Thread read;

	public Client(App app) {
		this.app = app;
	}

	public String availableServers(int port) {
		String availableServers = "";
		System.out.println("finding servers...");
		socket = new Socket();
		// try {
		// socket.setSoTimeout(1000);
		// } catch (SocketException e1) {
		// e1.printStackTrace();
		// }
		for (int i = ip_interval_st; i <= ip_interval_en; i++) {
			try {
				System.out.println("checking: " + "127.0.0." + i);
				// socket.connect("127.0.0." + i, port);
				SocketAddress sockaddr = new InetSocketAddress("127.0.0." + i, port);
				socket.connect(sockaddr, 1000);
				System.out.println("connected to : " + "127.0.0." + i);
				if (validServer(socket)) {
					availableServers += "127.0.0." + i;
					System.out.println("valid");
				}
			} catch (Exception e) {
			}
		}
		return availableServers;
	}

	private boolean validServer(Socket socket2) {
		try {
			input = new ObjectInputStream(socket2.getInputStream());
			Object o = input.readObject();
			String s = (String) o;
			if (s.equals("Agar")){
				output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject("no");
				return true;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String connectToServer(String ip, int port) {
		try {
			System.out.println("trying to connect...");
			socket = new Socket(ip, port);
			System.out.println("connection established.");
			return ("connection established.");
		} catch (IOException e) {
			return ("Can not find the Server");
		}
	}

	public void sendUserInfo(String name) {
		try {
			input = new ObjectInputStream(socket.getInputStream());
			Object o = input.readObject();
			output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject("yes");
			UserInfo info = new UserInfo(name);
			output.writeObject(info);
		} catch (IOException | ClassNotFoundException e) {

		}
	}

	public void send() {
		send = new Thread(new Runnable() {
			public void run() {
				try {
					int cnt = 1;

					while (true) {
						output.reset();
						Point pt = new Point(app.window.field.mouseX, app.window.field.mouseY);
						output.writeObject(pt);

						// PrintWriter writer;
						// try {
						// writer = new PrintWriter("clientsend.txt", "UTF-8");
						// writer.println(cnt);
						// writer.close();
						// } catch (FileNotFoundException |
						// UnsupportedEncodingException e1) {
						// e1.printStackTrace();
						// }
						Thread.currentThread().sleep(70);
					}
				} catch (Exception e) {

				}
			}
		});
		send.start();
	}

	public void read() {
		read = new Thread(new Runnable() {
			public void run() {
				try {
					int cnt = 1;

					while (true) {

						Object point = input.readObject();
						Point pointer = (Point) point;
						app.window.field.playerX = pointer.x;
						app.window.field.playerY = pointer.y;

						Object balls = input.readObject();
						ArrayList<Ball> Balls2 = (ArrayList<Ball>) balls;
						app.window.field.balls = Balls2;
						app.window.field.repaint();

						Object users = input.readObject();
						ArrayList<UserInfo> usersInfo = (ArrayList<UserInfo>) users;
						app.window.field.usersInfo = usersInfo;

						// PrintWriter writer;
						// try {
						// writer = new PrintWriter("clientread.txt", "UTF-8");
						// writer.println(cnt++);
						// writer.close();
						// } catch (FileNotFoundException |
						// UnsupportedEncodingException e1) {
						// e1.printStackTrace();
						// }

						Thread.currentThread().sleep(70);
					}
				} catch (Exception e) {

				}
			}
		});
		read.start();
	}

}
