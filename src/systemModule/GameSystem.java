/**
 * 
 */
package systemModule;

/**
 * @author Robert Mills
 *
 */
public class GameSystem {
	String name, filename, description, logoFileName;
	GameSystem(){
		
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the logoFileName
	 */
	public String getLogoFileName() {
		return logoFileName;
	}
	/**
	 * @param logoFileName the logoFileName to set
	 */
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	
}
