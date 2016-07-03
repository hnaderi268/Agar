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

public class LoginPanel extends JFrame {

		private JLabel labelName = new JLabel("Enter your name: ");
		private JLabel labelPass = new JLabel("Enter your password: ");
		private JTextField textName = new JTextField(20);
		private JTextField textPass = new JTextField(20);
		private JButton playButton = new JButton("Play!");
		private Client client;

		public LoginPanel(Client client) {

			this.client = client;

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
			constraints.gridwidth = 2;
			constraints.anchor = GridBagConstraints.CENTER;
			newPanel.add(playButton, constraints);

			playButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					client.loginUser(textName.getText(), textPass.getText());
					client.app.window = new Window(client.app);
					client.read();
					client.send();
					setVisible(false);
				}
			});

			// set border for the panel
			newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));

			// add the panel to this frame
			add(newPanel);

			pack();
			setLocationRelativeTo(null);

			this.setVisible(true);
		}
}
