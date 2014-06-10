package systemModule;

/**
 * Holds data one of the RPG systems the software can access
 * @author Robert Mills
 */
public class GameSystem {
	String name, fileName, description, logoFileName;
	GameSystem(){
	}
	
	/**
	 * gets the name of the systems
	 * @return name, the name of the GameSystem
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the name of the system
	 * @param name, the name to be given to the GameSystem
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * gets the system's filename
	 * @return filename, the filename of the GameSystem
	 */
	public String getFilename() {
		return fileName;
	}
	
	/**
	 * sets the system's filename
	 * @param filename, the filename to be given to the GameSystem
	 */
	public void setFilename(String filename) {
		this.fileName = filename;
	}
	
	/**
	 * gets the description of the systems
	 * @return description, the description of the GameSystem
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * sets the description of the system
	 * @param description, the description to be given to the GameSystem
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * gets the filename of the system's logo
	 * @return logoFileName, the filename of the logo for the GameSystem
	 */
	public String getLogoFileName() {
		return logoFileName;
	}
	
	/**
	 * sets the filename of the system's logo
	 * @param logoFileName, the filename of the logo to be attached to the GameSystem
	 */
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	
}
