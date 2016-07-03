package Client;

import javax.swing.JOptionPane;

public class App {

	static Client client;
	public static Window window;
	static StarterPanel startPan;
	public static Logger logger;
	
	public static void main(String[] args) {
		App app = new App();
		logger=new Logger();
		logger.write("Welcome!");
		client = new Client(app);
		startPan=new StarterPanel(app,client);
	}
}
