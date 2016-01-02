package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Editor extends JPanel implements Runnable{
	Thread thread = new Thread(this);
	
	int worldWidth;
	int myWidth, myHeight;
	
	boolean first = true;
	
	final int blockSize = 16;
	
	Block[][] blocks
	
	
	public Editor(int worldWidth){
		this.worldWidth = worldWidth;
		
		this.setName("Editor");
		
		Main.panel.setPreferredSize(new Dimension((worldWidth+1)*blockSize, 18*blockSize));
		Main.frame.pack();
		
		thread.start();
	}
	
	
	
	
	public void paintComponent(Graphics g){
		if(first){
			define();
			first = false;
		}
		g.setColor(Color.white);
		g.fillRect(0, 0, myWidth, myHeight);
	}
	
	public void define(){
		myWidth = getWidth();
		myHeight = getHeight();
	}
	
	@Override
	public void run() {
		
	}
}
