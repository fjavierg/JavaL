import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.FileNotFoundException;

public class Example1 implements Example {

  private static final String INPUT_DIRECTORY = System.getProperty("inputfiles");
  private static final String TRANSFORM_TEMPLATE_DIRECTORY = System.getProperty("templates");
  private static final String OUTPUT_DIRECTORY = System.getProperty("outputfiles");
  
  private TransformerFactory myTransformerFactory = null;
  private Transformer myTransformer = null;
  private Source myTransformTemplate = null;         
  private String myTransFormerTemplateName = null;

  public Example1() {
    myTransformerFactory = TransformerFactory.newInstance();        
  }

  protected Source getInputSourceObject(String _pathName) throws FileNotFoundException  {
    return getSourceObject(_pathName);
  }


  private Source getSourceObject(String _pathName) throws FileNotFoundException {
    File file = new File(_pathName);
    if (!fileExists(file)) {
      throw getFileNotFoundException(file);
    }
    StreamSource streamSource = new StreamSource(file);   
    return streamSource;
  }


  private Result getResultObject(String _fileName) throws FileNotFoundException {
    File file = new File(_fileName);
    StreamResult streamResult = new StreamResult(file);   
    return streamResult;
  }


  private void initTransformer(String _templateName) 
    throws FileNotFoundException, TransformerConfigurationException {
    if (myTransformer != null && myTransFormerTemplateName.equals(_templateName)) {
      return;
    }   
    myTransFormerTemplateName = _templateName;
    Source myTransformTemplate = getSourceObject(_templateName);
    myTransformer = myTransformerFactory.newTransformer(myTransformTemplate);  
  }
 
  protected boolean fileExists(File _file) {
    if (_file.isFile()) return true;
    if (_file.canRead()) return true;
    return false;
  }

  protected FileNotFoundException getFileNotFoundException(File _file) {
    String errorMessage = "File " + _file.getAbsolutePath() + " is not found, or is not Readable.";
    return new FileNotFoundException(errorMessage);
  }

  public void doTransform(String _inputFileName, String _transformerFileName, String _outputFileName) 
    throws FileNotFoundException { 
    try {
      initTransformer( _transformerFileName);
    } catch (TransformerConfigurationException tce) {
      System.out.println("Exception in transformer initialization.");
      System.out.println(tce.getMessage());
      System.out.println();
      System.out.println("Stack trace follows.");
      System.out.println("--------------------");
      tce.printStackTrace(System.out);
    }
    Source myXMLSource = getInputSourceObject(_inputFileName);  
    Result myResult = getResultObject(_outputFileName);   
    try {
      myTransformer.transform(myXMLSource, myResult);
    } catch (TransformerException te) {
      System.out.println("Exception in transformation."); 
      System.out.println(te.getMessage());
      System.out.println();
      System.out.println("Stack trace follows.");
      System.out.println("--------------------");
      te.printStackTrace(System.out);
    }
  }    
}