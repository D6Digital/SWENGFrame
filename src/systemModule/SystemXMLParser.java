/**
 * 
 */
package systemModule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Robert Mills
 *
 */
enum ProcessingElement{
	SYSTEMLIST, SYSTEM, NONE
}
public class SystemXMLParser extends DefaultHandler{
	private GameSystem currentSystem;
	private SystemCollection collection;
	
	private String attrVal;
	private String fileName;
	
	private ProcessingElement currentElement = ProcessingElement.NONE;
	
	public SystemXMLParser(String fileName) {
		URL schemaFile;
		Schema schema;
		Source xmlFile = new StreamSource(new File(fileName));
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			schemaFile = new URL("http://www-users.york.ac.uk/~rjm529/schema.xsd");
			try {
				schema = schemaFactory.newSchema(schemaFile);
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				schema = null;
				e1.printStackTrace();
			}
			Validator validator = schema.newValidator();
			try {
			  validator.validate(xmlFile);
			  System.out.println(xmlFile.getSystemId() + " is valid");
			  System.out.println("Is Valid");
			} catch (SAXException e) {
			  System.out.println(xmlFile.getSystemId() + " is NOT valid");
			  System.out.println("Reason: " + e.getLocalizedMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.fileName = fileName;
	    parse(this.fileName);
	    
	    for(int i = 0; i < collection.size(); i++){
	    	System.out.println(collection.get(i).getName());
	    }
	}

	private void parse(String filename) {

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
		
		if(elementName.equals("systemlist")){
			currentElement = ProcessingElement.SYSTEMLIST;
			collection = new SystemCollection();
		}
		else if(elementName.equals("system")){
			currentElement = ProcessingElement.SYSTEM;
			currentSystem = new GameSystem();
			attrVal = attrs.getValue("name");
			if(!(attrVal == null)){
				currentSystem.setName(attrVal);
			}
			attrVal = attrs.getValue("xmlfilename");
			if(!(attrVal == null)){
				currentSystem.setFilename(attrVal);
			}
			attrVal = attrs.getValue("description");
			if(!(attrVal == null)){
				currentSystem.setDescription(attrVal);
			}
			attrVal = attrs.getValue("logofilename");
			if(!(attrVal == null)){
				currentSystem.setLogoFileName(attrVal);
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
		
		if(elementName.equals("systemlist")){
			currentElement = ProcessingElement.NONE;
		}
		else if (elementName.equals("system")){
			collection.add(currentSystem);
			currentSystem = null;
			currentElement = ProcessingElement.SYSTEMLIST;
		}
	}
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char ch[], int start, int length) throws SAXException {
		
	}
	
}
