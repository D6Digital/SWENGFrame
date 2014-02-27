/**
 * 
 */
package slideModule;

import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

/**
 * @author Robert
 *
 */
public class XMLParser extends DefaultHandler{
	private String fileName;
	private ArrayList<Slide> slides = new ArrayList<Slide>(0);
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
}


