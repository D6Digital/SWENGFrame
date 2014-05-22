/**
 * 
 */
package presentation;

import java.util.ArrayList;

/**
 * @author rjm529
 *
 */
public class Collection {
	ArrayList<Presentation> presentations = new ArrayList<Presentation>(0);

	/**
	 * @param arg0
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(Presentation arg0) {
		return presentations.add(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Presentation get(int arg0) {
		return presentations.get(arg0);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return presentations.size();
	}
	
	public ArrayList<Presentation> getPresentationList() {
		return presentations;
	}
}
