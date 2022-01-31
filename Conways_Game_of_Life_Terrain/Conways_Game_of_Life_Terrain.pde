int cell = 5;
int hgt = 600;
int wdt = 600;
int cols = wdt/cell;
int rows = hgt/cell;
int [][] board = new int[cols][rows];
{
  for (int y=0; y<rows; y++) {
    for (int x=0; x<cols; x++) {
      board[x][y] = int(random(2));
    }
  }
}

void setup() {
  size(600, 650);
  frameRate(12);
}

void draw() {
  background(0);
  int [][] board2 = new int[cols][rows];
  for (int y=1; y<rows-1; y++) {
    for (int x=1; x<cols-1; x++) {
      int neighbours = countNeighbours(x,y);
      board2[x][y] = ruleOfLife(board[x][y], neighbours);
    }
  }
  board = board2;
  drawBoard();
  textSize(40);
  fill(220);
  text(frameCount + " Generations", 20, 630);
  
}
  int countNeighbours(int x, int y) {
    int neighbours = 0;
    for (int i=-1; i <= 1; i++) {
      for (int j=-1; j<=1; j++) {
        neighbours += board[x+j][y+i];
  }
 }
 neighbours -= board[x][y];
 return (neighbours);
}

 int ruleOfLife (int status, int neighbours) {
  if(status == 0 && neighbours > 4) return(1);
  else if (status == 1 && neighbours < 3) return(0);
  else if (status == 1 && neighbours == 3) return(0);
   
   /* Original Rules
  if(status == 1 && neighbours > 3) return (0);
  else if(status == 1 && neighbours < 2) return (0);
  else if (status == 0 && neighbours == 3) return(1);
  else if (status == 0 && neighbours > 3) return (Math.round(random(1))); */
  else return(status);
 
}


void drawBoard() {
  for (int y=0; y<rows; y++) {
    for (int x=0; x<cols; x++) {
      if (board[x][y]==1) {
        fill(255);
      }
      else {
        fill(1);
      }
      ellipse(x*cell, y*cell,cell,cell);
    }
  }
}
  

void mousePressed() {
  noLoop();
  }

void mouseReleased() {
  loop();
}
