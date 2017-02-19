package br.sk.proxy;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.thoughtworks.qdox.model.JavaParameter;

import br.sk.model.EAnnotation;
import br.sk.model.EMethodParameter;
import lombok.ToString;

@ToString(callSuper = true)
public class EMethodParameterProxy extends EMethodParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JavaParameter javaParameter;

	public EMethodParameterProxy(JavaParameter javaParameter) {
		super();
		this.javaParameter = javaParameter;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = javaParameter.getName();
		}
		return this.name;
	}

	@Override
	public String getType() {
		if (this.type == null) {
			this.type = javaParameter.getType().getValue();
		}
		return this.type;
	}

	@Override
	public Set<EAnnotation> getAnnotations() {
		if (this.annotations == null) {
			//// @formatter:off
			this.annotations = Arrays.asList(javaParameter.getAnnotations())
					.stream()
					.map(EAnnotationProxy::new)
					.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.annotations;
	}

}
