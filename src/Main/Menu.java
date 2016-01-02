package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel implements Runnable, ActionListener{
	Thread thread = new Thread(this);
	
	int myWidth, myHeight;
	
	boolean first = true;
	
	JButton ok;
	JLabel levelWidthLabel;
	JTextField levelWidth;
	
	public Menu(){
		ok = new JButton();
		levelWidthLabel = new JLabel();
		levelWidth = new JTextField();
		
		levelWidthLabel.setText("   Level Width (Blocks) --->");
		
		add(levelWidthLabel);
		
		add(levelWidth);
		
		
		ok.setText("Ok");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		
		add(ok);
	
		this.setLayout(new GridLayout(2,2,4,4));
		
		this.setName("Menu");
		
		thread.start();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("ok")){
			if(isNumeric(levelWidth.getText())){
				Main.setComponent(new Editor(Integer.parseInt(levelWidth.getText())));
			}
			else{
				JOptionPane.showMessageDialog(this, "You have not entered a valid integer");
			}
		}	
	}
	
	public static boolean isNumeric(String str)  
	{  
	int i;
	  try  
	  {  
	    i = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  if(i<1){
		  return false;
	  }
	  return true;  
	}
	
	
	public void paintComponent(Graphics g){
		if(first){
			define();
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, myWidth, myHeight);
	}
	
	public void define(){
		myWidth = this.getWidth();
		myHeight = this.getHeight();
	}




	@Override
	public void run() {
		repaint();
	}
}
