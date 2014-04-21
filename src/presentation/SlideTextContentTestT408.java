package presentation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import presentation.TextContent.ScriptTypeDef;

/**
 * T408, a test to check that TextContent contains all the correct parameters
 * and that they are all working correctly. proper getters/setters etc.
 * @author Joshua Lant
 */
public class SlideTextContentTestT408 {

	ScriptTypeDef scriptType;
	Boolean bold, italic, underlined, hyperlink;
	String textString;
	
	@Before
	public void before() {
		scriptType = ScriptTypeDef.superScript;
		bold = true;
		italic = true;
		underlined = true;
		hyperlink = true;
		textString = "dsjdiosjviosjviodsjj";
	}
	
	
	@Test
	public void testSteps1to2() {
		// Step 1. Instantiate TextContent supplying values to construcor.
		TextContent textContent = new TextContent();

		// Step 2. Check that the getters and constructor work properly.
		assertEquals("scriptType has a problem with the constructor or the getters",
				scriptType, textContent.getScriptType());
		assertEquals("bold has a problem with the constructor or the getters",
				bold, textContent.isBold());
		assertEquals("italic has a problem with the constructor or the getters",
				italic, textContent.isItalic());
		assertEquals("underlined has a problem with the constructor or the getters",
				underlined, textContent.isUnderlined());
		assertEquals("hyperlink has a problem with the constructor or the getters",
				hyperlink, textContent.isHyperlink());
		assertEquals("textString has a problem with the constructor or the getters",
				textString, textContent.getTextString());
		
	}
	
	@Test
	public void testSteps3to4() {
		// Step 3. Instantiate TextContent with empty constructor.
		TextContent textContent = new TextContent();
		
		textContent.setBold(bold);
		textContent.setHyperlink(hyperlink);
		textContent.setItalic(italic);
		textContent.setScriptType(scriptType);
		textContent.setTextString(textString);
		textContent.setUnderlined(underlined);

		// Step 3. Check that the getters and setters work properly.
		assertEquals("scriptType has a problem with the setters or the getters",
				scriptType, textContent.getScriptType());
		assertEquals("bold has a problem with the setters or the getters",
				bold, textContent.isBold());
		assertEquals("italic has a problem with the setters or the getters",
				italic, textContent.isItalic());
		assertEquals("underlined has a problem with the setters or the getters",
				underlined, textContent.isUnderlined());
		assertEquals("hyperlink has a problem with the setters or the getters",
				hyperlink, textContent.isHyperlink());
		assertEquals("textString has a problem with the setters or the getters",
				textString, textContent.getTextString());
	}

}
