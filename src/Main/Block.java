package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	int xCo, yCo;
	public int ID = 0;
	public static final Color[] squareColor = {Color.WHITE, Color.BLACK, new Color(100,200,255), Color.GREEN, Color.CYAN};
	boolean selected = false;
	
	public Block(int i, int j){
		xCo = i;
		yCo = j;
		
		width = Editor.blockSize;
		height = Editor.blockSize;
		
		x = (xCo+2)*Editor.blockSize;
		y = yCo*Editor.blockSize;
	}
	
	public void draw(Graphics g){
		g.setColor(squareColor[ID]);
		g.fillRect(x, y, width, height);
			
		g.setColor(Color.GREEN);
		g.drawRect(x, y, width, height);
		
		if(selected){
			g.setColor(new Color(0,255,255,150));
			g.fillRect(x, y, width, height);
		}
	}
}
