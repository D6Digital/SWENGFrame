package textModule;

public class TextBody 
{

	private String text;
	private boolean bold = false;
	private boolean italic = false;
	private boolean underlined = false;
	private int branch = -1;

	public String getText() 
	{
		return text;
	}
	
	public void setText(String s)
	{
		text = s;
	}
	
	public boolean getBold()
	{
		return bold;
	}

	public void setBold(boolean b) 
	{
		bold = b;
		
	}
	
	public boolean getItalic()
	{
		return italic;
	}

	public void setItalic(boolean i) 
	{
		italic = i;
		
	}
	
	public boolean getUnderlined()
	{
		return underlined;
	}

	public void setUnderlined(boolean u) 
	{
		underlined = u;
		
	}
	
	public int getBranch()
	{
		return branch;
	}

	public void setBranch(int branch) 
	{
		this.branch = branch;
		
	}

}
