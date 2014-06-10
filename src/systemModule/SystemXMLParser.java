package systemModule;

import java.io.File;
import java.io.IOException;
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
 * Parses system XML files
 * @author Robert Mills
 */
enum ProcessingElement{
	SYSTEMLIST, SYSTEM, NONE
}

public class SystemXMLParser extends DefaultHandler{
	
	private GameSystem currentSystem;
	private SystemCollection collection;
	private String attrVal;
	private String fileName;
	@SuppressWarnings("unused")
	private ProcessingElement currentElement = ProcessingElement.NONE;
	
	/**
	 * Creates XML parser for system files
	 * @param fileName, the name of the XML file to be parsed
	 */
	public SystemXMLParser(String fileName) {
		File schemaFile;
		Schema schema;
		Source xmlFile = new StreamSource(new File(fileName));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schemaFile = new File("bin/schema.xsd");
		
		try {
			schema = schemaFactory.newSchema(schemaFile);
			}
		catch (SAXException e1) {
			schema = null;
			e1.printStackTrace();
			}
		Validator validator = schema.newValidator();
		
		try {
		  validator.validate(xmlFile);
		  System.out.println(xmlFile.getSystemId() + " is valid");
		  System.out.println("Is Valid");
			}
		catch (SAXException e) {
		  System.out.println(xmlFile.getSystemId() + " is NOT valid");
		  System.out.println("Reason: " + e.getLocalizedMessage());
			}
		catch (IOException e) {
			e.printStackTrace();
			}
		
		this.fileName = fileName;
	    parse(this.fileName);
	    
	    for(int i = 0; i < collection.size(); i++){
	    	System.out.println(collection.get(i).getName());
	    }
	}

	/**
	 * uses the XML parser to parse a specified system file
	 * @param filename, the name of the XML file to be parsed
	 */
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
	 * handels start elements within the XML file
	 * @param uri, address of the start element
	 * @param localName, local identifier for the start element
	 * @param qName, alternative local identifier
	 * @param attrs, attributes of the first element
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes	attrs) throws SAXException {		
		
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
	 * handels end elements with the XML file
	 * @param uri, address of end element
	 * @param localName, local identifier for the start element
	 * @param qName, alternative local identifier
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {		
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
	 * handels the content of elements with the XML file
	 * @param ch[], characters to be parsed 
	 * @param start, start value of the element
	 * @param length, length of the element
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char ch[], int start, int length) throws SAXException {
	}
	
	/**
	 * returns the list of systems
	 * @return collection, the SystemCollection being returned
	 */
	public SystemCollection getSystem(){
		return collection;
	}
}
