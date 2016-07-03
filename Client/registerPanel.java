package Client;

import java.awt.Color;
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
import javax.swing.JColorChooser;
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
	private String address="Avatar/1.jpg";
	private Color color = giveColor();

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
		JButton colorButton = new JButton("Color");
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Choose color", Color.white);
			}
		});
		newPanel.add(colorButton, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(playButton, constraints);

		btnPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("Avatar"));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					address = selectedFile.getAbsolutePath();
				}
			}
		});

		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				if (name.equals(""))
					name = "noname " + (int) (Math.random() * 9000 + 1000);
				String passCode = textPass.getText();
				if (passCode.equals(""))
					passCode = "pass";
				client.sendUserInfo(new UserInfo(name, address, passCode, color));
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

	public Color giveColor() {
		Color[] colors = new Color[20];
		colors[0] = new Color(241, 196, 15);
		colors[1] = new Color(26, 188, 156);
		colors[2] = new Color(22, 160, 133);
		colors[3] = new Color(46, 204, 113);
		colors[4] = new Color(39, 174, 96);
		colors[5] = new Color(52, 152, 219);
		colors[6] = new Color(41, 128, 185);
		colors[7] = new Color(142, 68, 173);
		colors[8] = new Color(52, 73, 94);
		colors[9] = new Color(243, 156, 18);
		colors[10] = new Color(230, 126, 34);
		colors[11] = new Color(211, 84, 0);
		colors[12] = new Color(231, 76, 60);
		colors[13] = new Color(231, 76, 60);
		colors[14] = new Color(192, 57, 43);
		return colors[(int) (Math.random() * 15)];
	}
}
