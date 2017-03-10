package br.sk.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

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

	private Map<String, MultiplicityType> multiplicityTypes = createMultiplicityType();

	public EntityAttributeImpl(JavaField javaField) {
		super();
		this.javaField = javaField;
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
	 * @see br.sk.model.jpa.EntityAttribute#isId()
	 */
	@Override
	public boolean isId() {
		return this.getAnnotations().stream().anyMatch(ann -> ann.getName().equals("Id"));
	}

	@Override
	public boolean isList() {
		return this.javaField.getType().getGenericValue().startsWith("List");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.jpa.EntityAttribute#getLabel()
	 */
	@Override
	public String getLabel() {
		DocletTag label = javaField.getTagByName("label");
		return label != null ? label.getParameters().get(0) : "";
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
				.filter(ann -> ann.getName().equals("javax.persistence.Column"))
				.findFirst()
				.map(ann -> ann.getName())
				.orElse("");
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
				.orElse(MultiplicityType.NONE);
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
	 * @see br.sk.model.jpa.EntityAttribute#getAnnotations()
	 */
	@Override
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
		/*
		 * if (this.genericTypes == null) { this.genericTypes = new HashMap<>();
		 * if (this.javaField.getType().getActualTypeArguments() != null &&
		 * this.javaField.getType().getActualTypeArguments().length > 0) { for
		 * (int i = 0; i <
		 * this.javaField.getType().getActualTypeArguments().length; i++) {
		 * this.genericTypes.put(i, new
		 * EntityImpl(this.javaField.getType().getActualTypeArguments()[i].
		 * getJavaClass())); } } }
		 */
		// System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		// System.out.println(this.javaField.getType().getGenericCanonicalName());
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
