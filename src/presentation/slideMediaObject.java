package presentation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * @author Andrew Walter
 * @version 1.1
 * @since 1.0
 */
public class slideMediaObject extends JPanel implements MouseListener{

	private int branch;
	/**
	 * @param branch
	 */
	public slideMediaObject(int branch) {
		super();
		this.branch = branch;
	}
	
	/**
	 * @return branch
	 */
	public int getBranch(){
		return branch;
	}
	
	/**
	 */
	public void setBranch(int branch){
		this.branch = branch;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		
	};

}