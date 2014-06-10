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

	/**
	 * @return the font
	 */
	public String getFont()
	{
		return Font;
	}

	/**
	 * @param set the font
	 */
	public void setFont(String font)
	{
		Font = font;
	}

	/**
	 * @return the font size
	 */
	public int getFontSize()
	{
		return FontSize;
	}

	/**
	 * @param set the font size
	 */
	public void setFontSize(int fontsize)
	{
		FontSize = fontsize;
	}

	/**
	 * @return the font colour
	 */
	public String getFontColor()
	{
		return FontColor;
	}

	/**
	 * @param set the font colour
	 */
	public void setFontColor(String fontcolor)
	{
		FontColor = fontcolor;
	}

	/**
	 * @return the line colour
	 */
	public String getLineColor()
	{
		return LineColor;
	}

	/**
	 * @param set the line colour
	 */
	public void setLineColor(String linecolor)
	{
		LineColor = linecolor;
	}

	/**
	 * @return the X-coordinate of the start position
	 */
	public int getXstart()
	{
		return Xstart;
	}

	/**
	 * @param set the X-coordinate of the start position
	 */
	public void setXstart(int xstart)
	{
		Xstart = xstart;
	}

	/**
	 * @return the Y-coordinate of the start position
	 */
	public int getYstart()
	{
		return Ystart;
	}

	/**
	 * @param set the Y-coordinate of the start position
	 */
	public void setYstart(int ystart)
	{
		Ystart = ystart;
	}

	/**
	 * @return the X-coordinate of the end position
	 */
	public int getXend()
	{
		return Xend;
	}

	/**
	 * @param set the X-coordinate of the end position
	 */
	public void setXend(int xend)
	{
		Xend = xend;
	}

	/**
	 * @return the Y-coordinate of the end position
	 */
	public int getYend()
	{
		return Yend;
	}

	/**
	 * @param set the Y-coordinate of the end position
	 */
	public void setYend(int yend)
	{
		Yend = yend;
	}

	/**
	 * @return the start time of the text object
	 */
	public int getStartTime()
	{
		return Starttime;
	}

	/**
	 * @param set the start time of the text object
	 */
	public void setStartTime(int startime)
	{
		Starttime = startime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration()
	{
		return Duration;
	}

	/**
	 * @param set the duration
	 */
	public void setDuration(int duration)
	{
		Duration = duration;
	}

	/**
	 * @return the layer of the text object
	 */
	public int getLayer()
	{
		return Layer;
	}

	/**
	 * @param set the layer of the text object
	 */
	public void setLayer(int layer)
	{
		Layer = layer;
	}

	/**
	 * @return the body
	 */
	public TextBody getBody(int i) 
	{
		return textbody.get(i);
	}

	/**
	 * @param set the body
	 */
	public void setBody(TextBody body)
	{
		textbody.add(body);
	}

	/**
	 * This is an additional method required by the text module to process the text body
	 * @return the array list of text body
	 */
	public ArrayList<TextBody> getTextBody()
	{
		return textbody;
	}

}
