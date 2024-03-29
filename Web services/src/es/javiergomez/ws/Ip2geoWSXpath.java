package es.javiergomez.ws;

// Invoke ip2geo WS in http://ws.cdyne.com/ip2geo/ip2geo.asmx?wsdl to return IP Geolocation
//
//Response
//
//<?xml version="1.0" encoding="utf-8"?>
//<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
//<soap:Body>
//	<ResolveIPResponse xmlns="http://ws.cdyne.com/">
//		<ResolveIPResult>
//			<City>Madrid</City>
//			<StateProvince>29</StateProvince>
//			<Country>Spain</Country>
//			<Organization />
//			<Latitude>40.4086</Latitude>
//			<Longitude>-3.6922</Longitude>
//			<AreaCode>0</AreaCode>
//			<TimeZone />
//			<HasDaylightSavings>false</HasDaylightSavings>
//			<Certainty>90</Certainty>
//			<RegionName />
//			<CountryCode>ES</CountryCode>
//		</ResolveIPResult>
//	</ResolveIPResponse>
//</soap:Body>
//</soap:Envelope>
//


import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Ip2geoWSXpath {

	final static String ARG1 = "154.23.98.55";
	final static String ARG2 = "0";


	final static String ENDPOINT = "http://ws.cdyne.com/ip2geo/ip2geo.asmx"; //port address location
	final static String SOAPACTION = "http://ws.cdyne.com/ResolveIP";  // Operation
	final static String URN = "http://ws.cdyne.com/"; //nameSpace

    
	final static String PARAMTYPE = "ResolveIP";
	final static String PARAM1NAME = "ipAddress";
	final static String PARAM2NAME = "licenseKey";
    
	public static void main(String[] args) {


        try
        {
 
            // create the actual message
            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SOAPMessage message = messageFactory.createMessage();

            // SOAPHeader header = message.getSOAPHeader();
            SOAPBody body = message.getSOAPBody();

            QName bodyName = new QName(URN,PARAMTYPE, "urn");
            SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

            QName param1 = new QName(URN,PARAM1NAME, "urn");
            SOAPElement ipEl = bodyElement.addChildElement(param1);
            ipEl.addTextNode(ARG1);

            QName param2 = new QName(URN,PARAM2NAME, "urn");
            SOAPElement licenseEl = bodyElement.addChildElement(param2);
            licenseEl.addTextNode(ARG2);
            
            MimeHeaders headers = message.getMimeHeaders();  
            headers.addHeader("SOAPAction", SOAPACTION); 
            

            
            // Check the input
            
            System.out.println("\nRequest:\n");
            message.writeTo(System.out);
            System.out.println();

            // create the connection
            SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnFactory.createConnection();

            // Send the message and get the reply
            SOAPMessage reply = connection.call(message, ENDPOINT);
            
            // Close the connection           
            connection.close();
            
            System.out.println("\nResponse:\n");
            reply.writeTo(System.out);
            System.out.println();
        
            // Retrieve the result - no error checking is done: BAD!
            SOAPBody responseBody = reply.getSOAPBody();

            //Xpath
            Node node = responseBody.getFirstChild();

/*        	
        	File fXmlFile = new File("resources/BodyResponse.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
         
        	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            Node node = doc.getFirstChild().getFirstChild();
*/            MainClass.print(node, System.out);

        	
            XPath xpath = XPathFactory.newInstance().newXPath();

            xpath.setNamespaceContext(context);
            
            String expression = "/ResolveIPResponse/ResolveIPResult/City";
            String eval = xpath.evaluate(expression, node);
            System.out.println(expression + " evaluation = " + eval);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
