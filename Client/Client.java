package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Common.Data;
import Common.UserInfo;

/**
 * Created by Majid Vaghari on 6/26/2016.
 */
public class Client {
	Socket socket;

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
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			UserInfo info = new UserInfo(name);
			output.writeObject(info);
		} catch (IOException e) {

		}
	}

	public void play() {
		try {
			while (true) {
//				System.out.println("creating input stream of objects...");
//				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//				System.out.println("input stream created.");
//				System.out.println("client: reading object...");
//				Object o = input.readObject();
//				ArrayList arr = (ArrayList) o;
//				System.out.println("client: object read successful.");
//				for (Object d : arr) {
//					Data k = (Data) d;
//					System.out.println("object: " + k);
//				}
//				System.out.println("object: " + o);
			}
		} catch (Exception e) {

		}
	}
}
