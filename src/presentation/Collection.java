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
	 * @return a succesful addition
	 * @see java.util.ArrayList#add(java.lang.Object)
	 * Add a presentation to the collection
	 */
	public boolean add(Presentation arg0) {
		return presentations.add(arg0);
	}

	/**
	 * Returns an individual presentation
	 * @param arg0
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Presentation get(int arg0) {
		return presentations.get(arg0);
	}

	/**
	 * @return the number of presentations
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return presentations.size();
	}
	
	/**
	 * @return all presentations
	 */
	public ArrayList<Presentation> getPresentationList() {
		return presentations;
	}
}
