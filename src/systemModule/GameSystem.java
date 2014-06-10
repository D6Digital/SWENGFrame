/**
 * 
 */
package systemModule;

/**
 * Holds data one of the RPG systems the software can access
 * @author Robert Mills
 */
public class GameSystem {
	String name, filename, description, logoFileName;
	GameSystem(){
		
	}
	/**
	 * gets the name of the systems
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets the name of the system
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * gets the system's filename
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * sets the system's filename
	 * @param filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * gets the description of the systems
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * sets the description of the system
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * gets the filename of the system's logo
	 * @return logoFileName
	 */
	public String getLogoFileName() {
		return logoFileName;
	}
	/**
	 * sets the filename of the system's logo
	 * @param logoFileName
	 */
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	
}
