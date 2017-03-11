package br.sk.model.impl;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;

import br.sk.model.Annotation;
import br.sk.model.Entity;
import br.sk.model.EntityAttribute;

public class EntityAttributeImpl implements EntityAttribute {

	private JavaField javaField;

	private Set<Annotation> annotations;

	@JsonIgnore
	private JavaProjectBuilder builder;

	public EntityAttributeImpl(JavaProjectBuilder builder, JavaField javaField) {
		super();
		this.builder = builder;
		this.javaField = javaField;
	}

	@Override
	public JavaProjectBuilder getBuilder() {
		return this.builder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getName()
	 */
	@Override
	public String getName() {
		return javaField.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getGetterName()
	 */
	@Override
	public String getGetterName() {
		return String.format("get%s", StringUtils.capitalize(javaField.getName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getSetterName()
	 */
	@Override
	public String getSetterName() {
		return String.format("set%s", StringUtils.capitalize(javaField.getName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getSingularName()
	 */
	@Override
	public String getSingularName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getType()
	 */
	@Override
	public String getType() {
		return this.javaField.getType().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isId()
	 */
	@Override
	public boolean isId() {
		return this.getAnnotations().stream().anyMatch(ann -> ann.getName().equals("Id"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isList()
	 */
	@Override
	public boolean isTypeList() {
		return this.javaField.getType().getGenericValue().startsWith("List");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isStatic()
	 */
	@Override
	public boolean isStatic() {
		return this.javaField.isStatic();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isUnique()
	 */
	@Override
	public boolean isColumnUnique() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Column"))
				.filter(ann -> ann.getParameters().containsKey("unique"))
				.findFirst()
				.map(ann -> ann.getParameters().get("unique").equals("true"))
				.orElse(false);
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isNullable()
	 */
	@Override
	public boolean isColumnNullable() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Column"))
				.filter(ann -> ann.getParameters().containsKey("nullable"))
				.findFirst()
				.map(ann -> ann.getParameters().get("nullable").equals("true"))
				.orElse(true);
		// @formatter:on
	}

	@Override
	public boolean isTypeNumber() {
		return Arrays.asList("Long", "long", "Integer", "int", "BigDecimal", "BigInteger").contains(this.javaField.getType().getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isDate()
	 */
	@Override
	public boolean isTypeDate() {
		return this.javaField.getType().getValue().equals("Date");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isString()
	 */
	@Override
	public boolean isTypeString() {
		return this.javaField.getType().getValue().equals("String");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isBlob()
	 */
	@Override
	public boolean isTypeBlob() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Lob"))
				.findFirst()
				.isPresent();
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getLabel()
	 */
	@Override
	public String getLabel() {
		DocletTag label = javaField.getTagByName("label");
		return label != null ? label.getParameters().get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getLength()
	 */
	@Override
	public Integer getColumnLength() {
		//// @formatter:off
		if(this.javaField.getType().getName().equals("String")) {
			return this.getAnnotations().stream()
					.filter(ann -> ann.getName().equals("Column"))
					.filter(ann -> ann.getParameters().containsKey("length"))
					.findFirst()
					.map(ann -> ann.getParameters().get("length"))
					.map(Integer::valueOf)
					.orElse(255);
		}
		return null;
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getColumnName()
	 */
	@Override
	public String getColumnName() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Column"))
				.filter(ann -> ann.getParameters().containsKey("name"))
				.findFirst()
				.map(ann -> ann.getParameters().get("name"))
				.orElse(null);
		// @formatter:on

	}

	@Override
	public String getGeneratedValueStrategy() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("GeneratedValue"))
				.filter(ann -> ann.getParameters().containsKey("strategy"))
				.findFirst()
				.map(ann -> ann.getParameters().get("strategy"))
				.orElse(null);
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getMultiplicityType()
	 */
	@Override
	public String getMultiplicity() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> Arrays.asList("OneToMany", "OneToOne", "ManyToOne", "ManyToMany").contains(ann.getName()))
				.findFirst()
				.map(ann -> ann.getName())
				.orElse(null);
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isOrphanRemoval()
	 */
	@Override
	public boolean isOrphanRemoval() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> Arrays.asList("OneToOne", "OneToMany").contains(ann.getName()))
				.filter(ann -> ann.getParameters().containsKey("orphanRemoval"))
				.findFirst()
				.map(ann -> ann.getParameters().get("orphanRemoval").equals("true"))
				.orElse(false);
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#hasMappedBy()
	 */
	@Override
	public String getMappedBy() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> Arrays.asList("ManyToMany", "OneToOne", "OneToMany").contains(ann.getName()))
				.filter(ann -> ann.getParameters().containsKey("mappedBy"))
				.findFirst()
				.map(ann -> ann.getParameters().get("mappedBy"))
				.orElse(null);
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
			this.annotations = javaField.getAnnotations()
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
	 * @see br.sk.model.jpa.EntityAttribute#getGenericTypes()
	 */

	@Override
	public Entity getGenericType() {
		return null;
	}

}
