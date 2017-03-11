package br.sk.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thoughtworks.qdox.JavaProjectBuilder;

@JsonPropertyOrder(alphabetic = true)
public interface EntityAttribute {

	JavaProjectBuilder getBuilder();

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
	@JsonIgnore
	Set<Annotation> getAnnotations();

	/**
	 * 
	 * @return
	 */
	Entity getGenericType();

}
