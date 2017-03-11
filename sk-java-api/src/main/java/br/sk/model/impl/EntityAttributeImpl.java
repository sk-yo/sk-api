package br.sk.model.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;

import br.sk.model.core.EAnnotation;
import br.sk.model.jpa.Entity;
import br.sk.model.jpa.EntityAttribute;
import br.sk.model.jpa.enums.MultiplicityType;
import br.sk.model.jpa.enums.RelationshipType;

public class EntityAttributeImpl implements EntityAttribute {

	private JavaField javaField;

	private Set<EAnnotation> annotations;

	@JsonIgnore
	private Map<String, MultiplicityType> multiplicityTypes = createMultiplicityType();

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
	public boolean isList() {
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
	public boolean isUnique() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Column"))
				.filter(ann -> ann.getParameters().containsKey("unique"))
				.findFirst()
				.map(ann -> ann.getParameters().get("unique").equals("true"))
				.orElse(false);
		// @formatter:on
	}

	@Override
	public boolean isNullable() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> ann.getName().equals("Column"))
				.filter(ann -> ann.getParameters().containsKey("nullable"))
				.findFirst()
				.map(ann -> ann.getParameters().get("nullable").equals("true"))
				.orElse(true);
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
	public Integer getLength() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getMultiplicityType()
	 */
	@Override
	public MultiplicityType getMultiplicityType() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> multiplicityTypes.containsKey(ann.getName()))
				.findFirst()
				.map(ann -> multiplicityTypes.get(ann.getName()))
				.orElse(null);
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getRelationshipType()
	 */
	@Override
	public RelationshipType getRelationshipType() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#isUnidirecional()
	 */
	@Override
	public boolean isUnidirecional() {
		return false;
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
	@JsonIgnore
	public Set<EAnnotation> getAnnotations() {
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

	private Map<String, MultiplicityType> createMultiplicityType() {
		Map<String, MultiplicityType> multiplicityTypes = new HashMap<>();
		multiplicityTypes.put("OneToMany", MultiplicityType.ONE_TO_MANY);
		multiplicityTypes.put("OneToOne", MultiplicityType.ONE_TO_ONE);
		multiplicityTypes.put("ManyToMany", MultiplicityType.MANY_TO_MANY);
		multiplicityTypes.put("ManyToOne", MultiplicityType.MANY_TO_ONE);
		return multiplicityTypes;
	}

}
