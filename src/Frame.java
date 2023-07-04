import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Frame extends JFrame{

	Panel panel;
	Frame(){
		
		panel= new Panel();
		this.add(panel);
		this.setTitle("PING PONG");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}