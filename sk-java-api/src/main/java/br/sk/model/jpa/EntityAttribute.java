package br.sk.model.jpa;

import java.util.Map;
import java.util.Set;

import br.sk.model.core.EAnnotation;
import br.sk.model.jpa.enums.MultiplicityType;
import br.sk.model.jpa.enums.RelationshipType;

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
	Map<Integer, Entity> getGenericTypes();

}
