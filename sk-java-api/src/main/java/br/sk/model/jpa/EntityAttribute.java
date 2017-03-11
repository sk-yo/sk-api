package br.sk.model.jpa;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thoughtworks.qdox.JavaProjectBuilder;

import br.sk.model.core.EAnnotation;
import br.sk.model.jpa.enums.MultiplicityType;
import br.sk.model.jpa.enums.RelationshipType;

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
	boolean isId();

	/**
	 * 
	 * @return
	 */
	boolean isList();

	/**
	 * Flag identificando se o atributo é static.
	 * 
	 * @return
	 */
	boolean isStatic();

	/**
	 * 
	 * @return
	 */
	boolean isUnique();

	/**
	 * 
	 * @return
	 */
	boolean isNullable();
	
	/**
	 * 
	 * @return
	 */
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
	MultiplicityType getMultiplicityType();

	/**
	 * 
	 * @return
	 */
	RelationshipType getRelationshipType();

	/**
	 * 
	 * @return
	 */
	boolean isUnidirecional();

	/**
	 * 
	 * @return
	 */
	Set<EAnnotation> getAnnotations();

	/**
	 * 
	 * @return
	 */

	Entity getGenericType();

}
