/**
 * @author Robert Mills
 * @version 1.0
 */
package slideModule;

import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
enum ProcessingElement{
	NONE, DOCUMENTINFO, DEFAULTS, SLIDE, NUM, TEXT,
	SHAPE, AUDIO, IMAGE, VIDEO, AUTHOR, VERSION, COMMENT,
	WIDTH, HEIGHT, BACKGROUNDCOLOUR, FONT, FONTCOLOUR,
	LINECOLOUR, FILLCOLOUR, TEXTELEMENT, POINT, FONTSIZE
}

/**
 * 
 */
public class XMLParser extends DefaultHandler{
	private String fileName;

	private Slide newSlide;
	private Image newImage;
	private Point newPoint;
	private Shapes newShape;
	private Text newText;
	private TextContent newTextContent;
	private Video newVideo;
	private Sound newSound;

	private ProcessingElement currentElement = ProcessingElement.NONE;

	private Presentation presentation;
	
	private String attrVal;


	/**
	 * 
	 */
	public XMLParser(String fileName) {
		super();
		this.fileName = fileName;
		parse(this.fileName);
	}
	public Presentation getSlides() {
		//Insert Parser here
		return presentation;
	}

	private void parse(String filename){

		try {			
			// use the default parser
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			// parse the input			
			saxParser.parse(filename, this);
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}		
		catch (SAXException saxe) {			
			saxe.printStackTrace();		
		}		
		catch (IOException ioe) {			
			ioe.printStackTrace();		
		}		

	}


