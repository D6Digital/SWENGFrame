/**
 * 
 */
package systemModule;

import java.util.ArrayList;

import bookModule.Book;

/**
 * Stores multiple GameSystems
 * @author Robert Mills
 */
public class SystemCollection {
	static ArrayList<GameSystem> systems = new ArrayList<GameSystem>(0);
	public SystemCollection() {

	}
	/**
	 * @param e the system to add to the collection
	 * @return true if the add successful
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(GameSystem e) {
		return systems.add(e);
	}
	/**
	 * @param index the index of the system to get
	 * @return the requested system
	 * @see java.util.ArrayList#get(int)
	 */
	public GameSystem get(int index) {
		return systems.get(index);
	}
	/**
	 * @return the size of the collection
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return systems.size();
	}
	
	/**
	 * gets the list of RPG systems stored
	 * @return systems
	 */
	public static ArrayList<GameSystem> getList() {
		// TODO Auto-generated method stub
		return systems;
	}
	
}
