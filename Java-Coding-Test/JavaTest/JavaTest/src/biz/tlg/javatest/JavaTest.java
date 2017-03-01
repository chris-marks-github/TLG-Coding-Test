package biz.tlg.javatest;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.Document;
import org.jdom.Element;

import biz.tlg.javatest.dto.CardDTO;
import biz.tlg.javatest.dto.CustomerPresent;
import biz.tlg.javatest.dto.TokenDTO;
import biz.tlg.javatest.dto.TransactionDTO;
import biz.tlg.javatest.dto.TransactionType;
import biz.tlg.javatest.messages.Messages;


/**
 * 
 * @author paul.manning
 *
 */
public class JavaTest {
    
    private static final String LOG4J_PROPERTIES = "/log4j.properties";
    
    /** log4j logger */
    private static final Logger LOG = Logger.getLogger(JavaTest.class);
    
    /**
     * USE THIS METHOD....
     */
    private void run() {

        LOG.debug("Starting run method...");
                             
        TransactionDTO transactionDTO = new TransactionDTO();
        
        // Messages.SIMPLE_CARD_MESSAGE_DOC;
        Document simpleCardMessageDocument = Messages.SIMPLE_CARD_MESSAGE_DOC;
        processDocument(simpleCardMessageDocument, transactionDTO);
        LOG.info("Output of DTOs...");
        LOG.info(transactionDTO.toString());
        
        // Messages.COMPLEX_CARD_MESSAGE_DOC;
        transactionDTO = new TransactionDTO();
        Document complexCardMessageDocument = Messages.COMPLEX_CARD_MESSAGE_DOC;
        processDocument(complexCardMessageDocument, transactionDTO); 			
        LOG.info("Output of DTOs...");
        LOG.info(transactionDTO.toString());
              
    }
      
    public void processDocument(Document document, TransactionDTO transactionDTO) {
    	
    	Element rootElement = document.getRootElement();
    	processElement(rootElement, transactionDTO);
    	
    }
    
    public void processElement(Element element, TransactionDTO transactionDTO) {
    	
    	checkElement(element, transactionDTO);
    	List<Element> elements = element.getChildren();
    	for (Element e : elements) {
    		processElement(e, transactionDTO);
    	}
    	
    }
    
    public void checkElement(Element e, TransactionDTO transactionDTO) {
    	
    	System.out.println("Processing element " + e.toString());
    	
    	CardDTO cardDTO;
    	
    	String elementName = e.getName();
    	switch (elementName) {
	    	case "PAN" :
	    		String pan = e.getValue();
    			String end = e.getAttributeValue("end");
    			cardDTO = new CardDTO(pan, end);
    			transactionDTO.setCardDTO(cardDTO);
    			break;
	    	case "TRACK2" :
	    		String track2 = e.getValue();
	    		// If PAN element is not in XML then there will be no cardDTO to add the TRACK2 into
	    		Optional<CardDTO> optionalCardDTO = Optional.<CardDTO>ofNullable(transactionDTO.getCardDTO());
	    		if (optionalCardDTO.isPresent()) {
	    			cardDTO = optionalCardDTO.get();
	    			cardDTO.setTrack2(track2);
	    		}
	    		break;
	    	case "TOKEN" :
	    		String origin = e.getAttributeValue("origin");
				String token = e.getValue();
				// If PAN element is not in XML then there will be no cardDTO to add the TOKEN into
				optionalCardDTO = Optional.<CardDTO>ofNullable(transactionDTO.getCardDTO());
	    		if (optionalCardDTO.isPresent()) {
	    			cardDTO = optionalCardDTO.get();
	    			cardDTO.getTokens().add(new TokenDTO(token, origin));	    			
	    		}
	    		break;
	    	case "TRANSACTION" : 
	    		String type = e.getAttributeValue("type");
    			transactionDTO.setTransactionType(TransactionType.getTransactionType(type));
    			String customer = e.getAttributeValue("customer");
    			transactionDTO.setCustomerPresent(CustomerPresent.getCustomerPresent(customer));
    			String time = e.getAttributeValue("time");
    			// XML file contains only a time (with no date) but DTO accepts a Date object - default to todays date
    			LocalTime localTime = LocalTime.parse(time);
    			Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
    			Date date = Date.from(instant);    					    
				transactionDTO.setTransactionTime(date);	  					    
				break;
	    	case "AMOUNT" :
	    		String amount = e.getValue();
    			transactionDTO.setTransactionAmount(amount);    
    			break;
    	}
    	
    }
    
    /**
     * Run the Java Test
     * 
     * @param args
     */
    public static void main(String[] args) {

        /* Initialise log4j */
        String currentdir = System.getProperty("user.dir");
        System.out.println("Reading log4j properties file: " + LOG4J_PROPERTIES + " ...");
        if (new File(currentdir + LOG4J_PROPERTIES).exists()) {
            PropertyConfigurator.configureAndWatch(currentdir + LOG4J_PROPERTIES);
        } else {
            System.out.println("FAILED - " + currentdir + " Not Found.");
        }
                            
        new JavaTest().run();
        
    }

    
    

}
