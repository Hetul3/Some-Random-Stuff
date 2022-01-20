import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{

	Random random;
	double xVelocity;
	double yVelocity;
	double initialSpeed = 50;


	DecimalFormat df = new DecimalFormat("0.000");	
	
	Ball(int x, int y, int width, int height) {
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*initialSpeed);
		 
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);
		
		
	}
	public void setYDirection(double randomYDirection) {
		yVelocity = randomYDirection;
		
	}

	public void setXDirection(double randomXDirection) {
	
		xVelocity = randomXDirection;
	}
	public void move () {
		x = (int) (x + xVelocity);
		y = (int) (y + yVelocity);
	}
	public void hitbox() {
		setRect(x, y, 35, 35);
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}

	

	
}
 