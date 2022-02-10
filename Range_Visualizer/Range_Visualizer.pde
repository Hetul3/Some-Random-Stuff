float x = 0;
float y = 0;
float y_in = -300;
float x_in = 0;
float v = 75;
float degree = 45;
float g = -9.81;
float t = 0;
float dt = 0.1;
float ball = 25;
float prevx = -1;
float prevy = -1;
/*"a" is the amount you want to change how much to compress
x distance in order to fit more y on the screen */
float a = 1;

PGraphics trail;

void setup() {
  size(1000,500);
  trail = createGraphics(width, height);
  trail.beginDraw();
  trail.background(255);
  trail.endDraw();
}

void draw() {
  float ang = degree * PI / 180;
  
    x = (x_in + v * cos(ang) * t)/a;
    y = y_in + v * sin(ang) * t + (0.5) * g * t * t;
      

  t += dt;
         
  image(trail, 0, 0);
  stroke(0);
  strokeWeight(1);
  
  fill(25, 25, 100);
  line(prevx, prevy, x, y);
  ellipse(x, -y, ball, ball);
   
    trail.beginDraw();
    trail.strokeWeight(2);
    trail.stroke(255, 0, 0);
   
    if(frameCount > 1) {
      stroke(0);
      strokeWeight(5);
      trail.line(prevx, -prevy, x, -y);
    }
    trail.endDraw();
    
    prevx = x;
    prevy = y;
  
 if (frameCount > 10 && height <= -y) {
    noLoop(); 
    float range = (v * v * sin(2 * ang))/(Math.abs(g));
   
    if(y_in > -500) {
       range = v * cos(ang) * t;
    }
    
    float mxh = ((v * v * sin(ang) * sin(ang)) / (2 * -g)) - y_in;
    textSize(50);
    text("Range: " + range + " units", 30, 50);
    text("Max Height: " + mxh + " units", 30, 100);
 
 }
  
}
