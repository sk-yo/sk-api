package br.sk.proxy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.qdox.model.JavaField;

import br.sk.model.EAnnotation;
import br.sk.model.EAttribute;

/**
 * Classe de proxy de EAttribute
 * 
 * @author jcruz
 *
 */
public class EAttributeProxy extends EAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JavaField javaField;

	public EAttributeProxy(JavaField javaField) {
		super();
		this.javaField = javaField;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = this.javaField.getName();
		}
		return this.name;
	}

	@Override
	public String getGetterName() {
		if (this.getterName == null) {
			this.getterName = String.format("get%s", StringUtils.capitalize(this.getName()));
		}
		return this.getterName;
	}

	@Override
	public String getSetterName() {
		if (this.setterName == null) {
			this.setterName = String.format("set%s", StringUtils.capitalize(this.getName()));
		}
		return this.setterName;
	}

	@Override
	public String getType() {
		if (this.type == null) {
			this.type = this.javaField.getType().getFullyQualifiedName();
		}
		return this.type;
	}

	@Override
	public String getShortType() {
		if (this.shortType == null) {
			String[] shortType = this.javaField.getType().getFullyQualifiedName().split("\\.");
			this.shortType = shortType[shortType.length - 1];
		}
		return this.shortType;
	}

	@Override
	public Set<String> getModifiers() {
		if (this.modifiers == null) {
			this.modifiers = new HashSet<>(Arrays.asList(this.javaField.getModifiers()));
		}
		return this.modifiers;
	}

	@Override
	public Map<Integer, String> getGenericTypes() {
		if (this.genericTypes == null) {
			this.genericTypes = new HashMap<>();
			if (this.javaField.getType().getActualTypeArguments() != null && this.javaField.getType().getActualTypeArguments().length > 0) {
				for (int i = 0; i < this.javaField.getType().getActualTypeArguments().length; i++) {
					this.genericTypes.put(i, this.javaField.getType().getActualTypeArguments()[i].getValue());
				}
			}
		}
		return this.genericTypes;
	}

	@Override
	public Set<EAnnotation> getAnnotations() {
		if (this.annotations == null) {
			//// @formatter:off
			this.annotations = Arrays.asList(javaField.getAnnotations())
					.stream()
					.map(EAnnotationProxy::new)
					.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.annotations;
	}

	@Override
	public boolean isCollectionAttribute() {
		return Arrays.asList("List", "Collection", "Set").contains(this.getShortType());
	}

	@Override
	public boolean isIdAttribute() {
		return this.getAnnotations().stream().anyMatch(ann -> ann.getName().equals("javax.persistence.Id"));
	}

}
