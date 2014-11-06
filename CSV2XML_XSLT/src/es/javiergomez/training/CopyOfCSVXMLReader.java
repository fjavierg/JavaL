package es.javiergomez.training;

import java.io.*;
import java.net.URL;
 
import org.xml.sax.*;
import org.xml.sax.helpers.*;
 
 
/**
* A utility class that parses a Comma 
* Separated Values (CSV) file and outputs its 
* contents using SAX2 events. The format of CSV 
* that this class reads is identical to the export 
* format for Microsoft Excel. For simple values, the 
* CSV file may look like this:
* <pre>
* a,b,c
* d,e,f
* </pre>
* Quotes are used as delimiters when the values 
* contain commas:
* <pre>
* a,"b,c",d
* e,"f,g","h,i"
* </pre>
* And double quotes are used when the values 
* contain quotes. This parser is smart enough 
* to trim spaces around commas, as well.
*
* @author Eric M. Burke
*/
public class CopyOfCSVXMLReader extends AbstractXMLReader {
 
  // an empty attribute for use with SAX
  private static final Attributes EMPTY_ATTR = new AttributesImpl( );
 
  /**
   * Parse a CSV file. SAX events are 
   * delivered to the ContentHandler
   * that was registered via 
   * <code>setContentHandler</code>.
   *
   * @param input the comma separated 
   * values file to parse.
   */  
  private Attributes myAtts = new AttributesImpl(); 
  private String myNameSpaceURI = "";
  public final static String CSVFILE = "csvFile";
  public final static String LINE = "line";
  public final static String VALUE = "value";
  
  public void parse(InputSource input) throws IOException,
      SAXException {
    // if no handler is registered to receive events, don't bother
    // to parse the CSV file


    ContentHandler ch = getContentHandler( );
    if (ch == null) {
      return;
    }
 
    String hello = "HELLO WORLD";
    
    ch.startDocument( );
 
    // emit <csvFile>
    ch.startElement(myNameSpaceURI,CSVFILE,CSVFILE,myAtts);
        ch.startElement(myNameSpaceURI,LINE,LINE,myAtts);
        	ch.startElement(myNameSpaceURI,VALUE,VALUE,myAtts);
        		ch.characters(hello.toCharArray(), 0, hello.length( ));
        	ch.endElement(myNameSpaceURI,VALUE,VALUE);
        ch.endElement(myNameSpaceURI,LINE,LINE);
    ch.endElement(myNameSpaceURI,CSVFILE,CSVFILE);
    ch.endDocument( );
    
  }
 
  // Break an individual line into tokens. 
  // This is a recursive function
  // that extracts the first token, then 
  // recursively parses the
  // remainder of the line.
  private void parseLine(String curLine, ContentHandler ch)
    throws IOException, SAXException {
 
    String firstToken = null;
    String remainderOfLine = null;
    int commaIndex = locateFirstDelimiter(curLine);
    if (commaIndex > -1) {
      firstToken = curLine.substring(0, commaIndex).trim( );
      remainderOfLine = curLine.substring(commaIndex+1).trim( );
    } else {
      // no commas, so the entire line is the token
      firstToken = curLine;
    }
 
    // remove redundant quotes
    firstToken = cleanupQuotes(firstToken);
 
    // emit the <value> element
    ch.startElement("","","value",EMPTY_ATTR);
    ch.characters(firstToken.toCharArray(), 0, firstToken.length( ));
    ch.endElement("","","value");
 
    // recursively process the remainder of the line
    if (remainderOfLine != null) {
      parseLine(remainderOfLine, ch);
    }
  }
 
  // locate the position of the comma, 
  // taking into account that
  // a quoted token may contain ignorable commas.
  private int locateFirstDelimiter(String curLine) {
    if (curLine.startsWith("\"")) {
      boolean inQuote = true;
      int numChars = curLine.length( );
      for (int i=1; i<numChars; i++) {
        char curChar = curLine.charAt(i);
        if (curChar == '"') {
          inQuote = !inQuote;
        } else if (curChar == ',' && !inQuote) {
          return i;
        }
      }
      return -1;
    } else {
      return curLine.indexOf(',');
    }
  }
 
  // remove quotes around a token, as well as pairs of quotes
  // within a token.
  private String cleanupQuotes(String token) {
    StringBuffer buf = new StringBuffer( );
    int length = token.length( );
    int curIndex = 0;
 
    if (token.startsWith("\"") && token.endsWith("\"")) {
      curIndex = 1;
      length--;
    }
 
    boolean oneQuoteFound = false;
    boolean twoQuotesFound = false;
 
    while (curIndex < length) {
      char curChar = token.charAt(curIndex);
      if (curChar == '"') {
        twoQuotesFound = (oneQuoteFound) ? true : false;
oneQuoteFound = true;
      } else {
        oneQuoteFound = false;
        twoQuotesFound = false;
      }
 
      if (twoQuotesFound) {
        twoQuotesFound = false;
        oneQuoteFound = false;
        curIndex++;
        continue;
      }
 
      buf.append(curChar);
      curIndex++;
    }
 
    return buf.toString( );
  }
}
