//game panel for the pong.java project 12/11/2021

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable, MouseListener{

	
	static int GAME_WIDTH = 1000;
	static int GAME_HEIGHT = (int)(GAME_WIDTH * (0.6));
	static Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static int BALL_DIAMETER = 35;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Ball ball;
	Hole hole;
	double friccon = 0.98;
	int prevX;
	int prevY;
	JButton resetButton;
	JFrame frame = new JFrame();

	
	GamePanel() {
		newBall();
		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		this.addMouseListener(this);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
		
	
	public void newBall() {
		hole = new Hole((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/3) - BALL_DIAMETER/2, BALL_DIAMETER + 5, BALL_DIAMETER + 5);
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - BALL_DIAMETER/2, BALL_DIAMETER, BALL_DIAMETER);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	public void draw(Graphics g) {
		hole.draw(g);
		ball.draw(g);
	}
	public void move() {
		ball.move();	
	}
	public void checkCollision() {
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.x <= 0) {
			ball.setXDirection(-ball.xVelocity);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			ball.setXDirection(-ball.xVelocity);
		}	
		
		/*set collision when the ball changes 
		diameter, so it doesn't get stuck
		*/
		if(ball.x > GAME_WIDTH-BALL_DIAMETER) {
			ball.x = GAME_WIDTH-BALL_DIAMETER;
		}
		if(ball.x < 0) {
			ball.x = 0;
		}
		if(ball.y > GAME_HEIGHT-BALL_DIAMETER) {
			ball.y = GAME_HEIGHT-BALL_DIAMETER;
		}
		if(ball.y < 0) {
			ball.y = 0;
		}
		
	}
	

	public void friction() {
		//friction
		ball.yVelocity = ball.yVelocity * friccon;
		ball.xVelocity = ball.xVelocity * friccon;

		if(Math.abs(ball.xVelocity) < 1 && Math.abs(ball.yVelocity) < 1) {
			ball.xVelocity = 0;
			ball.yVelocity = 0;
		}
	
	}
	
	public void holein() {
		double dx = ball.x - hole.x;
		double dy = ball.y - hole.y;
		
		double Radius = BALL_DIAMETER - 5;
		double dist = (dx * dx) + (dy * dy);
		double distsqrt = Math.sqrt(dist);
		
		if(distsqrt <= Radius) {
			ball.xVelocity = 0;
			ball.yVelocity = 0;
			ball.x = hole.x;
			ball.y = hole.y;
		
		}
	}
			
	public void run() {
	//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				friction();
				holein();
				delta--;
			}
		}
		
		
	}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			ball.xVelocity = 0;
			ball.yVelocity = 0;
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			ball.width = 50;
			ball.height = 50;
			BALL_DIAMETER = 50;
			prevX = e.getX();
			prevY = e.getY();
			
		}
		public void mouseDragged(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			ball.width = 35;
			ball.height = 35;
			BALL_DIAMETER = 35;	
	
			//remove if statement if you wan to be able to impact the ball even in motion
			
		if(Math.abs(ball.xVelocity) <= 0 && Math.abs(ball.yVelocity) <= 0) {
	
			int curX = e.getX();
			int curY = e.getY();
			
			int distX = curX - prevX;
			int distY = curY - prevY;
			
			ball.xVelocity = -distX / 7;
			ball.yVelocity = -distY / 7;
	} 
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub	
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}