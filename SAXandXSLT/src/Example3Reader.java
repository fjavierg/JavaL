import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class Example3Reader implements XMLReader  {

  private ContentHandler myHandler;
  private Attributes myAtts = new AttributesImpl(); 

  private String myNameSpaceURI = "";
  private char[] myChArray = new char[255];

  public final static String ORDER_INFO = "ORDER_INFO";
  public final static String CUSTOMER = "CUSTOMER";
  public final static String GROUP = "GROUP";
  public final static String ID = "ID";
  public final static String SERVICE_ORDERS = "SERVICE_ORDERS";
  public final static String ORDER = "ORDER";
  public final static String PRODUCT_ID = "PRODUCT_ID";
  public final static String PRICE = "PRICE";
  public final static String TIMESTAMP = "TIMESTAMP";
         
  public void parse(InputSource _input) 
    throws IOException, SAXException {
    try {
      InputStream istream = _input.getByteStream();
      InputStreamReader istreamreader = new InputStreamReader(istream); 
      BufferedReader br = new BufferedReader(istreamreader);
      String line = br.readLine();
      int numberOfCustomers = 0;
      if (line != null) {
        numberOfCustomers = Integer.parseInt(line);
        if (myHandler == null) {
          throw new SAXException("ContentHandler is null.");
        }
        myHandler.startDocument();
        myHandler.startElement(myNameSpaceURI, ORDER_INFO, ORDER_INFO, myAtts);
        for (int i = 0; i < numberOfCustomers; i++) {
          readCustomer(br);
        }
        myHandler.endElement(myNameSpaceURI, ORDER_INFO, ORDER_INFO);
        myHandler.endDocument();
      }
    } catch(SAXException se) {
      System.out.println("Example4Reader throws a SAX Exception.");
      throw se;
    } catch(IOException ioe) {
      System.out.println("Example4Reader throws an IO Exception.");
      throw ioe;
    } catch(NumberFormatException nfe) {
      System.out.println("Example4Reader throws a format exception.");
      System.out.println(nfe.getMessage());
      throw new SAXException("Error in input file format.");
    }
  } 


  private void readCustomer(BufferedReader _br) 
    throws SAXException, IOException {
    String customerLine = _br.readLine();
    String[] customerElements = customerLine.split(":");
    String group = customerElements[0];
    Attributes customerAttributes = getSimpleAttributes(GROUP, group);
    String id = customerElements[1];
    myHandler.startElement(myNameSpaceURI, CUSTOMER, CUSTOMER, customerAttributes);
    myHandler.startElement(myNameSpaceURI, ID, ID, myAtts);
    sendCharacters(id);
    myHandler.endElement(myNameSpaceURI, ID, ID);  
    readOrders(_br);
    myHandler.endElement(myNameSpaceURI, CUSTOMER, CUSTOMER);
  }


  private void readOrders(BufferedReader _br) throws SAXException, IOException {
    myHandler.startElement(myNameSpaceURI, SERVICE_ORDERS, SERVICE_ORDERS, myAtts);
    String line = _br.readLine();
    int numberOfOrders = Integer.parseInt(line);
    String productIdLine = null;
    String priceLine = null;
    String timeStampLine = null;    
    String[] lineElements = null; 
    for (int i = 0; i < numberOfOrders; i++) {
      myHandler.startElement(myNameSpaceURI,  ORDER, ORDER, myAtts);
      productIdLine = _br.readLine();
      lineElements = productIdLine.split(":");
      sendSimpleElement(PRODUCT_ID, lineElements[1]);
      priceLine = _br.readLine();
      lineElements = priceLine.split(":");
      sendSimpleElement(PRICE, lineElements[1]);
      timeStampLine = _br.readLine();     
      lineElements = timeStampLine.split(":");
      sendSimpleElement(TIMESTAMP, lineElements[1]);
      myHandler.endElement(myNameSpaceURI, ORDER, ORDER);       
    }
    myHandler.endElement(myNameSpaceURI, SERVICE_ORDERS, SERVICE_ORDERS);       
  }


  private void sendCharacters(String _chStr) throws SAXException {
    if (_chStr == null) {
      return;
    }   
    int start = 0;
    int length = _chStr.length();
   _chStr.getChars(start,
      length,
      myChArray,
      start);
    myHandler.characters(myChArray, start, length);
  }; 


  private void sendSimpleElement(String _elemName, String _elemValue) throws SAXException {
    myHandler.startElement(myNameSpaceURI,  _elemName, _elemName, myAtts);
    sendCharacters(_elemValue);
    myHandler.endElement(myNameSpaceURI, _elemName, _elemName);    
  }


  private Attributes getSimpleAttributes(String _attName, String _Attrvalue) {
    AttributesImpl atts = new AttributesImpl(); 
    atts.addAttribute("", _attName, _attName, "CDATA", _Attrvalue); 
    return atts;  
  };


  public void setContentHandler(ContentHandler _handler) {
    myHandler = _handler;
  }   

  public ContentHandler getContentHandler() {
    return myHandler;
  }

  public void setErrorHandler(ErrorHandler _handler) { 
  }
    
  public ErrorHandler getErrorHandler() { 
    return null; 
  }

  public void parse(String _systemId)
  throws IOException, SAXException { 
  }
     
  public DTDHandler getDTDHandler() { 
    return null; 
  }
  
  public EntityResolver getEntityResolver() { 
    return null; 
  }
  
  public void setEntityResolver(EntityResolver _resolver) { 
  }
  
  public void setDTDHandler(DTDHandler _handler) { 
  }
  
  public Object getProperty(String _name) { 
    return null; 
  }
    
  public void setProperty(String _name, Object _value) { 
  } 

  public void setFeature(String _name, boolean _value) { 
  }
    
  public boolean getFeature(String _name) { 
    return false;
  } 
}