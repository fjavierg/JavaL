package myutil;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public interface DefaultHandlerInterface {

  public void defaultstartElementHandler(String _uri,
                           String _localName,
                           String _qName,
                           Attributes _attr) throws SAXException;

  public void defaultCharactersHandler(char[] ch,
                        int start,
                        int length) throws SAXException;

  public void defaultEndElementHandler(String _uri,
                         String _localName,
                         String _qName) throws SAXException;
} 