	/**
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes	attrs)
			throws SAXException {
		// sort out element name if (no) namespace in use		
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}

		//slideshow element
		if(elementName.equals("slideshow")){
			if (presentation == null) {
				presentation = new Presentation();
			}
		}
		//document info element
		else if(elementName.equals("documentinfo")) {
			currentElement = ProcessingElement.DOCUMENTINFO;

		}		
		else if(elementName.equals("author")){
			if (currentElement == ProcessingElement.DOCUMENTINFO) {
				currentElement = ProcessingElement.AUTHOR;
			}
		}
		else if(elementName.equals("version")){
			if (currentElement == ProcessingElement.DOCUMENTINFO) {
				currentElement = ProcessingElement.VERSION;
			}
		}
		else if (elementName.equals("comment")) {
			if (currentElement == ProcessingElement.DOCUMENTINFO) {
				currentElement = ProcessingElement.COMMENT;
			}
		}
		else if (elementName.equals("width")) {
			if (currentElement == ProcessingElement.DOCUMENTINFO) {
				currentElement = ProcessingElement.WIDTH;
			}
		}
		else if (elementName.equals("height")) {
			if (currentElement == ProcessingElement.DOCUMENTINFO) {
				currentElement = ProcessingElement.HEIGHT;
			}
		}
		//defaults element
		else if (elementName.equals("defaults")) {
			currentElement = ProcessingElement.DEFAULTS;
		}
		else if (elementName.equals("backgroundcolour")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.BACKGROUNDCOLOUR;
			}
		}
		else if (elementName.equals("font")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.FONT;
			}
		}
		else if (elementName.equals("fontsize")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.FONTSIZE;
			}
		}
		else if (elementName.equals("fontcolour")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.FONTCOLOUR;
			}
		}
		else if (elementName.equals("linecolour")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.LINECOLOUR;
			}
		}
		else if (elementName.equals("fillcolour")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.FILLCOLOUR;
			}
		}
		//slide
		else if (elementName.equals("slide")) {
			currentElement =  ProcessingElement.SLIDE;
			attrVal = attrs.getValue("id");
			newSlide.setSlideID(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("duration");
			if(attrVal != null){
				newSlide.setDuration(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("lastslide");
			if(attrVal != null){
				newSlide.setLastSlide(Boolean.parseBoolean(attrVal));
			}
		}
		else if (elementName.equals("text")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.TEXT;
			}
			attrVal = attrs.getValue("font");
			if(attrVal == null){
				newText.setFont(presentation.getFont());
			}
			else {
				newText.setFont(attrVal);
			}
			attrVal = attrs.getValue("fontsize");
			if(attrVal == null){
				newText.setSize(presentation.getFontSize());
			}
			else {
				newText.setSize(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("fontcolour");
			if(attrVal == null){
				newText.setColour(presentation.getFontColour());
			}
			else {
				newText.setColour(attrVal);
			}
			
		}
		else if (elementName.equals("shape")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.SHAPE;
			}
		}
		else if (elementName.equals("image")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.IMAGE;
			}
		}
		else if (elementName.equals("video")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.VIDEO;
			}
		}
		else if (elementName.equals("audio")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.AUDIO;
			}
		}
		//Text element
		else if (elementName.equals("textelement")) {
			if (currentElement == ProcessingElement.TEXT) {
				currentElement = ProcessingElement.TEXTELEMENT;
			}
		}
		// Shape element
		else if (elementName.equals("point")) {
			if (currentElement == ProcessingElement.SHAPE) {
				currentElement = ProcessingElement.POINT;
			}
		}
	}

	/**
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// sort out element name if (no) namespace in use		
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		//slideshow element
		if(elementName.equals("slideshow")){
			currentElement = ProcessingElement.NONE;
		}
		//document info element
		else if(elementName.equals("documentinfo")) {
			currentElement = ProcessingElement.NONE;

		}		
		else if(elementName.equals("author")){
			if (currentElement == ProcessingElement.AUTHOR) {
				currentElement = ProcessingElement.DOCUMENTINFO;
			}
		}
		else if(elementName.equals("version")){
			if (currentElement == ProcessingElement.VERSION) {
				currentElement = ProcessingElement.DOCUMENTINFO;
			}
		}
		else if (elementName.equals("comment")) {
			if (currentElement == ProcessingElement.COMMENT) {
				currentElement = ProcessingElement.DOCUMENTINFO;
			}
		}
		else if (elementName.equals("width")) {
			if (currentElement == ProcessingElement.WIDTH) {
				currentElement = ProcessingElement.DOCUMENTINFO;
			}
		}
		else if (elementName.equals("height")) {
			if (currentElement == ProcessingElement.HEIGHT) {
				currentElement = ProcessingElement.DOCUMENTINFO;
			}
		}
		//defaults element
		else if (elementName.equals("defaults")) {
			currentElement = ProcessingElement.NONE;
		}
		else if (elementName.equals("backgroundcolour")) {
			if (currentElement == ProcessingElement.BACKGROUNDCOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("font")) {
			if (currentElement == ProcessingElement.FONT) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("fontsize")) {
			if (currentElement == ProcessingElement.FONTSIZE) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("fontcolour")) {
			if (currentElement == ProcessingElement.FONTCOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("linecolour")) {
			if (currentElement == ProcessingElement.LINECOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("fillcolour")) {
			if (currentElement == ProcessingElement.FILLCOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		//slide
		else if (elementName.equals("slide")) {
			currentElement =  ProcessingElement.NONE;
			presentation.add(newSlide);
		}
		else if (elementName.equals("text")) {
			if (currentElement == ProcessingElement.TEXT) {
				currentElement = ProcessingElement.SLIDE;
				newSlide.addText(newText);
				newText = null;
			}
		}
		else if (elementName.equals("shape")) {
			if (currentElement == ProcessingElement.SHAPE) {
				currentElement = ProcessingElement.SLIDE;
				newSlide.addShape(newShape);
				newShape = null;
			}
		}
		else if (elementName.equals("image")) {
			if (currentElement == ProcessingElement.IMAGE) {
				currentElement = ProcessingElement.SLIDE;
				newSlide.addImage(newImage);
				newImage = null;
			}
		}
		else if (elementName.equals("video")) {
			if (currentElement == ProcessingElement.VIDEO) {
				currentElement = ProcessingElement.SLIDE;
				newSlide.addVideo(newVideo);
				newVideo = null;
			}
		}
		else if (elementName.equals("audio")) {
			if (currentElement == ProcessingElement.AUDIO) {
				currentElement = ProcessingElement.SLIDE;
				newSlide.addSound(newSound);
				newSound = null;
			}
		}
		//Text element
		else if (elementName.equals("textelement")) {
			if (currentElement == ProcessingElement.TEXTELEMENT) {
				currentElement = ProcessingElement.TEXT;
				newText.add(newTextContent);
				newTextContent = null;
			}
		}
		// Shape element
		else if (elementName.equals("point")) {
			if (currentElement == ProcessingElement.POINT) {
				currentElement = ProcessingElement.SHAPE;
				newShape.addPoint(newPoint);
				newPoint = null;
			}
		}
	}
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char ch[], int start, int length) throws SAXException {
		switch (currentElement) {
		case AUTHOR:
			presentation.setAuthor(new String(ch, start, length));
			break;
		case VERSION:
			presentation.setVersion(new String(ch, start, length));
			break;
		case COMMENT:
			presentation.setComment(new String(ch, start, length));
			break;
		case WIDTH:
			presentation.setWidth(Integer.parseInt(new String(ch, start, length)));
			break;
		case HEIGHT:
			presentation.setHeight(Integer.parseInt(new String(ch, start, length)));
			break;
		case BACKGROUNDCOLOUR:
			presentation.setBackgroundColour(new String(ch, start, length));
			break;
		case FONT:
			presentation.setFont(new String(ch, start, length));
			break;
		case FONTSIZE:
			presentation.setFontSize(new String(ch, start, length));
			break;
		case FONTCOLOUR:
			presentation.setFontColour(new String(ch, start, length));
			break;
		case LINECOLOUR:
			presentation.setLineColour(new String(ch, start, length));
			break;
		case  FILLCOLOUR:
			presentation.setFillColour(new String(ch, start, length));
			break;
		default:
			break;
		}

	}
}






