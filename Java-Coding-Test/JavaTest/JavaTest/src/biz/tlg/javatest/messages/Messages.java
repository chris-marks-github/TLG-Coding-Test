package biz.tlg.javatest.messages;

import org.jdom.Document;

import biz.tlg.javatest.utils.Utils;

/**
 * Messages used in the JavaTest
 * 
 * 
 * @author paul.manning
 *
 */
public class Messages {

    
    private static final String SIMPLE_CARD_MESSAGE = "<RLSOLVE_MSG>"+
    "<CARD>"+
      "<PAN end=\"2016-12\">4444333322221111</PAN>"+
    "</CARD>"+
    "<TRANSACTION type=\"refund\" customer=\"present\" time=\"10:55:44\" >"+
       "<AMOUNT>2000</AMOUNT>"+
    "</TRANSACTION>"+
    "</RLSOLVE_MSG>";
    
    private static final String COMPLEX_CARD_MESSAGE = "<RLSOLVE_MSG>"+
    "<CARD>"+
      "<PAN end=\"2016-12\">4111111111111111</PAN>"+
      "<TRACK2>;476173******0010=*****************?4</TRACK2>"+
      "<TOKENS>"+
              "<TOKEN origin=\"central\">476173bbbbbbbgf0010</TOKEN>"+
       "</TOKENS>"+
    "</CARD>"+
    "<TRANSACTION type=\"purchase\" customer=\"present\" time=\"10:53:30\" >"+
       "<AMOUNT>15000</AMOUNT>"+
    "</TRANSACTION>"+
    "</RLSOLVE_MSG>";
    
    public static final Document SIMPLE_CARD_MESSAGE_DOC = Utils.createDocument(SIMPLE_CARD_MESSAGE);
    
    public static final Document COMPLEX_CARD_MESSAGE_DOC = Utils.createDocument(COMPLEX_CARD_MESSAGE);
    
}
