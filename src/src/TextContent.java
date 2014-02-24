package src;

/**
 * @author Robert Mills
 *
 */
public class TextContent {
	private boolean bold, italic, underlined;
	private String textString;
	/**
	 * @param bold
	 * @param italic
	 * @param underlined
	 * @param textString
	 */
	public TextContent(boolean bold, boolean italic, boolean underlined,
			String textString, String font) {
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.textString = textString;
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
