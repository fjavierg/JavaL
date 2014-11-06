package es.javiergomez.tutorial.jaxb;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBUnmarshallExample {
	public static void main(String[] args) {
		

 
	 try {
 
		InputStream inputStream =  
				 JAXBUnmarshallExample.class.getClassLoader().getResourceAsStream("XMLFile.xml");
		 
		//File file = new File("D:\\Documents and Settings\\javierg\\My Documents\\workspace-juno\\Tutorial\\resources\\es\\javiergomez\\tutorial\\jaxb\\XMLFile.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Customer customer = (Customer) jaxbUnmarshaller.unmarshal(inputStream);
		System.out.println(customer.getName());
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
	}
}