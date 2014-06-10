package systemModule;

import java.util.ArrayList;

/**
 * Stores multiple GameSystems
 * @author Robert Mills
 */
public class SystemCollection {
	static ArrayList<GameSystem> systems = new ArrayList<GameSystem>(0);
	public SystemCollection() {

	}
	
	/**
	 * Adds a game system to the collection
	 * @param e, the GameSystem to be added
	 * @return systems.add(e), returns true if add was successful
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public Boolean add(GameSystem e) {
		return systems.add(e);
	}
	
	/**
	 * Returns the system at a specified index in the collection
	 * @param index, the index possition in the collection
	 * @return systems.get(index), the GameSystem at the index possition
	 * @see java.util.ArrayList#get(int)
	 */
	public GameSystem get(int index) {
		return systems.get(index);
	}
	
	/**
	 * returns the size of the collection
	 * @return size, the number of entries in the collection
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return systems.size();
	}
	
	/**
	 * gets the list of RPG systems stored
	 * @return systems, an ArrayList<GameSystem> of all the systems in the collection
	 */
	public static ArrayList<GameSystem> getList() {
		return systems;
	}
	
}
