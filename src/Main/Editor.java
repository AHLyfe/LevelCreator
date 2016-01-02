package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor extends JPanel{
	static Point mse;
	
	int worldWidth, worldHeight = 18;
	int myWidth, myHeight;
	
	boolean first = true;
	
	final static int blockSize = 16;
	
	Block[][] blocks;
	
	Rectangle[] menuButtons;
	
	int ID = 0;
	
	public Editor(int worldWidth){
		this.worldWidth = worldWidth;
		
		this.setName("Editor");
		
		Main.panel.setPreferredSize(new Dimension((worldWidth+2)*blockSize, worldHeight*blockSize));
		Main.frame.pack();
		
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
	}
	
	
	public void paintComponent(Graphics g){
		if(first){
			define();
			first = false;
		}
		g.setColor(Color.white);
		g.fillRect(0, 0, myWidth, myHeight);
		
		for(int j = 0;j < worldHeight;j++){
			for(int i = 0;i < worldWidth;i++){
				blocks[i][j].draw(g);
			}
		}
		
		drawMenu(g);
	}
	
	public void click(int mb){
		
	}
	
	public void define(){
		myWidth = getWidth();
		myHeight = getHeight();
		
		initialiseMenu();
		
		blocks = new Block[worldWidth][worldHeight];
		for(int j = 0;j < worldHeight;j++){
			for(int i = 0;i < worldWidth;i++){
				blocks[i][j] = new Block(i,j);
			}
		}
		//Set focusable so mouseMotionListener and keyListener can detect and focus on panel
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	void initialiseMenu(){
		menuButtons = new Rectangle[worldHeight];
		
		for(int i = 0;i < menuButtons.length;i++){
			menuButtons[i] = new Rectangle(0, i*blockSize, blockSize, blockSize);
		}
	}
	
	void drawMenu(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, menuButtons[0].y, blockSize, blockSize);
		
		g.setColor(Color.black);
		g.fillRect(0, menuButtons[1].y, blockSize, blockSize);
	}
}
