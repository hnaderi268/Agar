package Client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
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

	Thread send;
	Thread read;

	public Client(App app) {
		this.app = app;
	}

	public String connectToServer(int port) {
		try {
			System.out.println("trying to connect...");
			socket = new Socket("localhost", port);
			System.out.println("connection established.");
			return ("connection established.");
		} catch (IOException e) {
			return ("Can not find the Server");
		}
	}

	public void sendUserInfo(String name) {
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			UserInfo info = new UserInfo(name);
			output.writeObject(info);
		} catch (IOException e) {

		}
	}

	public void send() {
		send = new Thread(new Runnable() {
			public void run() {
				try {
					int cnt = 1;
					

					while (true) {
						output.reset();
						Point pt=new Point(app.window.field.mouseX, app.window.field.mouseY);
						output.writeObject(pt);
						
//						PrintWriter writer;
//						try {
//							writer = new PrintWriter("clientsend.txt", "UTF-8");
//							writer.println(cnt);
//							writer.close();
//						} catch (FileNotFoundException | UnsupportedEncodingException e1) {
//							e1.printStackTrace();
//						}
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
					input = new ObjectInputStream(socket.getInputStream());
					while (true) {

						Object balls = input.readObject();
						ArrayList<Ball> Balls2 = (ArrayList<Ball>) balls;
						app.window.field.balls = Balls2;
						app.window.field.repaint();

						Object users = input.readObject();
						ArrayList<UserInfo> usersInfo = (ArrayList<UserInfo>) users;
						app.window.field.usersInfo = usersInfo;

//						PrintWriter writer;
//						try {
//							writer = new PrintWriter("clientread.txt", "UTF-8");
//							writer.println(cnt++);
//							writer.close();
//						} catch (FileNotFoundException | UnsupportedEncodingException e1) {
//							e1.printStackTrace();
//						}

						Thread.currentThread().sleep(70);
					}
				} catch (Exception e) {

				}
			}
		});
		read.start();
	}
}
