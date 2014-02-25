package src;

/**
 * @author Robert Mills
 *
 */
public class TextContent {
	public enum ScriptTypeDef { superScript , subScript, normal };
	private boolean bold, italic, underlined;
	private String textString;
	private ScriptTypeDef scriptType;

	/**
	 * @param bold
	 * @param italic
	 * @param underlined
	 * @param textString
	 * @param scriptType
	 */
	public TextContent(boolean bold, boolean italic, boolean underlined,
			String textString, ScriptTypeDef scriptType) {
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.textString = textString;
		this.scriptType = scriptType;
	}
	/**
	 * @return the scriptType
	 */
	public ScriptTypeDef getScriptType() {
		return scriptType;
	}
	/**
	 * @return the bold
	 */
	public boolean isBold() {
		return bold;
	}
	/**
	 * @return the italic
	 */
	public boolean isItalic() {
		return italic;
	}
	/**
	 * @return the underlined
	 */
	public boolean isUnderlined() {
		return underlined;
	}
	/**
	 * @return the textString
	 */
	public String getTextString() {
		return textString;
	}

}
