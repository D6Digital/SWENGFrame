package presentation;

/**
 * @author Robert Mills
 *
 */
public class TextContent {
	public enum ScriptTypeDef { superScript , subScript, normal };
	private Boolean bold, italic, underlined, hyperlink = false;
	private Integer branch = null;
	private String textString = null;
	private ScriptTypeDef scriptType = ScriptTypeDef.normal;
	private Integer chapterBranch = null;

	/**
	 * 
	 * @param scriptType
	 * @param bold
	 * @param italic
	 * @param underlined
	 * @param hyperlink
	 * @param textString
	 */
	public TextContent(ScriptTypeDef scriptType, Boolean bold, Boolean italic,
			Boolean underlined, Boolean hyperlink, String textString) {
		this.scriptType = scriptType;
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.hyperlink = hyperlink;
		this.textString = textString;
	}

	/**
	 * 
	 */
	public TextContent() {
	}

	/**
	 * @return the scriptType (superscript, subscript, normal)
	 */
	public ScriptTypeDef getScriptType() {
		return scriptType;
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
	 * @return bold returns true if the text is bold 
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

	/**
	 * @return true if a hyperlink
	 */
	public boolean isHyperlink() {
		return hyperlink;
	}

	/**
	 * @param hyperlink true if hyperlink
	 */
	public void setHyperlink(boolean hyperlink) {
		this.hyperlink = hyperlink;
	}


	/**
	 * @return the index of the slide to branch to
	 */
	public Integer getBranch() {
		return branch;
	}


	/**
	 * @param branch the branch number to be set
	 */
	public void setBranch(int branch) {
		this.branch = branch;
	}


	/**
	 * @param chapterBranch Branch to a given chapter
	 */
	public void setChapterBranch(int chapterBranch) {
		this.chapterBranch = chapterBranch;
	}

	/**
	 * @return the branch to a given chapter
	 */
	public Integer getChapterBranch() {
		return chapterBranch;
	}


}
