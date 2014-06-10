/**
 * @author Robert Mills
 * @version 1.0
 */
package presentation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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

enum ProcessingElement{
	NONE, PRESENTATION, COLLECTION, DOCUMENTINFO, DEFAULTS, SLIDE, NUM, TEXT,
	SHAPE, AUDIO, IMAGE, VIDEO, AUTHOR, VERSION, COMMENT,
	WIDTH, HEIGHT, BACKGROUNDCOLOUR, FONT, FONTCOLOUR,
	LINECOLOUR, FILLCOLOUR, TEXTELEMENT, TEXTSTRING, POINT, FONTSIZE, TITLE
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
	
	private Collection collection;
	private boolean isCollection = false;
	
	private String attrVal;
	
	

	/**
	 * @throws MalformedURLException 
	 * @throws SAXException 
	 * @param fileName the filename and path of the xml to be parsed
	 */
	public XMLParser(String fileName){
		super();
		File schemaFile;
		Schema schema;
		Source xmlFile = new StreamSource(new File(fileName));
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schemaFile = new File("bin/schema.xsd");
        try {
        	//load the schema into the parser
        	schema = schemaFactory.newSchema(schemaFile);
        } catch (SAXException e1) {
        	schema = null;
        	e1.printStackTrace();
        }
        //generate and run the validator
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
		
		this.fileName = fileName;
	    parse(this.fileName);

	}
	public Presentation getSlides() {
		
		if(presentation == null) presentation = collection.get(0);
		return presentation;
	}
	public Collection getCollection(){
		if(collection == null){
			collection = new Collection();
			collection.add(presentation);
			presentation = null;
		}
		return collection;
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
		//collection
		if(elementName.equals("collection")){
			collection = new Collection();
			isCollection = true;
		}

		//slideshow element
		else if(elementName.equals("slideshow")){
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
		else if(elementName.equals("title")){
			if (currentElement == ProcessingElement.DOCUMENTINFO) {
				currentElement = ProcessingElement.TITLE;
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
		else if (elementName.equals("backgroundcolor")) {
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
		else if (elementName.equals("fontcolor")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.FONTCOLOUR;
			}
		}
		else if (elementName.equals("linecolor")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.LINECOLOUR;
			}
		}
		else if (elementName.equals("fillcolor")) {
			if (currentElement == ProcessingElement.DEFAULTS) {
				currentElement = ProcessingElement.FILLCOLOUR;
			}
		}
		//slide
		else if (elementName.equals("slide")) {
			
			if (newSlide == null) {
				newSlide = new Slide();
			}
			
			currentElement =  ProcessingElement.SLIDE;
			attrVal = attrs.getValue("id");
			newSlide.setSlideID(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("duration");
			if(attrVal != null){
				newSlide.setDuration(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("lastSlide");
			if(attrVal != null){
				newSlide.setLastSlide(Boolean.parseBoolean(attrVal));
			}
			attrVal = attrs.getValue("descriptor");
			if (attrVal != null) {
				newSlide.setDescriptor(attrVal);
			}
		}
		else if (elementName.equals("text")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.TEXT;
			}
			
			if (newText == null) {
				newText = new Text();
			}
			
			attrVal = attrs.getValue("font");
			if(attrVal == null){
				newText.setFile(presentation.getFont());
			}
			else {
				newText.setFile(attrVal);
			}
			attrVal = attrs.getValue("fontsize");
			if(attrVal == null){
				newText.setSize(presentation.getFontSize());
			}
			else {
				newText.setSize(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("fontcolor");
			if(attrVal == null){
				newText.setColour(presentation.getFontColour());
			}
			else {
				newText.setColour(attrVal);
			}
			attrVal = attrs.getValue("starttime");
			if(attrVal == null){
				newText.setStart(0);
			}
			else {
				newText.setStart(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("duration");
			if(attrVal == null){
				newText.setDuration(0);
			}
			else {
				newText.setDuration(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("layer");
			if(attrVal == null){
				newText.setLayer(0);
			}
			else {
				newText.setLayer(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("xstart");
			newText.setX_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("ystart");
			newText.setY_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("xend");
			newText.setXend(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("yend");
			newText.setYend(Integer.parseInt(attrVal));
			
			
		}
		
		// Shape
		else if (elementName.equals("shape")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.SHAPE;
			}
			
			if (newShape == null) {
				newShape = new Shapes();
			}
			
			attrVal = attrs.getValue("totalpoints");
			newShape.setNumberOfPoints(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("width");
			newShape.setWidth(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("height");
			newShape.setHeight(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("fillcolor");
			if(attrVal == null){
				newShape.setFillColor(presentation.getFillColour());
			}
			else {
				newShape.setFillColor(attrVal);
			}
			attrVal = attrs.getValue("starttime");
			if(attrVal == null){
				newShape.setStart(0);
			}
			else {
				newShape.setStart(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("duration");
			if(attrVal == null){
				newShape.setDuration(0);
			}
			else {
				newShape.setDuration(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("layer");
			if(attrVal == null){
				newShape.setLayer(0);
			}
			else {
				newShape.setLayer(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("linecolor");
			if(attrVal == null){
				newShape.setLineColor(presentation.getLineColour());
			}
			else {
				newShape.setLineColor(attrVal);
			}
			attrVal = attrs.getValue("branch");
			if(attrVal == null){
				newShape.setBranch(-1);
			}
			else {
				newShape.setBranch(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("chapterbranch");
			if(attrVal == null){
				newShape.setChapterBranch(-1);
			}
			else {
				newShape.setChapterBranch(Integer.parseInt(attrVal));
			}
			
		}
		// Image
		else if (elementName.equals("image")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.IMAGE;
			}
			
			if (newImage == null) {
				newImage = new Image();
			}
			
			attrVal = attrs.getValue("urlname");
			newImage.setFile(attrVal);
			attrVal = attrs.getValue("xstart");
			newImage.setX_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("ystart");
			newImage.setY_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("width");
			if(attrVal == null){
				newImage.setWidth(0);
			}
			else {
				newImage.setWidth(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("height");
			if(attrVal == null){
				newImage.setHeight(0);
			}
			else {
				newImage.setHeight(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("starttime");
			if(attrVal == null){
				newImage.setStart(0);
			}
			else {
				newImage.setStart(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("duration");
			if(attrVal == null){
				newImage.setDuration(0);
			}
			else {
				newImage.setDuration(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("layer");
			if(attrVal == null){
				newImage.setLayer(0);
			}
			else {
				newImage.setLayer(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("branch");
			if(attrVal == null){
				newImage.setBranch(-1);
			}
			else {
				newImage.setBranch(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("chapterbranch");
			if(attrVal == null){
				newImage.setChapterBranch(-1);
			}
			else {
				newImage.setChapterBranch(Integer.parseInt(attrVal));
			}
			
			if(newImage.getFile().contains("Dice")){
				newImage.setStart(newImage.getStart()*10);
				newImage.setDuration(newImage.getDuration()/5);
				newImage.setFile("resources/images/"+newImage.getFile());
			}
			
		}
		// Video
		else if (elementName.equals("video")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.VIDEO;
			}
			
			if (newVideo == null) {
				newVideo = new Video();
			}
			
			attrVal = attrs.getValue("urlname");
			newVideo.setFile(attrVal);
			attrVal = attrs.getValue("xstart");
			newVideo.setX_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("ystart");
			newVideo.setY_coord(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("width");
			newVideo.setWidth(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("height");
			newVideo.setHeight(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("starttime");
			if(attrVal == null){
				newVideo.setStart(0);
			}
			else {
				newVideo.setStart(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("loop");
			if(attrVal == null){
				newVideo.setLooping(false);
			}
			else {
				newVideo.setLooping(Boolean.parseBoolean(attrVal));
			}
			attrVal = attrs.getValue("duration");
			if(attrVal == null){
				newVideo.setDuration(0);
			}
			else {
				newVideo.setDuration(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("playtime");
			if(attrVal == null){
				newVideo.setPlaytime(0);
			}
			else {
				newVideo.setPlaytime(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("layer");
			if(attrVal == null){
				newVideo.setLayer(0);
			}
			else {
				newVideo.setLayer(Integer.parseInt(attrVal));
			}
			
		}
		// Audio
		else if (elementName.equals("audio")) {
			if (currentElement == ProcessingElement.SLIDE) {
				currentElement = ProcessingElement.AUDIO;
			}
			
			if (newSound == null) {
				newSound = new Sound();
			}
			
			attrVal = attrs.getValue("urlname");
			newSound.setFile(attrVal);
			attrVal = attrs.getValue("starttime");
			if(attrVal == null){
				newSound.setStart(0);
			}
			else {
				newSound.setStart(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("playtime");
            if(attrVal == null){
                newSound.setObjectStartTime(0);
            }
            else {
                newSound.setObjectStartTime(Integer.parseInt(attrVal));
            }
            attrVal = attrs.getValue("duration");
            if(attrVal == null){
                newSound.setDuration(-10000);
            }
            else {
                newSound.setDuration(Integer.parseInt(attrVal));
            }
			
			attrVal = attrs.getValue("loop");
			if(attrVal == null){
				newSound.setLoop(false);
			}
			else {
				newSound.setLoop(Boolean.parseBoolean(attrVal));
			}
			
			
		}
		//Text element
		else if (elementName.equals("textbody")) {
			
			if (newTextContent == null) {
				newTextContent = new TextContent();
			}
			
			if (currentElement == ProcessingElement.TEXT) {
				currentElement = ProcessingElement.TEXTELEMENT;
			}
			
			attrVal = attrs.getValue("bold");
			if(attrVal != null){
				newTextContent.setBold(Boolean.parseBoolean(attrVal));
			}
			attrVal = attrs.getValue("italic");
			if(attrVal != null){
				newTextContent.setItalic(Boolean.parseBoolean(attrVal));
			}
			attrVal = attrs.getValue("underlined");
			if(attrVal != null){
				newTextContent.setUnderlined(Boolean.parseBoolean(attrVal));
			}
			attrVal = attrs.getValue("hyperlink");
			if(attrVal != null){
				newTextContent.setHyperlink(Boolean.parseBoolean(attrVal));
			}
			attrVal = attrs.getValue("branch");
			if(attrVal != null){
				newTextContent.setBranch(Integer.parseInt(attrVal));
			}
			attrVal = attrs.getValue("chapterbranch");
			if(attrVal != null){
				newTextContent.setChapterBranch(Integer.parseInt(attrVal));
			}
			
			
		}
		//Text String
		else if (elementName.equals("textstring")) {
			if(currentElement.equals(ProcessingElement.TEXTELEMENT)){
				currentElement = ProcessingElement.TEXTSTRING;
			}
		}
		// Shape element
		else if (elementName.equals("point")) {
			if (currentElement == ProcessingElement.SHAPE) {
				currentElement = ProcessingElement.POINT;
			}
			
			if (newPoint == null) {
				newPoint = new Point();
			}
			
			attrVal = attrs.getValue("num");
			newPoint.setNum(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("x");
			newPoint.setX(Integer.parseInt(attrVal));
			attrVal = attrs.getValue("y");
			newPoint.setY(Integer.parseInt(attrVal));
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
			if(isCollection){
				collection.add(presentation);
				presentation = null;
			}
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
		else if(elementName.equals("title")){
			if (currentElement == ProcessingElement.TITLE) {
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
		else if (elementName.equals("backgroundcolor")) {
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
		else if (elementName.equals("fontcolor")) {
			if (currentElement == ProcessingElement.FONTCOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("linecolor")) {
			if (currentElement == ProcessingElement.LINECOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		else if (elementName.equals("fillcolor")) {
			if (currentElement == ProcessingElement.FILLCOLOUR) {
				currentElement = ProcessingElement.DEFAULTS;
			}
		}
		//slide
		else if (elementName.equals("slide")) {
			currentElement =  ProcessingElement.NONE;
			presentation.add(newSlide);
			newSlide = null;
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
		else if (elementName.equals("textbody")) {
			if (currentElement == ProcessingElement.TEXTELEMENT) {
				currentElement = ProcessingElement.TEXT;
				newText.add(newTextContent);
				newTextContent = null;
			}
		}
		//Text string
		else if (elementName.equals("textstring")) {
			currentElement = ProcessingElement.TEXTELEMENT;
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
		case TITLE:
			presentation.setTitle(new String(ch, start, length));
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
			presentation.setFontSize(Integer.parseInt(new String(ch, start, length)));
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
		case  TEXTSTRING:
			String textString = new String(ch, start, length);
			
			char[] newline = {'\n'};
			String newlineString = new String(newline,0,1);
			textString = textString.replace("\\n", newlineString);
			char[] tab = {'\t'};
			String tabString = new String(tab,0,1);
			textString = textString.replace("\\t", tabString);
			newTextContent.setTextString(textString);
			
			break;
		default:
			break;
		}

	}
}






