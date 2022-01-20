import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

public class Hole extends Rectangle{

	Random random;
	
	DecimalFormat df = new DecimalFormat("0.000");	
	
	Hole(int x, int y, int height, int width) {
		super(x,y,width,height);
		random = new Random();
		int randomX = random.nextInt(2);
		int randomY = random.nextInt(2);		
	}
	public void hitbox() {
		setRect(x, y, 35, 35);
	}
	


	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, height, width);
	}	
}