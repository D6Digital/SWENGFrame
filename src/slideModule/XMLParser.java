/**
 * @author Robert Mills
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
	SHAPE, AUDIO, IMAGE, VIDEO, AUTHOR, VERSION, COMMENT, WIDTH, HEIGHT
}

/**
 * 
 */
public class XMLParser extends DefaultHandler{
	private String fileName;
	private ArrayList<Slide> slides = new ArrayList<Slide>(0);
	private Slide newSlide;
	private Image newImage;
	private Point newPoint;
	private Shapes newShape;
	private Text newText;
	private TextContent textContent;
	private Video newVideo;
	private ProcessingElement currentElement = ProcessingElement.NONE;
	/**
	 * 
	 */
	public XMLParser(String fileName) {
		super();
		this.fileName = fileName;
		parse(this.fileName);
	}
	public ArrayList<Slide> getSlides() {
		//Insert Parser here
		return slides;
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
		if(elementName.equals("videolist")){

		}	
		else if(elementName.equals("video")) {
			if(currentVideo == null){
				currentVideo = new VideoFile();
			}
			currentVideo.setId(attrs.getValue(0));
		}		else if(elementName.equals("title")){
			currentElement = ProcessingElement.TITLE;
		}
		else if(elementName.equals("filename")){
			currentElement = ProcessingElement.FILENAME;
		}
	}
}






