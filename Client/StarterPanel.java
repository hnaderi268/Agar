package Client;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Common.UserInfo;

public class StarterPanel extends JFrame {

	private JLabel labelIP = new JLabel();
	private JLabel servers = new JLabel();
	private JLabel help = new JLabel();
	private JTextField textIP = new JTextField(20);
	private JButton loginButton = new JButton("Login!");
	private JButton registerButton = new JButton("Register!");
	private JButton guestButton = new JButton("Play as guest!");
	private App app;
	private Client client;

	public StarterPanel(App app, Client client) {

		this.app = app;
		this.client = client;

		this.setTitle("Welcome to the game");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		labelIP.setText("Available servers are: \n");
		servers.setText(client.availableServers(1469));
		help.setText("Type IP of the server you want to join: ");

		JPanel newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
//		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(labelIP, constraints);

		constraints.gridx = 1;
		newPanel.add(servers, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		newPanel.add(help, constraints);

		constraints.gridx = 1;
		newPanel.add(textIP, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
//		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(registerButton, constraints);
		constraints.gridx = 1;
		newPanel.add(loginButton, constraints);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridx = 1;
		newPanel.add(guestButton, constraints);
//		newPanel.add(guestButton, BorderLayout.WEST);
		
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String state = client.connectToServer(textIP.getText(), 1469);
				System.out.println(state);
				// RegisterPanel regPan=new RegisterPanel(app);
				// LoginPanel logPan=new LoginPanel(app);
				if (!state.equals("Can not find the Server")) {
					RegisterPanel regPan = new RegisterPanel(client);
				} else {
					JOptionPane.showMessageDialog(null, state);
					System.out.println("Client closed.");
				}
			}
		});

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String state = client.connectToServer(textIP.getText(), 1469);
				System.out.println(state);
				// RegisterPanel regPan=new RegisterPanel(app);
				// LoginPanel logPan=new LoginPanel(app);
				if (!state.equals("Can not find the Server")) {
					LoginPanel loginPan = new LoginPanel(client);
				} else {
					JOptionPane.showMessageDialog(null, state);
					System.out.println("Client closed.");
				}
			}
		});

		guestButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String state = client.connectToServer(textIP.getText(), 1469);
				System.out.println(state);
				if (!state.equals("Can not find the Server")) {
					String name = "Guest " +(int)(Math.random()*9000+1000);
					String passCode = "pass";
					String address = "";
					client.sendUserInfo(new UserInfo(name, address, passCode));
					client.app.window = new Window(client.app);
					client.read();
					client.send();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, state);
					System.out.println("Client closed.");
				}
			}
		});

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Start"));

		// add the panel to this frame
		add(newPanel);

		pack();
		setLocationRelativeTo(null);

		this.setVisible(true);
	}

}
