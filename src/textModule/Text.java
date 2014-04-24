package textModule;

import java.util.ArrayList;

/*
 * This class contains the attributes font, fontsize, fontcolor, starttime, duration, layer, xstart
 * ystart, xend, yend, linecolor
 * and will contain the element TextBody
 * It sets the value (from the XML file) and gets the value (for the tests)
 */

public class Text {

	String Font = "Arial";
	int FontSize = 12;
	String FontColor = "BLACK";
	int Xstart;
	int Ystart;
	int Yend;
	int Xend;
	int Starttime = 0;
	int Duration = -1;
	int Layer = -1;
	ArrayList<TextBody> textbody = new ArrayList<TextBody>();
	String LineColor = "BLACK";
	
	public String getFont()
	{
		return Font;
	}
	
	public void setFont(String font)
	{
		Font = font;
	}
	
	public int getFontSize()
	{
		return FontSize;
	}
	
	public void setFontSize(int fontsize)
	{
		FontSize = fontsize;
	}
	
	public String getFontColor()
	{
		return FontColor;
	}
	
	public void setFontColor(String fontcolor)
	{
		FontColor = fontcolor;
	}
	
	public String getLineColor()
	{
		return LineColor;
	}
	
	public void setLineColor(String linecolor)
	{
		LineColor = linecolor;
	}
	
	public int getXstart()
	{
		return Xstart;
	}
	
	public void setXstart(int xstart)
	{
		Xstart = xstart;
	}
	
	public int getYstart()
	{
		return Ystart;
	}
	
	public void setYstart(int ystart)
	{
		Ystart = ystart;
	}
	public int getXend()
	{
		return Xend;
	}
	
	public void setXend(int xend)
	{
		Xend = xend;
	}
	
	public int getYend()
	{
		return Yend;
	}
	
	public void setYend(int yend)
	{
		Yend = yend;
	}
	
	public int getStartTime()
	{
		return Starttime;
	}
	
	public void setStartTime(int startime)
	{
		Starttime = startime;
	}
	
	public int getDuration()
	{
		return Duration;
	}
	
	public void setDuration(int duration)
	{
		Duration = duration;
	}
	
	public int getLayer()
	{
		return Layer;
	}
	
	public void setLayer(int layer)
	{
		Layer = layer;
	}

	public TextBody getBody(int i) 
	{
		return textbody.get(i);
	}
	
	public void setBody(TextBody body)
	{
		textbody.add(body);
	}

}
