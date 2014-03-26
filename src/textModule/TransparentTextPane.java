package textModule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextPane;

public class TransparentTextPane extends JTextPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransparentTextPane() {
		this.setOpaque(false);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255,255,255,0));
		Insets insets = getInsets();
		int x = insets.left;
        int y = insets.top;
        int width = getWidth() - (insets.left + insets.right);
        int height = getHeight() - (insets.top + insets.bottom);
        g.fillRect(x, y, width, height);
        super.paintComponent(g);
	}
	
	
	

}
