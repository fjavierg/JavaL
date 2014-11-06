package es.javiergomez.ws;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MainClass {
  public static void print(Node node, OutputStream os) {
    PrintStream ps = new PrintStream(os);
    switch (node.getNodeType()) {
    case Node.ELEMENT_NODE:
      ps.print("<" + node.getNodeName());

      NamedNodeMap map = node.getAttributes();
      for (int i = 0; i < map.getLength(); i++) {
        ps.print(" " + map.item(i).getNodeName() + "=\""
            + map.item(i).getNodeValue() + "\"");
      }
      ps.println(">");
      return;
    case Node.ATTRIBUTE_NODE:
      ps.println(node.getNodeName() + "=\"" + node.getNodeValue() + "\"");
      return;
    case Node.TEXT_NODE:
      ps.println(node.getNodeValue());
      return;
    case Node.CDATA_SECTION_NODE:
      ps.println(node.getNodeValue());
      return;
    case Node.PROCESSING_INSTRUCTION_NODE:
      ps.println(node.getNodeValue());
      return;
    case Node.DOCUMENT_NODE:
    case Node.DOCUMENT_FRAGMENT_NODE:
      ps.println(node.getNodeName() + "=" + node.getNodeValue());
      return;
    }
  }
}