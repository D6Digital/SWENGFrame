package textModule;


/**
 * 
 * @author TRiBE
 *
 */
public class TextBody 
{

	private String text;
	private boolean bold = false;
	private boolean italic = false;
	private boolean underlined = false;
	private int branch = -1;

	/**
	 * @return the text
	 */
	public String getText() 
	{
		return text;
	}

	/**
	 * @param set the text
	 */
	public void setText(String s)
	{
		text = s;
	}

	/**
	 * @return bold
	 */
	public boolean getBold()
	{
		return bold;
	}

	/**
	 * @param set bold
	 */
	public void setBold(boolean b) 
	{
		bold = b;

	}

	/**
	 * @return italic
	 */
	public boolean getItalic()
	{
		return italic;
	}

	/**
	 * @param set italic
	 */
	public void setItalic(boolean i) 
	{
		italic = i;

	}

	/**
	 * @return underlined
	 */
	public boolean getUnderlined()
	{
		return underlined;
	}

	/**
	 * @param set underlined
	 */
	public void setUnderlined(boolean u) 
	{
		underlined = u;

	}

	/**
	 * @return the branch
	 */
	public int getBranch()
	{
		return branch;
	}

	/**
	 * @param set the branch
	 */
	public void setBranch(int branch) 
	{
		this.branch = branch;

	}

}
