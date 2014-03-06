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
	NONE, DOCUMENTINFO, DEFAULTS, SLIDE, NUM, X, Y, TEXT,
	SHAPE, AUDIO, IMAGE, VIDEO, AUTHOR, VERSION, COMMENT, WIDTH, HEIGHT, BACKGROUNDCOLOUR
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
	private TextContent textContent;
	private Video newVideo;
	private ProcessingElement currentElement = ProcessingElement.NONE;

	private Presentation presentation;
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
	// overridden method for start element callback
	public void startElement(String uri, String localName, String qName, Attributes	attrs) throws SAXException {
		// sort out element name if (no) namespace in use		
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		// slideshow element
		if(elementName.equals("slideshow")){
			if (presentation == null) {
				presentation = new Presentation();
			}
		}
		//document info
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
		//defaults
		else if (elementName.equals("defaults")) {
			currentElement = ProcessingElement.DEFAULTS;
		}
		else if (elementName.equals("backgroundcolour")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.BACKGROUNDCOLOUR;
			}
		}
	}
}






