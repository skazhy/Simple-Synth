import processing.core.*; 
import processing.xml.*; 

import ddf.minim.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class sequencer_oop extends PApplet {



Minim minim;
ArrayList synth;

int counter=0, speed=350;
int[][] squares = new int[8][8];

public void setup() {
  background(200);
  size(296,296);
  
  minim = new Minim(this);
  synth = new ArrayList();
  
  for (int i=0; i<8; i++) synth.add(new Tone(i));
}

public void draw() {
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

public void mouseClicked() {
  int mx = mouseX, my = mouseY;
  for (int x=0; x<8; x++) {
    for (int y=0; y<8; y++) {
      if (mx < 40+x*35 && mx > 10+x*35 && my > 10+35*y && my <= 40+35*y) squares[x][y] = abs(squares[x][y] -1);
    }
  }
}

public void keyTyped() {
  if(key=='1' && speed < 1001) speed+=100;
  if(key=='2' && speed > 99 ) speed-=100;
}

public void stop()
{
  for (int i=synth.size()-1; i <=0; i--) {
    Tone tone = (Tone) synth.get(i);
    tone.closeTone();
    synth.remove(i);
  }
  minim.stop();
  super.stop();
}
class Tone {
  AudioSample atone;
  
  Tone(int a) {
    String tn = "tone"+a+".wav";
    atone = minim.loadSample(tn,2048);
  }
  
  public void playTone() {
    atone.trigger();
  }
  
  public void closeTone() {
    atone.close();
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#DFDFDF", "sequencer_oop" });
  }
}
