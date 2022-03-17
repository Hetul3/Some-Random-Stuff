//Hetul, Multi-tasking ball game like pong with special events

package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hsa2.GraphicsConsole;
import javax.swing.Timer;

public class BallBounce2 implements ActionListener{
	
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	final static int TIMERSPEED = 1;
	int rectwidth = 400;
	int rectheight = 20;
	int rectwidth2 = 200;
	//paddles will change color the more they're hit
	int bar1c = 0;
	int bar2c = 0;
	int bar3c = 0;
	int bar4c = 0;
	int ColorIncrement = 5;
	
	
	Timer timer = new Timer(TIMERSPEED, this);
	double seconds = 0.0;
	
	ArrayList<Ball> ballList = new ArrayList<Ball>();
	
	int counter = 10000; 
	int NUMBALLS = 1;
	
	GraphicsConsole gc = new GraphicsConsole(WIDTH, HEIGHT);
	Rectangle bar = new Rectangle(300, 580, rectwidth, rectheight);
	Rectangle bar2 = new Rectangle(780, 300, rectheight, rectwidth);
	Rectangle bar3 = new Rectangle(300, 0, rectwidth, rectheight);
	Rectangle bar4 = new Rectangle(0, 300, rectheight, rectwidth);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BallBounce2().Game();
	}
	
	BallBounce2() {
		
		gc.enableMouse();
		gc.enableMouseMotion();
		timer.start();
		
		gc.setTitle("Bounce");
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null);
		gc.setBackgroundColor(new Color(150, 191, 255));
		gc.clear();
		gc.setColor(Color.RED);
		
		
		for(int i = 0; i < NUMBALLS; i++) {
			ballList.add(new Ball());
		}
	}
	
	void Game() {
		while(counter > 0 && seconds <= 88005) {
			seconds++;
			moveBall();
			drawGraphics();
			gc.sleep(1);
			if(seconds == 1) {
				gc.showDialog("Keep the ball from touching the border. Watch out for Special Conditions!", "The Rules");
			}
			else if(seconds == 15000) {							//shows dialog box
				gc.showDialog("Your paddle size will be halved", "Special Condition");
			}
			else if(seconds > 15000 && seconds < 25000) {		//makes the paddles smaller
				bar.width = rectwidth2;
				bar2.height = rectwidth2;
				bar3.width = rectwidth2;
				bar4.height = rectwidth2;
				MouseControls();
			} 
			else if(seconds == 35000) 	gc.showDialog("Inverted Controls", "Special Condition");			
			else if(seconds > 35000 && seconds < 45000) {	//inverts your controls
				InverseMouse();
			}
			else if(seconds == 50000) gc.showDialog("Keyboard controls! Arrow Keys", "Special Condition");
			else if (seconds > 50000 && seconds < 60000) {
				if(gc.getKeyCode() == 'W' && bar2.y > 0) {	//Control using keyboard
					bar2.y-=2;
					bar4.y-=2;
				}
				if(gc.getKeyCode() == 'S' && bar2.y < HEIGHT - rectwidth) {
					bar2.y+=2;
					bar4.y+=2;
				}
				if(gc.getKeyCode() == 'A' && bar.x > 0) {
					bar.x-=2;
					bar3.x-=2;
				}
				if(gc.getKeyCode() == 'D' && bar.x < WIDTH - rectwidth) {
					bar.x+=2;
					bar3.x+=2;
				}
			}
			else if(seconds == 80000) gc.showDialog("WOW, I can't believe you've made it this far, but you can't keep going on forever. I know, I'll just accelerate the ball", "Congrats");
			else if(seconds > 80000) {						//Accelerates the ball to stop the game from going on forever for really good players
				Endgame();
				MouseControls();
			}
			else if(seconds > 88000) gc.showDialog("Congrats!!!", "Congratulation"); 
			else {											//reverts to normal when no special condition
				bar.width = rectwidth;
				bar2.height = rectwidth;
				bar3.width = rectwidth;
				bar4.height = rectwidth;
				MouseControls();
			}
		}
	}
	
	private void moveBall() {
		// TODO Auto-generated method stub
		
		for(Ball b: ballList) {						
			b.x += b.vx;
			b.y += b.vy;
			Random rand = new Random();
			
			if(b.x > (WIDTH - b.width)) {				//Ball hitting the edges
				b.vx*=-1;
				b.x = WIDTH - b.width;
				counter--;
			}
			if(b.x < 0) {
				b.vx*=-1;
				b.x = 0;
				counter--;
			}
			if(b.y > (HEIGHT - b.height)) {
				b.vy*=-1;
				b.y = HEIGHT - b.height;
				counter--;
			}
			if(b.y <= 0) {
				b.vy*=-1;
				b.y = 0;
				counter--;
			}
			
			if(b.intersects(bar)) {						//Ball hitting paddles
				b.y = HEIGHT - rectheight - b.height;	//prevents ball from clipping into paddles
				b.vy*=-1;
				if(bar1c < 250) bar1c+=ColorIncrement; 
			}
			if(b.intersects(bar2)) {
				b.x = WIDTH - rectheight - b.width;
				b.vx*=-1;
				if(bar2c < 250) bar2c+=ColorIncrement; 
			}
			if(b.intersects(bar3)) {
				b.y = b.height - rectheight;
				b.vy*=-1;
				if(bar3c < 250) bar3c+=ColorIncrement; 
			}
			if(b.intersects(bar4)) {
				b.x = b.width - rectheight;
				b.vx*=-1;
				if(bar4c < 250) bar4c+=ColorIncrement; 
			}
			
			checkCollision(b);
		}
	}
	
	void checkCollision(Ball b) {
		for(Ball ball : ballList) {

			if(ball.intersects(b)) {
				double temp = ball.vx; ball.vx = b.vx; b.vx = temp;
						temp = ball.vy; ball.vy = b.vy; b.vy = temp;
			}
		}
	}
	
	private void MouseControls() {					
		bar.x = gc.getMouseX();
		bar2.y = gc.getMouseY();
		bar3.x = gc.getMouseX();
		bar4.y = gc.getMouseY();
	}
	
	private void InverseMouse() {						//inverse mouse controls
		bar.x = gc.getMouseY();
		bar2.y = gc.getMouseX();
		bar3.x = gc.getMouseY();
		bar4.y = gc.getMouseX();
	}
	
	private void Endgame() {							//accelerates the ball during endgame
		for(Ball b: ballList) {
			b.x += b.vx;
			b.y += b.vy;
			Random rand = new Random();
			b.vx+=0.001;
			b.vy+=0.001;
		}
	}

	private void drawGraphics() {
		// TODO Auto-generated method stub
		synchronized(gc) {
				gc.clear();
				for(Ball b : ballList) {
					gc.setColor(new Color(92, 184, 0));
					gc.fillOval(b.x, b.y, b.width, b.height);
				}
			gc.setColor(new Color(bar1c, 0, 0));
			gc.fillRect(bar.x, bar.y, bar.width, bar.height);
			gc.setColor(new Color(bar2c, 0, 0));
			gc.fillRect(bar2.x, bar2.y, bar2.width, bar2.height);
			gc.setColor(new Color(bar3c, 0, 0));
			gc.fillRect(bar3.x, bar3.y, bar3.width, bar3.height);
			gc.setColor(new Color(bar4c, 0, 0));
			gc.fillRect(bar4.x, bar4.y, bar4.width, bar4.height);
			Font lives = new Font("Silom", Font.BOLD, 40);
			gc.setFont(lives);
			gc.setColor(new Color(235, 64, 52));
			gc.drawString("Lives: " + counter, 10, 50);
		
			if(counter <= 0) {
				Font score = new Font("Silom", Font.BOLD, 75);
				gc.setFont(score);
				gc.setColor(new Color(100, 0, 125));
				gc.drawString("Score: " + (int) seconds/100, 200, 200);
			}	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}