package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor extends JPanel{
	static Point mse;
	
	static int worldWidth;

	static int worldHeight = 18;
	int myWidth, myHeight;
	static int x1, y1, x2, y2;
	static boolean rightClicked = false;
	
	boolean first = true;
	
	final static int blockSize = 16;
	
	static Block[][] blocks;
	
	static Rectangle[] menuButtons;
	
	static int selectedID = 0;
	
	Image saveIcon;
	
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
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, myWidth, myHeight);
		
		for(int j = 0;j < worldHeight;j++){
			for(int i = 0;i < worldWidth;i++){
				blocks[i][j].draw(g);
			}
		}
		
		drawMenu(g);
	}
	
	public static void click(int mb){
		System.out.println(mb);
		if(mb == 1){
			for(int j = 0;j < worldHeight;j++){
				for(int i = 0;i < worldWidth;i++){
					if(blocks[i][j].contains(mse)){
						blocks[i][j].ID = selectedID;
						System.out.println(i + "   " + j);
					}
				}
			}
			for(int i = 0;i < Block.squareColor.length;i++){
				if(menuButtons[i].contains(mse)){
					selectedID = i;
					System.out.println(i);
				}
			}
			if(menuButtons[menuButtons.length-1].contains(mse)){
				FileHandler.save();
			}
		}
		else if(mb==3){
			if(!rightClicked){
				for(int j = 0;j < worldHeight;j++){
					for(int i = 0;i < worldWidth;i++){
						if(blocks[i][j].contains(mse)){
							x1 = i;
							y1 = j;
							rightClicked=true;
							blocks[i][j].selected = true;
							break;
						}
					}
				}
			}
			else if(rightClicked){
				for(int j = 0;j < worldHeight;j++){
					for(int i = 0;i < worldWidth;i++){
						if(blocks[i][j].contains(mse)){
							x2 = i;
							y2 = j;
							setSelection();
							rightClicked = false;
							break;
						}
					}
				}
			}
		}
	}
	
	public static void setSelection(){
		if(x2 > x1){
			int tempx = x1;
			x1 = x2;
			x2 = tempx;
		}
		if(y2 > y1){
			int tempy = y1;
			y1 = y2;
			y2 = tempy;
		}
		
		for(int j = y2;j<=y1;j++){
			for(int i = x2;i<=x1;i++){
				blocks[i][j].ID = selectedID;
				blocks[i][j].selected = false;
			}
		}
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
		
		saveIcon = new ImageIcon("res/save.png").getImage();
		
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
		for(int i = 0;i < Block.squareColor.length;i++){
			g.setColor(Block.squareColor[i]);
			g.fillRect(0, menuButtons[i].y, blockSize, blockSize);
		}
		
		g.drawImage(saveIcon, menuButtons[menuButtons.length-1].x, menuButtons[menuButtons.length-1].y, blockSize, blockSize, null);
	}
}
