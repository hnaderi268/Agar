package Client;

import javax.swing.JOptionPane;

public class App {

	static Client client;
	public static Window window;
	static StarterPanel startPan;
	public static Logger logger;
	
	public static void main(String[] args) {
		App app = new App();
//		logger=new Logger();
//		logger.write("Welcome!");
		client = new Client(app);
		// int port=Integer.parseInt(JOptionPane.showInputDialog("Hi.Welcome to
		// the world's best game ever\n" +
		// "First of all,write server's Port:"));
		
		startPan=new StarterPanel(app,client);
	}
}
