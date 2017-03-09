package br.sk.model.impl;

import com.thoughtworks.qdox.model.JavaField;

import br.sk.model.jpa.EntityAttribute;
import br.sk.model.jpa.enums.MultiplicityType;
import br.sk.model.jpa.enums.RelationshipType;

public class EntityAttributeImpl implements EntityAttribute {

	private JavaField javaField;

	public EntityAttributeImpl(JavaField javaField) {
		super();
		this.javaField = javaField;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getGetterName() {
		return null;
	}

	@Override
	public String getSetterName() {
		return null;
	}

	@Override
	public String getSingularName() {
		return null;
	}

	@Override
	public boolean isId() {
		return false;
	}

	@Override
	public String getLabel() {
		return null;
	}

	@Override
	public String getColumnName() {
		return null;
	}

	@Override
	public MultiplicityType getMultiplicityType() {
		return null;
	}

	@Override
	public RelationshipType getRelationshipType() {
		return null;
	}

	@Override
	public boolean isUnidirecional() {
		return false;
	}

}
