package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.control.ScrollBar;

public class Editor extends JPanel{
	static Point mse;
	
	static int worldWidth;

	static int worldHeight = 18;
	int myWidth, myHeight;
	static int x1, y1, x2, y2;
	static boolean rightClicked = false;
	
	boolean first = true;
	static boolean load = false;
	
	final static int blockSize = 16;
	
	static Block[][] blocks;
	
	static Rectangle[] menuButtons;
	
	static int selectedID = 0;
	
	Image saveIcon;
	
	public Editor(){
		this.setName("Editor");
		if(worldWidth < 64){
			Main.panel.setPreferredSize(new Dimension((worldWidth+2)*blockSize, worldHeight*blockSize));
		}
		else{
			Main.panel.setPreferredSize(new Dimension((64+2)*blockSize, (worldHeight+1)*blockSize));
		}
		Main.frame.pack();
		
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
	}
	
	public Editor(int worldWidth){
		Editor.worldWidth = worldWidth;
		
		this.setName("Editor");
		if(worldWidth < 64){
			Main.panel.setPreferredSize(new Dimension((worldWidth+2)*blockSize, worldHeight*blockSize));
		}
		else{
			Main.panel.setPreferredSize(new Dimension((64+2)*blockSize, (worldHeight+1)*blockSize));
		}
		Main.frame.pack();
		
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
	}
	
	
	public void paintComponent(Graphics g){
		if(first){
			define();
			first = false;
		}
		
		for(int j = 0;j < worldHeight;j++){
			for(int i = 0;i < worldWidth;i++){
				blocks[i][j].draw(g);
			}
		}
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, 2*blockSize, myHeight - blockSize);
		
		drawMenu(g);
	}
	
	public static void click(int mb){
		System.out.println(mb);
		if(mb == 1){
			if(mse.getX()>blockSize*2){
				for(int j = 0;j < worldHeight;j++){
					for(int i = 0;i < worldWidth;i++){
						if(blocks[i][j].contains(mse)){
							blocks[i][j].ID = selectedID;
							System.out.println(i + "   " + j);
						}
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
	
	public void updateBlocks(int offset){
		System.out.println(offset);
		
		for(int j = 0;j < worldHeight;j++){
			for(int i  = 0;i < worldWidth;i++){
				blocks[i][j].x = (blocks[i][j].xCo+2)*blockSize - offset*blockSize;
			}
		}
	}
	
	public void define(){
		myWidth = getWidth();
		myHeight = getHeight();
		
		initialiseMenu();
		
		if(!load){
			defineBlocks();
		}
		
		saveIcon = new ImageIcon("res/save.png").getImage();
		
		if(worldWidth > 64){
			Scrollbar sb = new Scrollbar(Scrollbar.HORIZONTAL, 0, 64, 0, worldWidth);
			sb.setLocation(0, 18*blockSize);
			System.out.println(sb.getLocation());
			sb.setPreferredSize(new Dimension(myWidth, blockSize));
			sb.setSize(myWidth, blockSize);
			sb.setBackground(new Color(230,230,230));
			sb.addAdjustmentListener(new AdjustmentListener(){
				@Override
				public void adjustmentValueChanged(AdjustmentEvent ae) {
					updateBlocks(ae.getValue());
				}
			});
			add(sb);
		}
		
		//Set focusable so mouseMotionListener and keyListener can detect and focus on panel
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public static void defineBlocks(){
		blocks = new Block[worldWidth][worldHeight];
		for(int j = 0;j < worldHeight;j++){
			for(int i = 0;i < worldWidth;i++){
				blocks[i][j] = new Block(i,j);
			}
		}
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
