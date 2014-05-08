/**
 * 
 */
package bookModule;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import presentation.Image;
enum ProcessingElement{
		NONE, TITLE, FILENAME, ICON 
		}

/**
 * @author Sam Lambert
 *
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
		
		

		if (elementName.equals("bookList")) {
			if (bookList == null) {
				bookList = new BookList(attrs.getValue(0));
			}
		} else if (elementName.equals("book")) {
			currentBook = new Book(attrs.getValue(0));
		} else if (elementName.equals("title")) {
			currentElement = ProcessingElement.TITLE;
		} else if (elementName.equals("filename")) {
			currentElement = ProcessingElement.FILENAME;
		}
		else if (elementName.equals("icon")) {
			currentElement = ProcessingElement.ICON;
			
			if (newIcon == null) {
				newIcon = new Image();
			}
			attrVal = attrs.getValue("urlname");
			newIcon.setFile(attrVal);
			attrVal = attrs.getValue("xstart");
			newIcon.setX_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("ystart");
			newIcon.setY_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("width");
			if(attrVal == null){
				newIcon.setWidth(0);
			}
			else {
				newIcon.setWidth(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("height");
			if(attrVal == null){
				newIcon.setHeight(0);
			}
			else {
				newIcon.setHeight(Integer.parseInt(attrVal));
			}
			
			currentBook.setButtonIcon(newIcon);
		}
	}
	
	public void characters(char ch[], int start, int length)
			throws SAXException {

		switch (currentElement) {
		case TITLE:
			currentBook.setTitle(new String(ch, start, length));
			break;
		case FILENAME:
			currentBook.setFileName(new String(ch, start, length));
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
			bookList.addBook(currentBook);
		} else if (elementName.equals("filename")) {
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
		List<Book> books = BookList.getList();
		
		for(Book currentBook : books){
		System.out.println("Title: " + currentBook.getTitle());
		System.out.println("FIlename: " + currentBook.getFileName());
		}
	}
}
