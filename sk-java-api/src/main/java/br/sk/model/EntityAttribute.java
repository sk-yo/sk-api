package br.sk.model;

import java.util.LinkedList;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.sk.factory.EntityContext;

@JsonPropertyOrder(value = { "name" })
public interface EntityAttribute {

	@JsonIgnore
	EntityContext getContext();

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	Entity getEntity();

	/**
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 
	 * @return
	 */
	String getGetterName();

	/**
	 * 
	 * @return
	 */
	String getSetterName();

	/**
	 * Nome no singular em caso de listas.
	 * 
	 * @return
	 */
	String getSingularName();

	/**
	 * 
	 * @return
	 */
	String getType();

	/**
	 * 
	 * 
	 * @return
	 */
	@JsonProperty("isId")
	boolean isId();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isTypeList")
	boolean isTypeList();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isTypeNumber")
	boolean isTypeNumber();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isTypeDate")
	boolean isTypeDate();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isTypeString")
	boolean isTypeString();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isTypeBlob")
	boolean isTypeBlob();

	/**
	 * Flag identificando se o atributo é static.
	 * 
	 * @return
	 */
	@JsonProperty("isStatic")
	boolean isStatic();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isColumnUnique")
	boolean isColumnUnique();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isColumnNullable")
	boolean isColumnNullable();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isOrphanRemoval")
	boolean isOrphanRemoval();

	/**
	 * 
	 * @return
	 */
	String getMappedBy();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("hasMappedBy")
	boolean hasMappedBy();

	/**
	 * 
	 * @return
	 */
	String getLabel();

	/**
	 * 
	 * @return
	 */
	Integer getColumnLength();

	/**
	 * 
	 * @return
	 */
	String getColumnName();

	/**
	 * 
	 * @return
	 */
	String getGeneratedValueStrategy();

	/**
	 * 
	 */
	String getMultiplicity();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("hasMultiplicity")
	boolean hasMultiplicity();

	/**
	 * 
	 * @return
	 */
	LinkedList<String> getCascadeType();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("hasCascade")
	boolean hasCascade();

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	Set<Annotation> getAnnotations();

	/**
	 * 
	 * @return
	 */
	Entity getGenericType();

	/**
	 * 
	 * @return
	 */
	String getNavegability();

}
