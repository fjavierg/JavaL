package myutil.command;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import myutil.dataAccess.DataAccessor;
import myutil.dataAccess.DataAccessorFactory;

import java.io.CharArrayWriter;

import myutil.DefaultHandlerInterface;

public class ProductIdCommand implements Command {

  private CharArrayWriter contents = new CharArrayWriter(); 

  private String idString = "";

  public void reset() {    
  };

  // return the name of the last product handled
  public Object getResult() {
    return idString;
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
  };


  public void endElement(String _uri,
                         String _localName,
                         String _qName,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
    idString = contents.toString();
    DataAccessorFactory myFactory = DataAccessorFactory.getFactory();
    DataAccessor myAccessor = myFactory.getDataAccessor();
    idString = myAccessor.getProductName(idString);      
    _default.defaultCharactersHandler(idString.toCharArray(), 0, idString.length());
    _default.defaultEndElementHandler(_uri, _localName, _qName);
  };  
 
}