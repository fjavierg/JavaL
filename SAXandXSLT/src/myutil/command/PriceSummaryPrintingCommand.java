package myutil.command;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;

import myutil.DefaultHandlerInterface;

public class PriceSummaryPrintingCommand implements Command {

  private Command myPriceCollectorCommand;
  private Attributes myAtts = new AttributesImpl();
  
  public static final String PRICE_SUMMARY = "PRICE_SUMMARY";
  public static final String PRODUCT = "PRODUCT";
  public static final String NAME = "NAME";
  public static final String SUM = "SUM";

  public PriceSummaryPrintingCommand(Command _collectorCommand) {
    myPriceCollectorCommand = _collectorCommand;
  }

  
  public void reset() {    
  };


  public Object getResult() {
    return null;
  };

  
  public void startElement(String _uri,
                           String _localName,
                           String _qName,
                           Attributes _attr,
                           XMLFilterImpl _caller,
                           DefaultHandlerInterface _default) throws SAXException {
    _default.defaultstartElementHandler(_uri, _localName, _qName, _attr);
  };


  public void characters(char[] ch,
                        int start,
                        int length,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
    _default.defaultCharactersHandler(ch, start, length);
  };


  public void endElement(String _uri,
                         String _localName,
                         String _qName,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
    insertSummary(_default, _uri);     
    _default.defaultEndElementHandler(_uri, _localName, _qName);
  };  
  

  private void insertSummary(DefaultHandlerInterface _default, String _uri) throws SAXException {
    _default.defaultstartElementHandler(_uri, PRICE_SUMMARY, PRICE_SUMMARY, myAtts);
    HashMap priceData = (HashMap)myPriceCollectorCommand.getResult();
    Set currentKeySet = priceData.keySet();
    Iterator keyIterator = currentKeySet.iterator();
    String productName = null;
    Integer priceSum = null;
    String priceSumAsString = null;
    while (keyIterator.hasNext()) {
      _default.defaultstartElementHandler(_uri, PRODUCT, PRODUCT, myAtts);
      productName = (String)keyIterator.next();
      emitSimpleElement(_default, _uri, NAME, productName);
      priceSumAsString = getPriceSumAsString(priceData, productName);
      emitSimpleElement(_default, _uri, SUM, priceSumAsString);
      _default.defaultEndElementHandler(_uri, PRODUCT, PRODUCT);
    }
    _default.defaultEndElementHandler(_uri, PRICE_SUMMARY, PRICE_SUMMARY);    
  }  


  private String getPriceSumAsString(HashMap _priceData, String _productName) {
    Integer priceSum = (Integer)_priceData.get(_productName);
    String priceSumAsString = null;
    if (priceSum != null) {
      priceSumAsString = priceSum.toString();
    } else {
      priceSumAsString = "";
    }
    return priceSumAsString;
  } 


  private void emitSimpleElement(DefaultHandlerInterface _default, String _uri, 
  String _elementName, String _elementValue) throws SAXException {
    _default.defaultstartElementHandler(_uri, _elementName, _elementName, myAtts);
    _default.defaultCharactersHandler(_elementValue.toCharArray(), 0, _elementValue.length()); 
    _default.defaultEndElementHandler(_uri, _elementName, _elementName);
  }
}