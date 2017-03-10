package br.sk.model.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;

import br.sk.model.jpa.Entity;
import br.sk.model.jpa.EntityAttribute;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityImpl implements Entity {

	private JavaClass javaClass;

	private Set<EntityAttribute> attributes;

	@JsonIgnore
	private JavaProjectBuilder builder;

	public EntityImpl(JavaProjectBuilder builder, JavaClass javaClass) {
		super();
		this.builder = builder;
		this.javaClass = javaClass;

	}

	@Override
	public JavaProjectBuilder getBuilder() {
		return this.builder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.Entity#getName()
	 */
	@Override
	public String getName() {
		return javaClass.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.Entity#getInstanceName()
	 */
	@Override
	public String getInstanceName() {
		return StringUtils.uncapitalize(javaClass.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.Entity#getPluralizedInstanceName()
	 */
	@Override
	public String getPluralizedInstanceName() {
		return javaClass.getName().concat("s");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.Entity#getFullyQualifiedName()
	 */
	@Override
	public String getFullyQualifiedName() {
		return javaClass.getFullyQualifiedName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.Entity#getTableName()
	 */
	@Override
	public String getTableName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.Entity#getAttributes()
	 */
	@Override
	public Set<EntityAttribute> getAttributes() {
		if (this.attributes == null) {
			//// @formatter:off
			this.attributes = javaClass.getFields()
								.stream()
								.map(EntityAttributeImpl::new)
								.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.attributes;
	}

}
