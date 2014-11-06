package myutil.command;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import java.io.CharArrayWriter;

import java.util.HashMap;

import myutil.DefaultHandlerInterface;

public class PriceCollectorCommand implements Command {

  private CharArrayWriter contents = new CharArrayWriter(); 
 
  private Command myProductIdCommand;  
  
  private HashMap myPriceSums = new HashMap();

  public PriceCollectorCommand(Command _command) {
    myProductIdCommand = _command; 
  }

  public void reset() {    
  };

  // return the name of the last product handled
  public Object getResult() {
    return myPriceSums;
  };
  
  public void startElement(String _uri,
                           String _localName,
                           String _qName,
                           Attributes _attr,
                           XMLFilterImpl _caller,
                           DefaultHandlerInterface _default) throws SAXException {
    contents.reset();
    _default.defaultstartElementHandler(_uri, _localName, _qName, _attr);
  };


  public void characters(char[] ch,
                        int start,
                        int length,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
    contents.write(ch, start, length);
    _default.defaultCharactersHandler(ch, start, length);
  };


  public void endElement(String _uri,
                         String _localName,
                         String _qName,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
    String productIdAsKey = (String)myProductIdCommand.getResult();
    String priceAsString = contents.toString();
    
    Integer price = priceToNumber(priceAsString);
    if (price != null) {
      addPriceToHashMap(productIdAsKey, price);
    }       
        
    _default.defaultEndElementHandler(_uri, _localName, _qName);
  };  



  private Integer priceToNumber(String _priceAsString) {
    Integer integerValue = null;
    try {
      integerValue = new Integer(_priceAsString);
    } catch (NumberFormatException nfe) {
      // returns null;
    }
    return integerValue;
  };    



  private void addPriceToHashMap(String _key, Integer _price) {
    Integer currentPrice = (Integer)myPriceSums.get(_key);
    if (currentPrice == null) {
      currentPrice = _price; 
    } else {
      int newSumPrice = currentPrice.intValue() + _price.intValue();
      currentPrice = new Integer(newSumPrice);
    }
    myPriceSums.put(_key, currentPrice);
  };
}