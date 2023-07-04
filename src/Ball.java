import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Ball extends Rectangle{
	
	Random random;
	int yVelocity;
	int xVelocity;
	int speed=2;
	
	Ball(int x, int y, int width,int height){
		super(x,y,width,height);
		random= new Random();
		int randomY= random.nextInt(2);
		if(randomY==0)
			randomY--;
		setYDirection(randomY*speed);
		
		int randomX= random.nextInt(2);
		if(randomX==0)
			randomX--;
		setXDirection(randomX*speed);
		
		
	}
	
	public void setYDirection(int randomY) {
		yVelocity= randomY;
	}

	public void setXDirection(int randomX) {
		xVelocity= randomX;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}
	
	public void move() {
		y+=yVelocity;
		x+=xVelocity;
		
	}
	
		
}
