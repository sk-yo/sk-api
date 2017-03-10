package br.sk.model.impl;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoughtworks.qdox.model.JavaClass;

import br.sk.model.jpa.Entity;
import br.sk.model.jpa.EntityAttribute;

@JsonIgnoreProperties(ignoreUnknown= true)
public class EntityImpl implements Entity {

	private JavaClass javaClass;

	private Set<EntityAttribute> attributes;

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
		if (this.attributes == null) {
			//// @formatter:off
			this.attributes = Arrays.asList(javaClass.getFields())
								.stream()
								.map(EntityAttributeImpl::new)
								.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.attributes;
	}

}
