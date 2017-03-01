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
public class TestComplexCard {
       
    private static TransactionDTO  complexTestTransactionDTO;
        
    @BeforeClass
    public static void defineTransactionDTO() {
    	
    	JavaTest javaTest = new JavaTest();    	    	    	
    	complexTestTransactionDTO = new TransactionDTO();
    	javaTest.processDocument(Messages.COMPLEX_CARD_MESSAGE_DOC, complexTestTransactionDTO);
    	
    }
     
    @Test
    public void testForValidTokens() {    	
    	assertTrue(complexTestTransactionDTO.getCardDTO().getTokens().size() > 0);    	
    }
    
    @Test
    public void testForCorrectAmount() {    	
    	assertTrue(complexTestTransactionDTO.getTransactionAmount().equals("15000"));    	
    }
    
    @Test
    public void testForNumericAmount() {    	
    	try {
    		Double.parseDouble(complexTestTransactionDTO.getTransactionAmount()); 
    	} catch (NumberFormatException nfe) {
    		fail();
    	}    	
    }
    
}
