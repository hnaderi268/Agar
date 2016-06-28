package Server;

public class App {
	
	public static App app;
	public static Server server;
	public static Controller controller;
	
	public static void main(String[] args) {
		app=new App();
		controller=new Controller(app);
		server=new Server(app,1469);
		server.start();
	}
}
