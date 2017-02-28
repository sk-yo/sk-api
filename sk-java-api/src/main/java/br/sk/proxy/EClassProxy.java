package br.sk.proxy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.parser.ParseException;

import br.sk.model.EAnnotation;
import br.sk.model.EAttribute;
import br.sk.model.EClass;
import br.sk.model.EMethod;
import br.sk.model.EPackage;
import br.sk.model.ESourceFolder;

/**
 * Classe de proxy de EClass
 * 
 * @author jcruz
 *
 */
public class EClassProxy extends EClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JavaClass javaClass;

	public EClassProxy(JavaClass javaClass) {
		super();
		this.javaClass = javaClass;
	}

	@Override
	public ESourceFolder getSourceFolder() {
		if (this.sourceFolder == null) {
			this.sourceFolder = Arrays.asList(ESourceFolder.values()).stream()
					.filter(eSourceFolder -> javaClass.getSource().getURL().toString().contains(eSourceFolder.getPath())).findFirst().get();
		}
		return this.sourceFolder;
	}

	@Override
	public String getPath() {
		if (this.path == null) {
			this.path = javaClass.getSource().getURL().getPath();
		}
		return this.path;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = this.javaClass.getName();
		}
		return this.name;
	}

	@Override
	public String getInstanceName() {
		if(this.instanceName == null) {
			this.instanceName = StringUtils.uncapitalize(getName());
		}
		return this.instanceName;
	}

	@Override
	public String getFullyQualifiedName() {
		if (this.fullyQualifiedName == null) {
			this.fullyQualifiedName = this.javaClass.getFullyQualifiedName();
		}
		return this.fullyQualifiedName;
	}

	@Override
	public EPackage getClassPackage() {
		if (this.classPackage == null) {
			this.classPackage = new EPackageProxy(javaClass.getPackage());
		}
		return this.classPackage;
	}

	@Override
	public EAttribute getIdAttribute() {
		if (this.idAttribute == null) {
			Optional<EAttribute> idAttribute = getAttributes().stream()
					.filter(attr -> attr.getAnnotations().stream().anyMatch((ann -> ann.getName().equals("javax.persistence.Id"))))
					.findAny();
			this.idAttribute = idAttribute.isPresent() ? idAttribute.get() : new EAttribute();
		}
		return this.idAttribute;
	}

	@Override
	public Set<EAttribute> getAttributes() {
		if (this.attributes == null) {
			// @formatter:off
			this.attributes = Arrays.asList(javaClass.getFields())
				.stream()	
				.map(EAttributeProxy::new)
				.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.attributes;

	}

	@Override
	public Set<EMethod> getMethods() {
		if (this.methods == null) {
			// @formatter:off
			this.methods = Arrays.asList(javaClass.getMethods())
					.stream()
					.map(EMethodProxy::new)
					.collect(Collectors.toSet());
			// @formatter:on

		}
		return this.methods;
	}

	@Override
	public Set<EAnnotation> getAnnotations() {
		if (this.annotations == null) {
			//// @formatter:off
			this.annotations = Arrays.asList(javaClass.getAnnotations())
					.stream()
					.map(EAnnotationProxy::new)
					.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.annotations;
	}

	public static Optional<JavaClass> createJavaClass(Path path) {
		try {
			JavaDocBuilder doc = new JavaDocBuilder();
			JavaSource source = doc.addSource(new File(path.toFile().getAbsolutePath()));
			return Optional.of(source.getClasses()[0]);
		} catch (ParseException | IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	public static Optional<JavaClass> createJavaClass(String path) {
		try {
			JavaDocBuilder doc = new JavaDocBuilder();
			JavaSource source = doc.addSource(new File(path));
			return Optional.of(source.getClasses()[0]);
		} catch (ParseException | IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

}
