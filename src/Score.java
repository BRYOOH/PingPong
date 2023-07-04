import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Score {
	
	int player1;
	int player2;
	static int SCREEN_WIDTH;
	static int SCREEN_HEIGHT;
	Score(int SCREEN_WIDTH,int SCREEN_HEIGHT){
		Score.SCREEN_HEIGHT=SCREEN_HEIGHT;
		Score.SCREEN_WIDTH=SCREEN_WIDTH;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.ITALIC,60));
		
		g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (SCREEN_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (SCREEN_WIDTH/2)+20, 50);
	}
}
