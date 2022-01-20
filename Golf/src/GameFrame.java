import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;


public class GameFrame extends JFrame {

	GamePanel panel;
//	JButton resetButton;
//	GamePanel game;
	Color back = new Color(43, 161, 75);
	
	GameFrame() {
		panel = new GamePanel();	
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(true);
		this.setBackground(back);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
	}

	
	
}

