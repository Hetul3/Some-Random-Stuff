//Hetul, ball class for the simple game

package Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle{
	
	double vx = 1.0;
	double vy = 1.0;
	
	Ball() {
		width = height = 50;
		x = (int) (Math.random() * (BallBounce2.WIDTH - width));
		y = (int)(Math.random() * (BallBounce2.HEIGHT - width));
	}
}