package Server;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window extends JFrame {
	
	JTextArea label=new JTextArea();
	
	public Window() {
		this.setTitle("Server");
		this.setSize(400, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLayout(new GridLayout(0, 1));
		this.add(label);
		label.setEditable(false);
		this.pack();
		this.setSize(400, 900);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - this.getWidth();
        int y = 0;
        setLocation(x, y);
        setAlwaysOnTop (true);
		this.setVisible(true);
	}
	
	public void write(String text){
		label.append(text+"\n");
		this.add(label);
		this.setVisible(true);
	}
}
