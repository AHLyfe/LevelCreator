package Main;

import java.io.File;

import javax.swing.JFileChooser;

public class FileHandler {
	

	public static void save() {
		String filepath = getFileLocation();
		
	}
	
	public static String getFileLocation(){
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("/User/" + System.getProperty("user.name")));
		
		
		
	}
}
