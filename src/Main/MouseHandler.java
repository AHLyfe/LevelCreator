package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent me) {
		Editor.mse = me.getPoint();
		System.out.println(Editor.mse);
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		Editor.mse = me.getPoint(); 
		System.out.println(Editor.mse);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println("?");
		System.out.println(Editor.mse);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
