package br.sk.model.jpa;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.sk.model.core.EAnnotation;
import br.sk.model.jpa.enums.MultiplicityType;
import br.sk.model.jpa.enums.RelationshipType;

@JsonPropertyOrder(value = { "name", "label", "getterName", "setterName" })
public interface EntityAttribute {

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
	 * 
	 * @return
	 */
	String getLabel();

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
