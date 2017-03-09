package br.sk.model.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.qdox.model.JavaClass;

import br.sk.model.jpa.Entity;
import br.sk.model.jpa.EntityAttribute;

public class EntityImpl implements Entity {

	private JavaClass javaClass;

	public EntityImpl(JavaClass javaClass) {
		super();
		this.javaClass = javaClass;
	}

	@Override
	public String getName() {
		return javaClass.getName();
	}

	@Override
	public String getInstanceName() {
		return StringUtils.uncapitalize(javaClass.getName());
	}

	@Override
	public String getPluralizedInstanceName() {
		return javaClass.getName().concat("s");
	}

	@Override
	public String getFullyQualifiedName() {
		return javaClass.getFullyQualifiedName();
	}

	@Override
	public String getTableName() {
		return null;
	}

	@Override
	public Set<EntityAttribute> getAttributes() {
		return null;
	}

}
