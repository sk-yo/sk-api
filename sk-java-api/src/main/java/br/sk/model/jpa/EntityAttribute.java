package br.sk.model.jpa;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thoughtworks.qdox.JavaProjectBuilder;

import br.sk.model.core.EAnnotation;

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
	@JsonProperty("isList")
	boolean isList();

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
	@JsonProperty("isNumber")
	boolean isNumber();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isDate")
	boolean isDate();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isString")
	boolean isString();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isBlob")
	boolean isBlob();

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
	@JsonProperty("isUnidirecional")
	boolean isUnidirecional();

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	Set<EAnnotation> getAnnotations();

	/**
	 * 
	 * @return
	 */
	Entity getGenericType();

}
