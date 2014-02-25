package src;

/**
 * @author Robert Mills
 *
 */
public class TextContent {
	public enum ScriptTypeDef { superScript , subScript, normal };
	private boolean bold, italic, underlined, newLine, bulletPoint;
	private String textString;
	private ScriptTypeDef scriptType;


	public TextContent() {
	}
	/**
	 * @return bulletPoint returns true if the text is the first part of a bullet point
	 */
	public boolean isBulletPoint() {
		return bulletPoint;
	}
	/**
	 * @param bulletPoint set true if the text forms the first part of bullet point
	 */
	public void setBulletPoint(boolean bulletPoint) {
		this.bulletPoint = bulletPoint;
	}
	/**
	 * @return the scriptType
	 */
	public ScriptTypeDef getScriptType() {
		return scriptType;
	}
	/**
	 * @return returns whether or not the text marks the start of a new line
	 */
	public boolean isNewLine() {
		return newLine;
	}
	/**
	 * @param newLine sets whether or not the text is the start of a new line
	 */
	public void setNewLine(boolean newLine) {
		this.newLine = newLine;
	}
	/**
	 * @param bold sets the text bold
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	/**
	 * @param italic sets the text italic
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}
	/**
	 * @param underlined sets the text underlined
	 */
	public void setUnderlined(boolean underlined) {
		this.underlined = underlined;
	}
	/**
	 * @param textString the actual text string itself
	 */
	public void setTextString(String textString) {
		this.textString = textString;
	}
	
	/**
	 * @param scriptType sets whether the text is super / sub or normal script
	 */
	public void setScriptType(ScriptTypeDef scriptType) {
		this.scriptType = scriptType;
	}
	/**
	 * @return bold returns true if the text iws bold 
	 */
	public boolean isBold() {
		return bold;
	}
	/**
	 * @return italic returns true if the text is italic
	 */
	public boolean isItalic() {
		return italic;
	}
	/**
	 * @return underlined returns true if the text is underlined
	 */
	public boolean isUnderlined() {
		return underlined;
	}
	/**
	 * @return textString the string content of the text
	 */
	public String getTextString() {
		return textString;
	}

}
