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

public class TestSimpleCard {

    private static TransactionDTO simpleTestTransactionDTO;
         
    @BeforeClass
    public static void defineTransactionDTO() {
    	
    	JavaTest javaTest = new JavaTest();    	
    	simpleTestTransactionDTO = new TransactionDTO();
    	javaTest.processDocument(Messages.SIMPLE_CARD_MESSAGE_DOC, simpleTestTransactionDTO);
       	
    }
  
    @Test
    public void testCardNumberWorks() {    	  
    	assertTrue(simpleTestTransactionDTO.getCardDTO().getPan().equals("4444333322221111"));    	
    }
    
    @Test
    public void testForEndDate() {    	     	
       	assertTrue(simpleTestTransactionDTO.getCardDTO().getExpiryDate().equals("2016-12"));    	
    }
    
    @Test
    public void testForEmptyTokens() {    	
    	assertTrue(simpleTestTransactionDTO.getCardDTO().getTokens().size() == 0);    	
    }    
    
}
