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
	@JsonProperty("isUnique")
	boolean isUnique();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("isNullable")
	boolean isNullable();

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
	Integer getLength();

	/**
	 * 
	 * @return
	 */
	String getColumnName();

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
