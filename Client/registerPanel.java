package Client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Common.UserInfo;

public class RegisterPanel extends JFrame {

	private JLabel labelName = new JLabel("Enter your name(ID): ");
	private JLabel labelPass = new JLabel("Enter your password: ");
	private JLabel labelPic = new JLabel("Please select your costum picture: ");
	private JLabel labelColor = new JLabel("Please enter your costum color(in Hex): ");
	private JTextField textName = new JTextField(20);
	private JTextField textPass = new JTextField(20);
	private JButton btnPic = new JButton("Browse");
	private JTextField textColor = new JTextField(20);
	private JButton playButton = new JButton("Play!");
	private Client client;
	private String address;

	public RegisterPanel(Client client) {

		this.client = client;
		client.app.startPan.setVisible(false);

		this.setTitle("Register Panel");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(labelName, constraints);

		constraints.gridx = 1;
		newPanel.add(textName, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		newPanel.add(labelPass, constraints);

		constraints.gridx = 1;
		newPanel.add(textPass, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		newPanel.add(labelPic, constraints);

		constraints.gridx = 1;
		newPanel.add(btnPic, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		newPanel.add(labelColor, constraints);

		constraints.gridx = 1;
		newPanel.add(textColor, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(playButton, constraints);

		btnPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("/Users/ho3in/Desktop/Avatar"));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					address=selectedFile.getAbsolutePath();
				}
			}
		});
		
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name=textName.getText();
				String passCode=textPass.getText();
				client.sendUserInfo(new UserInfo(name,address,passCode));
				client.app.window = new Window(client.app);
				client.read();
				client.send();
			}
		});

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Register"));

		// add the panel to this frame
		add(newPanel);

		pack();
		setLocationRelativeTo(null);

		this.setVisible(true);
	}
}
