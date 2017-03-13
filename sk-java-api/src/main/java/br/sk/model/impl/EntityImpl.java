package br.sk.model.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoughtworks.qdox.model.JavaClass;

import br.sk.context.EntityContext;
import br.sk.model.Annotation;
import br.sk.model.Entity;
import br.sk.model.EntityAttribute;
import br.sk.utils.Inflector;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityImpl implements Entity {

	private JavaClass javaClass;

	private Set<EntityAttribute> attributes;

	@JsonIgnore
	private EntityContext builder;

	private Set<Annotation> annotations;

	private boolean backReference;

	public EntityImpl(EntityContext context, JavaClass javaClass, boolean backReference) {
		super();
		this.builder = context;
		this.javaClass = javaClass;
		this.backReference = backReference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.Entity#getContext()
	 */
	@Override
	public EntityContext getContext() {
		return this.builder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.Entity#getPackageName()
	 */
	@Override
	public String getPackageName() {
		return this.javaClass.getPackageName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.Entity#getPackageDir()
	 */
	@Override
	public String getPackageDir() {
		return FilenameUtils.normalize(StringUtils.replaceAll(this.javaClass.getPackageName(), "\\.", "/"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.Entity#getParentPackageName()
	 */
	@Override
	public String getParentPackageName() {
		String[] packageNamesArray = this.javaClass.getPackage().getName().split("\\.");
		if (packageNamesArray.length > 0) {
			return StringUtils.join(ArrayUtils.remove(packageNamesArray, packageNamesArray.length - 1), ".");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.Entity#getParentPackageDir()
	 */
	@Override
	public String getParentPackageDir() {
		if (this.getPackageName() != null) {
			return FilenameUtils.normalize(StringUtils.replaceAll(this.getParentPackageName(), "\\.", "/"));
		}
		return null;
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
		return Inflector.getForLocale("pt_BR").pluralize(StringUtils.uncapitalize(javaClass.getName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.sk.model.Entity#getPluralizedName()
	 */
	@Override
	public String getPluralizedName() {
		return Inflector.getForLocale("pt_BR").pluralize(javaClass.getName());
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
									.map(AnnotationImpl::new)
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
								.map(javaField -> new EntityAttributeImpl(builder, this, javaField, backReference))
								.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.attributes;
	}

	@Override
	public EntityAttribute getIdAttribute() {
		//// @formatter:off
		return this.getAttributes().stream()
				.filter(attr -> attr.isId())
				.findFirst()
				.map(attr -> attr)
				.orElse(null);
		// @formatter:on

	}

}
