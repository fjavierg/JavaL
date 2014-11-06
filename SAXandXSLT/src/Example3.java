import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.XMLReaderFactory;

public class Example3 extends Example2 {
  public Example3() {
    super();
    FACTORY_NAME = "Example2b";
  }

  protected XMLReader getReader() { 
    XMLReader myReader = new Example3Reader();
    return myReader;
  } 

}