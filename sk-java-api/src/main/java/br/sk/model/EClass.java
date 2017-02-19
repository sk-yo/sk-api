package br.sk.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.EqualsAndHashCode;

/**
 * Modelo de uma classe Java.
 * 
 * @author jcruz
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = "fullyQualifiedName")
@JsonTypeName("EClass")
public class EClass implements Serializable, Comparable<EClass> {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	protected ESourceFolder sourceFolder;

	/**
	 * 
	 */
	protected String path;

	/**
	 * 
	 */
	protected String name;

	/**
	 * 
	 */
	protected String fullyQualifiedName;

	/**
	 * 
	 */
	protected EPackage classPackage;

	/**
	 * 
	 */
	// @JsonIgnore
	protected Set<EAttribute> attributes;

	/**
	 * 
	 */
	// @JsonIgnore
	protected Set<EMethod> methods;

	/**
	 * 
	 */
	// @JsonIgnore
	protected Set<EAnnotation> annotations;

	public EClass() {
		super();
	}

	public ESourceFolder getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(ESourceFolder sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}

	public void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}

	public EPackage getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(EPackage classPackage) {
		this.classPackage = classPackage;
	}

	public Set<EAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<EAttribute> attributes) {
		this.attributes = attributes;
	}

	public Set<EMethod> getMethods() {
		return methods;
	}

	public void setMethods(Set<EMethod> methods) {
		this.methods = methods;
	}

	public Set<EAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<EAnnotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public int compareTo(EClass o) {
		return this.getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
	}

}
