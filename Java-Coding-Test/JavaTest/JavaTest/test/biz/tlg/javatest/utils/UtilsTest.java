package biz.tlg.javatest.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.BeforeClass;
import org.junit.Test;

import biz.tlg.javatest.JavaTest;
import biz.tlg.javatest.dto.TransactionDTO;
import biz.tlg.javatest.messages.Messages;
import biz.tlg.javatest.utils.Utils;


/**
 * Some example Unit tests for the Utils class 
 * 
 */
public class UtilsTest {

    /**
     * Create a test document for using in the unit tests
     */
    private Document testDoc = createDocument("<RLSOLVE_MSG><TEST_ELEMENT testAttribute=\"testValue\" /></RLSOLVE_MSG>");
   
    /**
     * Useful utility class used in test classes, converts a string representation into a JDOM document and calls JUnit fail if
     * any exceptions are thrown
     * 
     */
    public static Document createDocument(String input) {

        try {
            return new SAXBuilder().build(new StringReader(input));
        } catch (JDOMException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
        return null;
    }
              
    /**
     * Ensure an IllegalArgumentException is thrown when the input is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDocNullString() {

        Utils.createDocument(null);
        fail();
    }
    
    /**
     * Test that an empty String results in a JDOMException being thrown
     */
    @Test
    public void testCreateDocEmptyStringDoc() {
        
        Utils.createDocument("");
        fail();

    }
    
    /**
     * Test a simple String being converted to a Document
     */
    @Test
    public void testCreateDocValidString() {
        
        /* Our test input */
        String input = "<RLSOLVE_MSG><TEST_ELEMENT testAttribute=\"testValue\" /></RLSOLVE_MSG>";

        Document actual = Utils.createDocument(input); /* Build the actual result */

        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat().setOmitDeclaration(true));

        /* Compare the expected to actual as Strings as it is easier */
         assertEquals(outputter.outputString(testDoc), outputter.outputString(actual));
            
    }
    
    
    
    /** 
     * Make sure an IllegalArgumentException is thrown when a null Document is passed in 
     */
    @Test (expected=IllegalArgumentException.class)
    public void testGetElementNullDoc() {    
        
        try {
            Utils.getElement(null, null);
            fail();
        } catch (JDOMException e) {
            fail();
        }
    }
    
    /** 
     * Make sure an IllegalArgumentException is thrown when a null XPath is passed in 
     */
    @Test (expected=IllegalArgumentException.class)
    public void testGetElementNullXPath() {     
        
        try {
            Utils.getElement(testDoc, null);
            fail();
        } catch (JDOMException e) {
            fail();
        }
        
    } 
    
    /** 
     * Make sure an JDOMException is thrown when an empty XPath is passed in 
     */
    @Test
    public void testGetElementEmptyXPath() {    
        
        
        try {
            Utils.getElement(testDoc, "");
            fail();
        } catch (JDOMException e) {
            assertTrue(true);
        }
 
    } 
    
    /** 
     * Make sure the TEST_ELEMENT Element is returned when it exists in the JDOM document 
     */
    @Test
    public void testGetElementEmptyXPathReturned() {    
        
        Element testElem = testDoc.getRootElement().getChild("TEST_ELEMENT");
        
        try {
            assertEquals(testElem, Utils.getElement(testDoc, "//RLSOLVE_MSG/TEST_ELEMENT"));
        } catch (JDOMException e) {
            fail();
        }
 
    } 
    
    
    /** 
     * Make sure an IllegalArgumentException is thrown when a null JDOM Document is passed in 
     */
    @Test (expected=IllegalArgumentException.class)
    public void testGetAttributeNullDoc() {        
        
        try {
            Utils.getAttribute(null, null);
            fail();
        } catch (JDOMException e) {
            fail();
        }
        
    }
    
    /** 
     * Make sure an IllegalArgumentException is thrown when a null XPath is passed in 
     */
    @Test (expected=IllegalArgumentException.class)
    public void testGetAttributeNullXPath() {        
        
        try {
            Utils.getAttribute(testDoc, null);
            fail();
        } catch (JDOMException e) {
            fail();
        }
        
    } 
    
    /** 
     * Make sure an JDOMException is thrown when an empty XPath is passed in 
     */
    @Test
    public void testGetAttributeNullReturned() {        
        
        try {
            assertEquals(null, Utils.getAttribute(testDoc, ""));
            fail();
        } catch (JDOMException e) {
            assertTrue(true);
        }
        
    }
    
    
    /** 
     * Make sure the testAttribute Attribute is returned when it exists in the JDOM document 
     */
    @Test
    public void testGetAttributeSuccess() {    
        
        Attribute testElem = testDoc.getRootElement().getChild("TEST_ELEMENT").getAttribute("testAttribute");
        
        try {
            assertEquals(testElem, Utils.getAttribute(testDoc, "//RLSOLVE_MSG/TEST_ELEMENT/@testAttribute"));
        } catch (JDOMException e) {
            fail();
        }
 
    }     
  
}
