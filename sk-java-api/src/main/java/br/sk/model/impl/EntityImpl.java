package br.sk.model.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;

import br.sk.model.Annotation;
import br.sk.model.Entity;
import br.sk.model.EntityAttribute;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityImpl implements Entity {

	private JavaClass javaClass;

	private Set<EntityAttribute> attributes;

	@JsonIgnore
	private JavaProjectBuilder builder;

	private Set<Annotation> annotations;

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
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Table"))
				.filter(ann -> ann.getParameters().containsKey("name"))
				.findFirst()
				.map(ann -> ann.getParameters().get("name"))
				.orElse(null);
		// @formatter:on
	}

	@Override
	public boolean hasHashCode() {
		//// @formatter:off
		return this.javaClass.getMethods().stream()
				.filter(method -> method.getName().equals("hashCode"))
				.findFirst()
				.isPresent();
		// @formatter:on
	}

	@Override
	public boolean hasEquals() {
		//// @formatter:off
		return this.javaClass.getMethods().stream()
				.filter(method -> method.getName().equals("equals"))
				.findFirst()
				.isPresent();
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getAnnotations()
	 */
	@Override
	public Set<Annotation> getAnnotations() {
		if (this.annotations == null) {
			//// @formatter:off
			this.annotations = javaClass.getAnnotations()
									.stream()
									.map(EAnnotationImpl::new)
									.collect(Collectors.toSet());
			// @formatter:on

		}
		return this.annotations;
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
								.map(javaField -> new EntityAttributeImpl(builder, javaField))
								.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.attributes;
	}

}
