package presentation;

import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * @author Andrew Walter
 * @version 1.1
 * @since 1.0
 */
public class slideMediaObject extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer branch;
	private Integer chapterBranch;
	private Integer startTime;
	private Integer finishTime;


	private boolean isText;


	public slideMediaObject(Integer branch,Integer duration,Integer startTime, Integer chapterBranch) {
		super();
		this.setOpaque(false);
		this.setLayout(null);
		this.branch = branch;
		this.chapterBranch = chapterBranch;
		if(duration == -1 || duration == 0)
		{
			this.finishTime = -1;
		}
		else
		{
			this.finishTime = duration + startTime;
		}
		this.startTime = startTime;


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

	}

	public void setFinishTime(Integer finishTime) {
		// TODO Auto-generated method stub
		this.finishTime = finishTime;
	};

	public Integer getFinishTime() {
		// TODO Auto-generated method stub
		return finishTime;
	}

	/**
	 * @return the startTime
	 */
	public Integer getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	/**
	 * set to verify this object contains text 
	 * @param isText
	 */
	public void setText(boolean isText) {
		this.isText = isText;
	}

	public boolean isText() {
		// TODO Auto-generated method stub
		return isText;
	}

	/**
	 * @return the chapterBranch
	 */
	public Integer getChapterBranch() {
		return chapterBranch;
	}

	/**
	 * @param chapterBranch the chapterBranch to set
	 */
	public void setChapterBranch(Integer chapterBranch) {
		this.chapterBranch = chapterBranch;
	};

}