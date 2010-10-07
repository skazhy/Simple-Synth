import ddf.minim.*;

Minim minim;
ArrayList synth;

int counter=0, speed=350;
int[][] squares = new int[8][8];

void setup() {
  background(200);
  size(296,296);
  
  minim = new Minim(this);
  synth = new ArrayList();
  
  for (int i=0; i<8; i++) synth.add(new Tone(i));
}

void draw() {
  for (int x=0; x<8; x++) {
    for (int y=0; y<8; y++) {
      fill(200);
      if(squares[x][y] == 1) fill(50);
      if (counter==x) fill(220);
      if (squares[x][y] == 1 && counter==x) {
        fill(255);
        Tone tone = (Tone) synth.get(y);
        tone.playTone();
      }
      rect(10+x*35,10+35*y,30,30);
    }
  }
  
  counter++;
  delay(speed);
  counter%=8;
}

void mouseClicked() {
  int mx = mouseX, my = mouseY;
  for (int x=0; x<8; x++) {
    for (int y=0; y<8; y++) {
      if (mx < 40+x*35 && mx > 10+x*35 && my > 10+35*y && my <= 40+35*y) squares[x][y] = abs(squares[x][y] -1);
    }
  }
}

void keyTyped() {
  if(key=='1' && speed < 1001) speed+=100;
  if(key=='2' && speed > 99 ) speed-=100;
}

void stop()
{
  for (int i=synth.size()-1; i <=0; i--) {
    Tone tone = (Tone) synth.get(i);
    tone.closeTone();
    synth.remove(i);
  }
  minim.stop();
  super.stop();
}
