/**
 * 
 */
package bookModule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import presentation.Image;
enum ProcessingElement{
	NONE, BOOK, TITLE, FILENAME, ICON 
		}

/**
 * @author Sam Lambert
 * @author Robert Mills
 */
public class BookXMLParser extends DefaultHandler{
	
	public ProcessingElement currentElement = ProcessingElement.NONE;
	public BookList bookList;
	public Book currentBook;
	public Image newIcon;
	public String attrVal;
	
	
	public BookXMLParser(String inputFile){
		readBookXML(inputFile);
		writeBookinfo();
	}

	

	public BookList readBookXML(String inputFile) {
		//Check to see if xml is valid
		File schemaFile;
		Schema schema;
		Source xmlFile = new StreamSource(new File(inputFile));
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schemaFile = new File("bin/schema.xsd");
		try {
			schema = schemaFactory.newSchema(schemaFile);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			schema = null;
			e1.printStackTrace();
		}
		//generate validator
		Validator validator = schema.newValidator();
		try {
			// run validator on the desired xml file
		  validator.validate(xmlFile);
		  System.out.println(xmlFile.getSystemId() + " is valid");
		  System.out.println("Is Valid");
		} catch (SAXException e) {
			//say isn't valid and report where the error is
		  System.out.println(xmlFile.getSystemId() + " is NOT valid");
		  System.out.println("Reason: " + e.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//parse the xml file
		try {
			// use the default parser
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			// parse the input
			saxParser.parse(inputFile, this);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return bookList;	
	}

	public void startDocument() throws SAXException {
		System.out.println("Starting to process document.");
	}
	
	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		System.out.println("element: " + elementName);
		
		

		if (elementName.equals("booklist")) {
			if (bookList == null) {
				bookList = new BookList(attrs.getValue(0));
			}
		}
		//handle book element start
		else if (elementName.equals("book")) {
			currentBook = new Book(attrs.getValue(0));
			currentElement = ProcessingElement.BOOK;
			bookList.addBook(currentBook);
		}
		//handle title element start
		else if (elementName.equals("title")) {
			currentElement = ProcessingElement.TITLE;
		}
		//handle filename element start
		else if (elementName.equals("filename")) {
			currentElement = ProcessingElement.FILENAME;
		}
		//handle icon filename element start
		else if (elementName.equals("icon")) {
			currentElement = ProcessingElement.ICON;
		}	
	}
	// handle contents of elements
	public void characters(char ch[], int start, int length)
			throws SAXException {

		switch (currentElement) {
		//extracts title of book
		case TITLE:
			currentBook.setTitle(new String(ch, start, length));
			System.out.println(new String(ch, start, length) + " :: " + currentBook.getTitle());
			
			break;
		//extracts filename
		case FILENAME:
			currentBook.setFileName(new String(ch, start, length));
			System.out.println(new String(ch, start, length));
			break;
		//extract icon filename		
		case ICON:
			currentBook.setButtonIcon(new String(ch, start, length));
			System.out.println(new String(ch, start, length)); 
			break;
		default:
			break;
		}
	}
	
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// sort out element name if (no) namespace in use
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		if (elementName.equals("book")) {
			currentElement = ProcessingElement.NONE;
		} else if(elementName.equals("title")){
			currentElement = ProcessingElement.NONE;
		} else if (elementName.equals("filename")) {
			currentElement = ProcessingElement.NONE;
		} else if (elementName.equals("icon")){
			currentElement = ProcessingElement.NONE;
		}

		System.out.println("\tFound the end of an element (" + elementName
				+ ") ...");
	}
	
	public void endDocument() throws SAXException {
		System.out.println("Finished processing document.");
	}
	
	/* Test method. To be deleted after testing */
	private void writeBookinfo() {
		System.out.println("BookList version: " + BookList.getVersion());
		ArrayList<Book> books = BookList.getList();
		
		for(Book currentBook : books){
		System.out.println("Title: " + currentBook.getTitle());
		System.out.println("Filename: " + currentBook.getFileName());
		}
	}
}
