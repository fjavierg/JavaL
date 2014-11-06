package myutil;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

import org.xml.sax.SAXException;

import java.io.CharArrayWriter;

import myutil.command.Command;
import myutil.command.CommandFactory;

public class ModifyingXMLSource extends XMLFilterImpl implements DefaultHandlerInterface {  

  private CommandFactory factory = null;
  private Command currentCommand = null;
  private String tagIdentifier = "";

  public ModifyingXMLSource(XMLReader _reader, String _factory) {
    super(_reader);
    factory = CommandFactory.getFactory(_factory);       
  };


  public void defaultstartElementHandler(String _uri,
                           String _localName,
                           String _qName,
                           Attributes _attr) throws SAXException {
    super.startElement(_uri, _localName, _qName, _attr);
  };


  public void defaultCharactersHandler(char[] ch,
                        int start,
                        int length) throws SAXException {

    super.characters(ch, start, length);
  };


  public void defaultEndElementHandler(String _uri,
                         String _localName,
                         String _qName) throws SAXException {
    super.endElement(_uri, _localName, _qName);
  };




  public void startElement(String _uri,
                           String _localName,
                           String _qName,
                           Attributes _atts) throws SAXException {
    tagIdentifier += ("/" + _localName);
    try {
      currentCommand = factory.getCommand(tagIdentifier);
      currentCommand.startElement(_uri, _localName, _qName, _atts, this, this);
    } catch (SAXException sax) {
      System.out.println("ERROR ModifyingXMLSource.startElement");
      System.out.println();
      System.out.println(_uri);
      System.out.println(_localName);
      System.out.println(_qName);
      System.out.println("--------------");
      System.out.println(sax.getMessage());
      throw sax;
    } catch (Throwable t) {
      System.out.println("ERROR ModifyingXMLSource.startElement");
      System.out.println();
      System.out.println(_uri);
      System.out.println(_localName);
      System.out.println(_qName);
      System.out.println("--------------");
      System.out.println(t.getMessage());
    }     
  }


  public void characters(char[] ch,
                       int start,
                       int length)
                throws SAXException {
    try {
      currentCommand.characters(ch, start, length, this, this);
    } catch (SAXException sax) {
      System.out.println("SAXException in CustomerXMLHandler.characters");
      System.out.println();
      System.out.println("--------------");
      System.out.println(sax.getMessage());
      throw sax;
    } catch (Throwable t) {
      System.out.println("ERROR CustomerXMLHandler.characters");
      System.out.println();
      System.out.println("--------------");
      System.out.println(t.getMessage());
    }     
  };


  public void endElement(String _uri,
                         String _localName,
                         String _qName) throws SAXException {
    currentCommand = factory.getCommand(tagIdentifier);
    tagIdentifier = tagIdentifier.substring(0, tagIdentifier.length() - _localName.length() - 1);
    try {
        currentCommand.endElement(_uri, _localName, _qName,
          this, this);
    } catch (SAXException sax) {
      System.out.println("ERROR CustomerXMLHandler.endElement");
      System.out.println();
      System.out.println(_uri);
      System.out.println(_localName);
      System.out.println(_qName);
      System.out.println("--------------");
      System.out.println(sax.getMessage());
      throw sax;
    } catch (Throwable t) {
      System.out.println("ERROR CustomerXMLHandler.endElement");
      System.out.println();
      System.out.println(_uri);
      System.out.println(_localName);
      System.out.println(_qName);
      System.out.println("--------------");
      System.out.println(t.getMessage());
    }    
  }
}