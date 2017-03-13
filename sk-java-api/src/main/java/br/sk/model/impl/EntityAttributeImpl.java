package br.sk.model.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;

import br.sk.context.EntityContext;
import br.sk.model.Annotation;
import br.sk.model.Entity;
import br.sk.model.EntityAttribute;
import br.sk.utils.Inflector;

public class EntityAttributeImpl implements EntityAttribute {

	private JavaField javaField;

	private Set<Annotation> annotations;

	private EntityContext context;

	private Entity entity;
	
	private boolean backReference;

	public EntityAttributeImpl(EntityContext builder, Entity entity, JavaField javaField, boolean backReference) {
		super();
		this.context = builder;
		this.entity = entity;
		this.javaField = javaField;
		this.backReference = backReference;
	}

	@Override
	public EntityContext getContext() {
		return this.context;
	}

	@Override
	public Entity getEntity() {
		return this.entity;
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
	public String getSingulariedName() {
		return Inflector.getForLocale("pt_BR").singularize(this.javaField.getName());
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

	@Override
	public boolean hasMultiplicity() {
		return Arrays.asList("OneToMany", "OneToOne", "ManyToOne", "ManyToMany").contains(this.getMultiplicity());
	}

	@Override
	public LinkedList<String> getCascadeType() {
		//// @formatter:off
		return this.javaField.getAnnotations().stream()
				.filter(ann -> Arrays.asList("OneToMany", "OneToOne", "ManyToOne", "ManyToMany").contains(ann.getType().getName()))
				.filter(ann -> ann.getNamedParameter("cascade") != null)
				.findFirst()
				.map(ann -> this.resolveCascadeType(ann.getNamedParameter("cascade")))
				.orElse(null);
		// @formatter:on

	}

	@SuppressWarnings("unchecked")
	private LinkedList<String> resolveCascadeType(Object cascade) {
		if (cascade.getClass().getSimpleName().equals("String")) {
			return new LinkedList<>(Arrays.asList((String) cascade));
		}
		return (LinkedList<String>) cascade;
	}

	@Override
	public boolean hasCascade() {
		return this.getCascadeType() != null;
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
	 * @see br.sk.model.EntityAttribute#hasMappedBy()
	 */
	@Override
	public boolean hasMappedBy() {
		//// @formatter:off
		return this.getAnnotations().stream()
				.filter(ann -> Arrays.asList("ManyToMany", "OneToOne", "OneToMany").contains(ann.getName()))
				.filter(ann -> ann.getParameters().containsKey("mappedBy"))
				.findFirst()
				.map(ann -> StringUtils.isNotBlank(ann.getParameters().get("mappedBy")))
				.orElse(false);
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
									.map(AnnotationImpl::new)
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
	public String getGenericType() {
		if (this.hasMultiplicity() && this.isTypeList()) {
			return EntityAttributeImpl.getGenericType(this.javaField);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.EntityAttribute#getNavegability()
	 */
	@Override
	public String getNavegability() {
		if (this.hasMultiplicity()) {
			switch (this.getMultiplicity()) {
			case "OneToMany":
				return this.resolveNavegabilityForOneToMany();
			case "OneToOne":
				return this.resolveNavegabilityForOneToOne();
			case "ManyToMany":
				return this.resolveNavegabilityForManyToMany();
			case "ManyToOne":
				return this.resolveNavegabilityForManyToOne();
			default:
				break;
			}
		}
		return null;
	}

	@Override
	public Entity getRelationship() {
		if(!backReference) {
			if (this.hasMultiplicity()) {
				if (this.isTypeList()) {
					return this.context.findEntityByName(this.getGenericType(),true)
							.map(entity -> entity)
							.orElse(null);
				}
				return this.context.findEntityByName(this.getType(), true)
						.map(entity -> entity)
						.orElse(null);
			}
		}
		return null;
	}

	private String resolveNavegabilityForOneToMany() {
		if (this.getMultiplicity().equals("OneToMany")) {
			return this.hasMappedBy() ? "bidirectional" : "unidirectional";
		}
		return null;
	}

	private String resolveNavegabilityForOneToOne() {
		if (this.getMultiplicity().equals("OneToOne")) {
			Optional<Entity> entity = this.context.findEntityByName(this.getType());
			if (entity.isPresent()) {
				//// @formatter:off
				return entity.get().getAttributes().stream()
					.filter(attr -> attr.getType().equals(this.entity.getName()))
					.findFirst()
					.map(attr -> "bidirectional")
					.orElse("unidirectional");
				// @formatter:on
			}
			return this.hasMappedBy() ? "bidirectional" : "unidirectional";
		}
		return null;
	}

	private String resolveNavegabilityForManyToMany() {
		if (this.getMultiplicity().equals("ManyToMany")) {
			Optional<Entity> entity = this.context.findEntityByName(this.getGenericType());
			if (entity.isPresent()) {
				//// @formatter:off
				return entity.get().getAttributes().stream()
					.filter(attr -> attr.getType().equals("List"))
					.filter(attr -> attr.getGenericType().equals(this.entity.getName()))
					.findFirst()
					.map(attr -> "bidirectional")
					.orElse("unidirectional");
				// @formatter:on
			}
			return this.hasMappedBy() ? "bidirectional" : "unidirectional";
		}
		return null;
	}

	private String resolveNavegabilityForManyToOne() {
		if (this.getMultiplicity().equals("ManyToOne")) {
			Optional<Entity> entity = this.context.findEntityByName(this.getType());
			if (entity.isPresent()) {
				// entity.get().getAttributes().stream().forEach(attr ->
				// System.out.println(attr.getGenericType()));
				//// @formatter:off
				return entity.get().getAttributes().stream()
					.filter(attr -> attr.getType().equals("List"))
					.filter(attr -> attr.getGenericType().equals(this.entity.getName()))
					.findFirst()
					.map(attr -> "bidirectional")
					.orElse("unidirectional");
				// @formatter:on
			}
			return this.hasMappedBy() ? "bidirectional" : "unidirectional";
		}
		return null;
	}

	protected static String getGenericType(JavaField javaField) {
		if (javaField.getType().getName().equals("List")) {
			Pattern pattern = Pattern.compile("List<([a-zA-Z]+)>");
			Matcher m = pattern.matcher(javaField.getType().getGenericValue());
			if (m.find()) {
				return m.group(1);
			}
		}
		return null;
	}

}
