package myutil.command;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import myutil.DefaultHandlerInterface;

public class FilterCommand implements Command {
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
  };

  public void characters(char[] ch,
                        int start,
                        int length,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
  };

  public void endElement(String _uri,
                         String _localName,
                         String _qName,
                         XMLFilterImpl _caller,
                         DefaultHandlerInterface _default) throws SAXException {
  };  
}