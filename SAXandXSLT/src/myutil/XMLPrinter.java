package myutil;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

import org.xml.sax.SAXException;

import java.io.CharArrayWriter;

public class XMLPrinter extends XMLFilterImpl {
  
  private CharArrayWriter contents = new CharArrayWriter();  

  private String indent = "";

  public XMLPrinter(XMLReader _reader) {
    super(_reader);    
  };


  public void startElement(String _uri,
                           String _localName,
                           String _qName,
                           Attributes _atts) throws SAXException {
    String line = indent + "<" + _localName;
    if (_atts.getLength() > 0) {
      for (int i = 0; i < _atts.getLength(); i++) {
        line = line + " " + _atts.getLocalName(i) + "=\"" + _atts.getValue(i) + "\""; 
      } 
    }
    line = line + ">";
    System.out.println(line);
    indent += "  ";
    super.startElement(_uri, _localName, _qName, _atts);        
  }


  public void characters(char[] _ch,
                       int _start,
                       int _length)
                throws SAXException {
    contents.write(_ch, _start, _length);
    super.characters(_ch, _start, _length);
  };


  public void endElement(String _uri,
                         String _localName,
                         String _qName) throws SAXException {
    indent = indent.substring(0, indent.length()-2);
    String elementValue = contents.toString();
    elementValue = elementValue.trim();
    if (!elementValue.equals("")) {
      System.out.println(indent + "  " + elementValue);
    }
    System.out.println(indent + "</" + _localName + ">");

    contents.reset();
    super.endElement(_uri, _localName, _qName);
  }
}
