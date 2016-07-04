package Client;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;

public class Logger /* extends JFrame */ {
	JWindow frame;
	// JTextArea label=new JTextArea();
	StringBuilder l = new StringBuilder();

	public Logger() {
		// EventQueue.invokeLater(this::initUI);
		initUI();
	}

	private void initUI() {
		frame = new JWindow();
		frame.setSize(240, 220);
		frame.setLocation(30, 595);
		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);
		frame.setLayout(new GridLayout(0, 1));
		JTextPane label = new JTextPane();
		JScrollPane jsp = new JScrollPane(label);
		frame.add(jsp);
		frame.setVisible(true);

		new Timer(100, (e) -> {
			label.setText(l.toString());
			label.setText(label.getText()+" ");
		}).start();

	}

	public void write(String text) {
		l.append(text + "\n");
	}
}
