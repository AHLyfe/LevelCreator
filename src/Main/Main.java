package Main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	static JFrame frame;
	static JPanel panel;
	static Dimension size = new Dimension(400,300);
	
	public static void main(String[] args){
		createFrame();
	}
	
	public static void setComponent(Component component){
		panel.removeAll();
		panel.add(component);
		panel.validate();
		Component[] test = panel.getComponents();
		for(Component i:test){
			System.out.println(i.getName());
		}
	}
	
	public static void createFrame(){
		frame = new JFrame("Level Editor");
		frame.setSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(1,1,0,0));
		frame.setResizable(false);
		
		//Repaint thread - should probably have a static thread that can stop and start but whatever
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(14);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(frame.getComponentCount() != 0){
						panel.repaint();
					}
				}
			}
		}.start();
		
		panel = new JPanel(new CardLayout());
		frame.add(panel);
		
		setComponent(new Menu());
		
		frame.setVisible(true);
	}
}
