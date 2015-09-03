package app.techno;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathTests {

	public static void main(String[] args) {

		try {
			thirdTest();
		} catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void firstTest() {
		// Xpath du defect xml
		// testcases/testcase/defect/@link
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();

			// String xml = ...;
			// Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));

			XPath xPath = XPathFactory.newInstance().newXPath();

			// String expression = "//defect";//Select all defect nodes
			// String expression = "/testcases/testcase[position() <= 4]/defect";//Select the five firsts testcase nodes in the list
			// @ pour sélectionner un attribut, mettre [age>40] => age est un child est cette expression fait le test sur ça valeur
			// String expression = "/testcases/testcase/defect[@type='jira']";//Select all defects with jira type attribute
			// String expression = "/testcases/testcase[1]";//Select the first testcase
			// String expression = "/testcases/testcase[last()]";//Select the last testcase node
			// String expression = "/testcases/testcase[@actualStatus='failed'][@status='passed']/defect[@type='jira']";
			//
			// NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			// Node n;
			// if (nodeList.getLength() > 0) {
			// for (int i = 0; i < nodeList.getLength(); i++) {
			// n = nodeList.item(i);
			// // System.out.print(n.getNodeName() + " : " + n.getNodeValue() + " : " + n.getTextContent() );
			// // if(n.getAttributes().getLength()>0){
			// // System.out.print(" : attri. : "+n.getAttributes().getNamedItem("status"));
			// // }
			// // System.out.println();
			// System.out.println("\nCurrent Element :" + n.getNodeName());
			// if (n.getNodeType() == Node.ELEMENT_NODE) {
			// Element e = (Element) n;
			// System.out.println("expectedMessage : " + e.getAttribute("expectedMessage"));// Get attribute value
			// System.out.println("link : " + e.getAttribute("link"));
			// System.out.println("status : " + e.getAttribute("status"));
			// System.out.println("type : " + e.getAttribute("type"));
			//
			// System.out.println("name : " + ((Element) e.getParentNode()).getAttribute("name"));
			// // System.out.println("First Name : " + e.getElementsByTagName("firstname").item(0).getTextContent());// get identified child
			// // // content
			// // System.out.println("Last Name : " + e.getElementsByTagName("lastname").item(0).getTextContent());
			// // System.out.println("Nick Name : " + e.getElementsByTagName("nickname").item(0).getTextContent());
			// // System.out.println("Marks : " + e.getElementsByTagName("marks").item(0).getTextContent());
			// }
			//
			// }

			Document document = builder.parse(new FileInputStream(new File("test-defects.xml")));
			// String expression = "/result/suites/suite/cases/case[skipped='true']/className";

			// Document document = builder.parse(new FileInputStream(new File("test-defects.xml")));
			String expression = "/testcases/testcase[@actualStatus='failed'][@status='passed']/defect[@type='jira']/@type";

			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			Node n;
			if (nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					n = nodeList.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						System.out.println(e.getTextContent());
						// System.out.println(e.getNodeValue());
						// System.out.println(e.getFirstChild().getNodeValue());
						// System.out.println(e.getFirstChild().getTextContent());
						// System.out.println(e.getNodeName());
						// System.out.println(e.getAttribute(e.get));
						// String s = e.getElementsByTagName("className").item(0).getTextContent();
						// sb.append(s);
						// System.out.println(s);
						System.out.println(e.getAttribute("type"));
					} else if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
						Attr attr = (Attr) n;
						System.out.println(attr.getValue());
					}
					// else if (n.getNodeType() == Node.CDATA_SECTION_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.COMMENT_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.DOCUMENT_FRAGMENT_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.DOCUMENT_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.ENTITY_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.NOTATION_NODE) {
					// System.out.println("");
					// }else if (n.getNodeType() == Node.TEXT_NODE) {
					// System.out.println("");
					// }
				}
			}
			// }

		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void secondTest() {
		String fileName = "test-defects.xml";
		String expression = "";
		// ViewEntryColor vec5;
		// Run<?, ?> run = getLatestCompletedRun();

		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new FileInputStream(new File(fileName)));
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			Node n;
			if (nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					n = nodeList.item(i);

					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						// vec5 = xmlItem.getStatusMapping().get(e.getTextContent());
						// if (vec5 != null) {
						// xmlResults.add(vec5);
						// }
					} else if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
						Attr attr = (Attr) n;
						// vec5 = xmlItem.getStatusMapping().get(attr.getValue());
						// if (vec5 != null) {
						// xmlResults.add(vec5);
						// }
					}
				}
			}

		} catch (ParserConfigurationException ex) {
			// Logger.getLogger(ProjectWrapper.class.getName()).log(Level.SEVERE, "ParserConfigurationException : {0}", Arrays.toString(ex.getStackTrace()));
		} catch (FileNotFoundException ex) {
			// Logger.getLogger(ProjectWrapper.class.getName()).log(Level.SEVERE, "FileNotFoundException : {0}", ex.getMessage());
		} catch (SAXException ex) {
			// Logger.getLogger(ProjectWrapper.class.getName()).log(Level.SEVERE, "SAXException : {0}", ex.getMessage());
		} catch (IOException ex) {
			// Logger.getLogger(ProjectWrapper.class.getName()).log(Level.SEVERE, "IOException : {0}", ex.getMessage());
		} catch (XPathExpressionException ex) {
			// Logger.getLogger(ProjectWrapper.class.getName()).log(Level.SEVERE, "XPathExpressionException : {0}", ex.getMessage());
		} catch (Exception ex) {
		}
	}

	private static void thirdTest() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, XPathExpressionException {

		// String file = "test-defects.xml";
		// String expression = "\\\\defect";
		// DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		// DocumentBuilder builder = builderFactory.newDocumentBuilder();
		// Document document = builder.parse(new FileInputStream(file));
		// NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile(expression).evaluate(document, XPathConstants.NODESET);
		// Node n;
		// if (nodeList.getLength() > 0) {
		// for (int i = 0; i < nodeList.getLength(); i++) {
		// n = nodeList.item(i);
		// // returned XPATH handling
		// // regex
		// // count
		// if (n.getNodeType() == Node.ELEMENT_NODE) {
		// Element e = (Element) n;
		//
		// } else if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
		// Attr attr = (Attr) n;
		//
		// }
		// }
		// }

		try {
			FileInputStream file = new FileInputStream(new File("test-defects.xml"));

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(file);

			XPath xPath = XPathFactory.newInstance().newXPath();

			System.out.println("*************************");
			String expression = "//defect/@type";
			String result = xPath.compile(expression).evaluate(xmlDocument);
			System.out.println(result);

			System.out.println("*************************");
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
			}

			System.out.println("*************************");
			Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			if (null != node) {
				nodeList = node.getChildNodes();
				for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
					Node nod = nodeList.item(i);
					if (nod.getNodeType() == Node.ELEMENT_NODE)
						System.out.println(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue());
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
