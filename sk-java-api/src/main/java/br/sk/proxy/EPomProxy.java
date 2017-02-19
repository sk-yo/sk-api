package br.sk.proxy;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.sk.model.EPom;
import br.sk.model.EPomDependency;
import br.sk.model.EPomParent;
import br.sk.utils.XMLParser;

public class EPomProxy extends EPom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private XMLParser xmlParser;

	public EPomProxy(File file) throws SAXException, IOException, ParserConfigurationException {
		super();
		this.xmlParser = new XMLParser(file, false);
	}

	@Override
	public EPomParent getParent() {
		if (this.parent == null) {
			this.parent = new EPomParent();
			List<Node> dependecyNodes = xmlParser.getNodesByXPathExpression("/project/parent");
			for (Node dependencyNode : dependecyNodes) {
				NodeList nodeList = dependencyNode.getChildNodes();
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node childNode = nodeList.item(i);
					if (!childNode.getNodeName().equals("#text")) {
						String nodeName = childNode.getNodeName();
						String nodeContent = childNode.getTextContent();
						if (nodeName.equals("groupId")) {
							this.parent.setGroupId(nodeContent);
						} else if (nodeName.equals("artifactId")) {
							this.parent.setArtifactId(nodeContent);
						} else if (nodeName.equals("version")) {
							this.parent.setVersion(nodeContent);
						}
					}
				}
			}
		}
		return this.parent;
	}

	@Override
	public Set<EPomDependency> getDependecies() {
		if (this.dependecies == null) {
			this.dependecies = new HashSet<>();
			List<Node> dependecyNodes = xmlParser.getNodesByXPathExpression("/project/dependencies/dependency");
			for (Node dependencyNode : dependecyNodes) {
				NodeList nodeList = dependencyNode.getChildNodes();
				EPomDependency pomDependency = new EPomDependency();
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node childNode = nodeList.item(i);
					if (!childNode.getNodeName().equals("#text")) {
						String nodeName = childNode.getNodeName();
						String nodeContent = childNode.getTextContent();
						if (nodeName.equals("groupId")) {
							pomDependency.setGroupId(nodeContent);
						} else if (nodeName.equals("artifactId")) {
							pomDependency.setArtifactId(nodeContent);
						} else if (nodeName.equals("version")) {
							pomDependency.setVersion(nodeContent);
						} else if (nodeName.equals("scope")) {
							pomDependency.setScope(nodeContent);
						}
					}
				}
				this.dependecies.add(pomDependency);
			}
		}
		return this.dependecies;
	}

}
