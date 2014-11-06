import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.XMLFilterImpl;

import myutil.XMLPrinter;
import myutil.ModifyingXMLSource;

public class Example2 extends Example1 {

  private static final String MODE = System.getProperty("mode");

  protected static String FACTORY_NAME = "Example2";

  protected Source getInputSourceObject(String _pathName) throws FileNotFoundException  {

    InputSource inputSource = getInputSourceFromFile(_pathName);
    XMLReader xmlFilter = getFilteringReader();
    // SAXSource saxSource = new SAXSource(inputSource);
    SAXSource saxSource = new SAXSource(xmlFilter, inputSource);
    return saxSource;
  }


  private InputSource getInputSourceFromFile(String _fileName) throws FileNotFoundException {
    File file = new File(_fileName);
    if (!fileExists(file)) {
      throw getFileNotFoundException(file);
    }
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
    } catch(FileNotFoundException fnfe) {
      System.out.println("Input source file not found.");  
    }
    InputSource inputSource = new InputSource(fis);
    return inputSource;
  };
  

  private XMLReader getFilteringReader() { 
    XMLReader myReader = getReader();
    XMLFilterImpl xmlFilter = new ModifyingXMLSource(myReader, FACTORY_NAME);
    if ((MODE != null) && (MODE.equals("debug"))) {
      xmlFilter = new XMLPrinter(xmlFilter);
    }
    return xmlFilter;
  }


  protected XMLReader getReader() { 
    XMLReader myReader = null;
    try {
      myReader = XMLReaderFactory.createXMLReader();
    } catch (SAXException e) {
      System.err.println("Error in creating XMLReader instance");
    }
    return myReader;
  } 
}