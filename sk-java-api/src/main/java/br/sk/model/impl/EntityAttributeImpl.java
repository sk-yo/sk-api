package br.sk.model.impl;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.qdox.model.DocletTag;
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
		return javaField.getName();
	}

	@Override
	public String getGetterName() {
		return String.format("get%s", StringUtils.capitalize(javaField.getName()));
	}

	@Override
	public String getSetterName() {
		return String.format("set%s", StringUtils.capitalize(javaField.getName()));
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
		DocletTag label = javaField.getTagByName("label");
		return label != null ? label.getParameters()[0] : "";
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
