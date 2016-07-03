package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;

import Common.Ball;
import Common.Point;
import Common.UserInfo;
import Server.Player;

public class Client {

	Socket socket;
	App app;
	ObjectOutputStream output;
	ObjectInputStream input;

	int ip_interval_st = 1;
	int ip_interval_en = 10;

	Thread send;
	Thread read;

	long lastRead;
	Timer checkConnections;

	public Client(App app) {
		this.app = app;
	}

	public String availableServers(int port) {
		String availableServers = "";
		app.logger.write("finding servers...");
		socket = new Socket();

		for (int i = ip_interval_st; i <= ip_interval_en; i++) {
			try {
				app.logger.write("checking: " + "127.0.0." + i);
				// socket.connect("127.0.0." + i, port);
				SocketAddress sockaddr = new InetSocketAddress("127.0.0." + i, port);
				socket.connect(sockaddr, 1000);
				app.logger.write("connected to : " + "127.0.0." + i);
				if (validServer(socket)) {
					availableServers += "127.0.0." + i;
					app.logger.write("valid");
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
			if (s.equals("Agar")) {
				output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject("info");
				return true;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String connectToServer(String ip, int port) {
		try {
			app.logger.write("trying to connect...");
			socket = new Socket(ip, port);
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			Object o = input.readObject();
			return ("connection established.");
		} catch (IOException | ClassNotFoundException e) {
			return ("Can not find the Server");
		}
	}

	public void sendUserInfo(UserInfo userInfo) {
		try {
			output.writeObject("register");
			output.writeObject(userInfo);
			checkConnections();

		} catch (IOException e) {

		}
	}

	public void loginUser(String name,String passCode) {
		try {

			 output.writeObject("login");
			 output.writeObject(name);
			 output.writeObject(passCode);
			 Object o=input.readObject();
			 String msg=(String)o;
			 JOptionPane.showMessageDialog(null, msg);
			 checkConnections();

		} catch (Exception e) {

		}
	}

	private void checkConnections() {
		checkConnections = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (System.currentTimeMillis() - lastRead > 2000 && lastRead != 0 && System.currentTimeMillis() - lastRead < 2099 ) {
					app.logger.write("Your connection has lost. You're now disconnected from server.");
				}
			}
		});
		checkConnections.start();
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

						lastRead = System.currentTimeMillis();
						Thread.currentThread().sleep(70);
					}
				} catch (Exception e) {

				}
			}
		});
		read.start();
	}

}
