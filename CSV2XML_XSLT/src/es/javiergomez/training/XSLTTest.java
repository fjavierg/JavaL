package es.javiergomez.training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author M.Vasudevarao
 */
public class XSLTTest
{
    /**
     * @param args the command line arguments
     * @throws SAXException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, SAXException
    {
        if (args.length != 3)
        {
            System.err.println("give command as follows : ");
            System.err.println("XSLTTest data.xml converted.xsl converted.html");
            return;
        }
        String dataXML = args[0];
        String inputXSL = args[1];
        String outputHTML = args[2];

        XSLTTest st = new XSLTTest();
        try
        {
            st.transform(dataXML, inputXSL, outputHTML);
        }
        catch (TransformerConfigurationException e)
        {
            System.err.println("TransformerConfigurationException");
            System.err.println(e);
        }
        catch (TransformerException e)
        {
            System.err.println("TransformerException");
            System.err.println(e);
        }
    }

    public void transform(String dataXML, String inputXSL, String outputHTML) 
    		throws FileNotFoundException,
            TransformerConfigurationException,
            TransformerException, SAXException
    {

        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(inputXSL);
        Transformer transformer = factory.newTransformer(xslStream);
//        Transformer transformer = factory.newTransformer();
        Source in = getInputSourceObject(dataXML);
//        StreamSource in = new StreamSource(dataXML);
        Result out = new StreamResult(System.out);
//        StreamResult out = new StreamResult(outputHTML);
        transformer.transform(in, out);
//       System.out.println("The generated HTML file is:" + outputHTML);
    }
    protected Source getInputSourceObject(String _pathName) throws FileNotFoundException, SAXException  {

        InputSource inputSource = getInputSourceFromFile(_pathName);
//        XMLReader myReader = XMLReaderFactory.createXMLReader();
        XMLReader myReader = new CSVXMLReader();
        // SAXSource saxSource = new SAXSource(inputSource);
        SAXSource saxSource = new SAXSource(myReader, inputSource);
        return saxSource;
      }
    private InputSource getInputSourceFromFile(String _fileName) throws FileNotFoundException {
        File file = new File(_fileName);

        FileInputStream fis = null;
        try {
          fis = new FileInputStream(file);
        } catch(FileNotFoundException fnfe) {
          System.out.println("Input source file not found.");  
        }
        InputSource inputSource = new InputSource(fis);
        return inputSource;
      }

}