package Client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class registerPanel extends JFrame {

	private JLabel labelName = new JLabel("Enter your name: ");
	private JLabel labelPass = new JLabel("Enter your password: ");
	private JLabel labelPic = new JLabel("Please enter your costum picture: ");
	private JLabel labelColor = new JLabel("Please enter your costum color(in Hex): ");
	private JTextField textName = new JTextField(20);
	private JTextField textPass = new JTextField(20);
	private JTextField textPic = new JTextField(20);
	private JTextField textColor = new JTextField(20);
	private JButton playButton = new JButton("Play!");
	private App app;

	public registerPanel(App app) {

		this.app = app;

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
		newPanel.add(textPic, constraints);

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

		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				if (textMapW.getText().equals(""))
//					textMapW.setText("1000");
//				if (textMapH.getText().equals(""))
//					textMapH.setText("500");
//				if (textScoreballs.getText().equals(""))
//					textScoreballs.setText("0");
//				if (textSpeed.getText().equals(""))
//					textSpeed.setText("400");
//				int scoreballs = Integer.parseInt(textScoreballs.getText());
//				int mapw = Integer.parseInt(textMapW.getText());
//				int maph = Integer.parseInt(textMapH.getText());
//				int speed = Integer.parseInt(textSpeed.getText());
//				if (mapw < 1000 || mapw > 4000)
//					mapw = 2000;
//				if (maph < 500 || maph > 2000)
//					maph = 1000;
//				if (scoreballs == 0)
//					scoreballs = (int) ((maph * mapw) / 40000);
//
//				app.controller = new Controller(app, mapw, maph, speed, scoreballs);
			}
		});

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Game Config"));

		// add the panel to this frame
		add(newPanel);

		pack();
		setLocationRelativeTo(null);

		this.setVisible(true);
	}
}
