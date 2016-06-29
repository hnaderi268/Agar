package Server;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Window /* extends JFrame */ {
	JFrame frame;
	// JTextArea label=new JTextArea();
	StringBuilder l = new StringBuilder();

	public Window() {
	    EventQueue.invokeLater(this::initUI);
	}

	private void initUI() {
		frame = new JFrame();
		frame.setTitle("Server");
		frame.setSize(400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setLayout(new GridLayout(0, 1));
		JTextArea label = new JTextArea();
		frame.add(label);
		label.setEditable(false);
		frame.pack();
		frame.setSize(220, 900);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int x = (int) rect.getMaxX() - frame.getWidth();
		int y = 0;
		frame.setLocation(x, y);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

		new Timer(1000, (e) -> label.setText(l.toString())).start();

	}

	public void write(String text) {
		l.append(text + "\n");
		// frame.add(label);
		// frame.setVisible(true);
	}
}
