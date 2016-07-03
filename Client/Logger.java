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
		frame.setSize(240, 190);
		frame.setLocation(0, 350);
		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);
		frame.setLayout(new GridLayout(0, 1));
		JTextArea label = new JTextArea();
		frame.add(label);
		frame.setVisible(true);

		new Timer(100, (e) -> {
			label.setText(l.toString());
			label.setText(label.getText()+" ");
//			label.setCaretPosition(label.getDocument().getLength());
//			String s = label.getText();
//			int pos = s.length();
//			label.setCaretPosition(pos);
//			label.selectAll();
//			Document d = label.getDocument();
//			label.select(d.getLength(), d.getLength());
		}).start();

	}

	public void write(String text) {
		l.append(text + "\n");
	}
}
