package Client;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Window extends JFrame {
	public Field field;
	public App app;

	public Window(App app) {
		this.app = app;
		this.setTitle("Agar");
		this.setSize(1440, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		Container content = this.getContentPane();
		GridLayout layout = new GridLayout(0, 1);
		content.setLayout(layout);

		// Field
		field = new Field(app);
		content.add(field);

		// Mouse Listener
		
		
		this.setVisible(true);
	}

}
