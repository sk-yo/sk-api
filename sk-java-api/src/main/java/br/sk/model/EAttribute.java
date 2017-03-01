package br.sk.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = "name")
@JsonTypeName("EAttribute")
public class EAttribute implements Serializable, Comparable<EAttribute> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected String name;

	/**
	 * 
	 */
	protected String getterName;

	/**
	 * 
	 */
	protected String setterName;

	/**
	 * 
	 */
	protected String type;

	/**
	 * 
	 */
	protected String shortType;

	/**
	 * 
	 */
	protected Set<String> modifiers;

	/**
	 * 
	 */
	protected Map<Integer, String> genericTypes;

	/**
	 * 
	 */
	protected Set<EAnnotation> annotations;

	/**
	 * 
	 */
	protected boolean collectionAttribute;

	/**
	 * 
	 */
	protected boolean idAttribute;

	public EAttribute() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGetterName() {
		return getterName;
	}

	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}

	public String getSetterName() {
		return setterName;
	}

	public void setSetterName(String setterName) {
		this.setterName = setterName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShortType() {
		return shortType;
	}

	public void setShortType(String shortType) {
		this.shortType = shortType;
	}

	public Set<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(Set<String> modifiers) {
		this.modifiers = modifiers;
	}

	public Map<Integer, String> getGenericTypes() {
		return genericTypes;
	}

	public void setGenericTypes(Map<Integer, String> genericTypes) {
		this.genericTypes = genericTypes;
	}

	public Set<EAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<EAnnotation> annotations) {
		this.annotations = annotations;
	}

	public boolean isCollectionAttribute() {
		return collectionAttribute;
	}

	public void setCollectionAttribute(boolean collectionAttribute) {
		this.collectionAttribute = collectionAttribute;
	}

	public boolean isIdAttribute() {
		return idAttribute;
	}

	public void setIdAttribute(boolean idAttribute) {
		this.idAttribute = idAttribute;
	}

	@Override
	public int compareTo(EAttribute o) {
		return this.getName().compareTo(o.getName());
	}

}
