package Server;

import java.awt.GridLayout;

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
		this.setVisible(true);
	}
	
	public void write(String text){
		label.append(text+"\n");
		this.add(label);
		this.setVisible(true);
	}
}
