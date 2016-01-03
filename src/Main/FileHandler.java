package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {
	static PrintWriter writer;

	public static void save() {
		File filepath = getFileLocation();
		
		try {
			writer = new PrintWriter(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int j = 0;j < Editor.worldHeight;j++){
			for(int i = 0;i < Editor.worldWidth;i++){
				writer.print(Editor.blocks[i][j].ID + " ");
			}
			writer.println("");
		}
		writer.close();
		
		filepath = new File(filepath.getAbsolutePath() + "info");
		try {
			writer = new PrintWriter(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.println(Editor.worldWidth);
		
		System.out.println("Level saved");
	}
	
	public static File getFileLocation(){
		File selectedFile = null;
		
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("/User/" + System.getProperty("user.name")));
		jFileChooser.setFileFilter(new FileNameExtensionFilter(".level", ".level"));
		
		int result = jFileChooser.showOpenDialog(new JFrame("Choose File"));
		
		if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());	
		}

		return selectedFile;
	}
}
