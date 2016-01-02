package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	int xCo, yCo;
	
	public Block(int i, int j){
		xCo = i;
		yCo = j;
		
		width = Editor.blockSize;
		height = Editor.blockSize;
		
		x = (xCo+2)*Editor.blockSize;
		y = yCo*Editor.blockSize;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.drawRect(x, y, width, height);
	}
}
