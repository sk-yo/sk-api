package br.sk.model.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;

import br.sk.model.core.EAnnotation;
import br.sk.model.jpa.Entity;
import br.sk.model.jpa.EntityAttribute;
import br.sk.model.jpa.enums.MultiplicityType;
import br.sk.model.jpa.enums.RelationshipType;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class EntityAttributeImpl implements EntityAttribute {

	private JavaField javaField;

	private Set<EAnnotation> annotations;

	private Map<String, MultiplicityType> multiplicityTypes = createMultiplicityType();

	private Map<Integer, Entity> genericTypes;

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
		return this.getAnnotations().stream().anyMatch(ann -> ann.getName().equals("javax.persistence.Id"));
	}

	@Override
	public String getLabel() {
		DocletTag label = javaField.getTagByName("label");
		return label != null ? label.getParameters()[0] : "";
	}

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

	@Override
	public MultiplicityType getMultiplicityType() {
		return this.getAnnotations().stream().filter(ann -> multiplicityTypes.containsKey(ann.getName())).findFirst()
				.map(ann -> multiplicityTypes.get(ann.getName())).orElse(MultiplicityType.NONE);
	}

	@Override
	public RelationshipType getRelationshipType() {
		return null;
	}

	@Override
	public boolean isUnidirecional() {
		return false;
	}

	@Override
	public Set<EAnnotation> getAnnotations() {
		if (this.annotations == null) {
			//// @formatter:off
			this.annotations = Arrays.asList(javaField.getAnnotations())
									.stream()
									.map(EAnnotationImpl::new)
									.collect(Collectors.toSet());
			// @formatter:on

		}
		return this.annotations;
	}

	@Override
	public Map<Integer, Entity> getGenericTypes() {
		if (this.genericTypes == null) {
			this.genericTypes = new HashMap<>();
			if (this.javaField.getType().getActualTypeArguments() != null && this.javaField.getType().getActualTypeArguments().length > 0) {
				for (int i = 0; i < this.javaField.getType().getActualTypeArguments().length; i++) {
					this.genericTypes.put(i, new EntityImpl(this.javaField.getType().getActualTypeArguments()[i].getJavaClass()));
				}
			}
		}
		return this.genericTypes;
	}

	private Map<String, MultiplicityType> createMultiplicityType() {
		Map<String, MultiplicityType> multiplicityTypes = new HashMap<>();
		multiplicityTypes.put("javax.persistence.OneToMany", MultiplicityType.ONE_TO_MANY);
		multiplicityTypes.put("javax.persistence.OneToOne", MultiplicityType.ONE_TO_ONE);
		multiplicityTypes.put("javax.persistence.ManyToMany", MultiplicityType.MANY_TO_MANY);
		multiplicityTypes.put("javax.persistence.ManyToOne", MultiplicityType.MANY_TO_ONE);
		return multiplicityTypes;
	}

}
