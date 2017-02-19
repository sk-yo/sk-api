package br.sk.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	private File xmlFile;

	private XPath xpath;

	private Document doc;

	private boolean namespaceAware = false;

	public XMLParser(File xmlFile, boolean namespaceAware) throws SAXException, IOException, ParserConfigurationException {
		super();
		this.xmlFile = xmlFile;
		this.namespaceAware = namespaceAware;
		init();
	}

	private void init() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(this.namespaceAware);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.doc = dBuilder.parse(this.xmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		this.xpath = xPathfactory.newXPath();
	}

	/**
	 * Retorna uma lista de nos dada uma expressão XPATH.
	 * 
	 * @param expression
	 * @return
	 */
	public List<Node> getNodesByXPathExpression(String expression) {
		List<Node> nodes = new ArrayList<>();
		try {
			NodeList nodeList = (NodeList) this.xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				nodes.add(nodeList.item(i));
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodes;
	}

	/**
	 * 
	 * Retorna um nó dada uma expressão XPATH.
	 * 
	 * @param expression
	 * @return
	 */
	public Optional<Node> getNodeByXPathExpression(String expression) {
		try {
			Node node = (Node) this.xpath.compile(expression).evaluate(doc, XPathConstants.NODE);
			return Optional.ofNullable(node);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public File getXmlFile() {
		return xmlFile;
	}

}
