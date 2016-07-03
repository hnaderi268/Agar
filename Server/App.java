package Server;

import java.awt.EventQueue;

public class App {

	public static App app;
	public static Server server;
	public static Controller controller;
	public Window window;

	public static void main(String[] args) {
		app = new App();
		controller = new Controller(app);
		app.window = new Window();		
		server = new Server(app, app.window, 1469);
		server.start();
	}
}
