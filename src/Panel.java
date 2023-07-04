import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {
	
	static final int SCREEN_WIDTH = 800;
	static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH*0.555);
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	static final int BALL_DIAMETER = 20;
	Thread thread;
	Image image;
	Graphics graphics;
	Random random;
	Paddles paddle1;
	Paddles paddle2;
	Ball ball;
	Score score;
	
	Panel(){
		newBall();
		newPaddles();
		score= new Score(SCREEN_WIDTH,SCREEN_HEIGHT);
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(new ActionListener());
		this.setBackground(Color.black);
		
		thread= new Thread(this);
		thread.start();
	}
	
	public void newBall() {
		ball = new Ball((SCREEN_WIDTH/2)-(BALL_DIAMETER/2),(SCREEN_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
	}

	public void newPaddles() {
		paddle1=new Paddles(0,(SCREEN_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2=new Paddles((SCREEN_WIDTH-PADDLE_WIDTH),(SCREEN_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	public void paintComponent(Graphics g) {
		image= createImage(getWidth(),getHeight());
		graphics= image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0,0,this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		
	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void checkCollision() {
		//checks the ball and the top and bottom borders
		if(ball.y<=0) 
			ball.setYDirection(-ball.yVelocity);
			
		if(ball.y>=SCREEN_HEIGHT-BALL_DIAMETER) 
			ball.setYDirection(-ball.yVelocity);
			
		//checks if ball intersects with paddles
		
		if(ball.intersects(paddle1)) {
			ball.xVelocity=Math.abs(ball.xVelocity);
		    ball.xVelocity++;
		    if(ball.yVelocity>0)
		    	ball.yVelocity++;
		    else
		    	ball.yVelocity--;
			
			
			ball.setYDirection(ball.yVelocity);
			ball.setXDirection(ball.xVelocity);
			}
		
		if(ball.intersects(paddle2)) {
			ball.xVelocity=Math.abs(ball.xVelocity);
		    ball.xVelocity++;
		    if(ball.yVelocity>0)
		    	ball.yVelocity++;
		    else
		    	ball.yVelocity--;
			
			
			ball.setYDirection(ball.yVelocity);
			ball.setXDirection(-ball.xVelocity);
			}
			
		//checks paddles and the window borders
		if(paddle1.y <=0)
			paddle1.y=0;
		if(paddle1.y>=(SCREEN_HEIGHT-PADDLE_HEIGHT))
			paddle1.y=SCREEN_HEIGHT-PADDLE_HEIGHT;
	
		if(paddle2.y <=0)
			paddle2.y=0;
		if(paddle2.y>=(SCREEN_HEIGHT-PADDLE_HEIGHT))
			paddle2.y=SCREEN_HEIGHT-PADDLE_HEIGHT;	
		
		//checks if player 2 scored
		if(ball.x<=0) {
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("Player 2:" + score.player2);
		}
		
		//checks if player 1 scored
		if(ball.x>=SCREEN_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("Player 1:" + score.player1);
		}
		
	}
	
	public void run() {
		//gameLoop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns= 1000000000/amountOfTicks;
		double delta=0;
		while(true) {
			long now= System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime= now;
			if(delta>=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class ActionListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
