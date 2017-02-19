package br.sk.proxy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.thoughtworks.qdox.model.JavaMethod;

import br.sk.model.EAnnotation;
import br.sk.model.EMethod;
import br.sk.model.EMethodParameter;
import lombok.ToString;

@ToString(callSuper = true)
public class EMethodProxy extends EMethod {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JavaMethod javaMethod;

	public EMethodProxy(JavaMethod javaMethod) {
		super();
		this.javaMethod = javaMethod;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = javaMethod.getName();
		}
		return this.name;
	}

	@Override
	public String getBody() {
		if (this.body == null) {
			this.body = javaMethod.getCodeBlock();
		}
		return this.body;
	}

	@Override
	public Map<Integer, EMethodParameter> getParameters() {
		if (this.parameters == null) {
			this.parameters = new HashMap<>();
			if (this.javaMethod.getParameters() != null && this.javaMethod.getParameters().length > 0) {
				for (int i = 0; i < this.javaMethod.getParameters().length; i++) {
					this.parameters.put(i, new EMethodParameterProxy(this.javaMethod.getParameters()[i]));
				}
			}
		}
		return this.parameters;
	}

	@Override
	public Set<EAnnotation> getAnnotations() {
		if (this.annotations == null) {
			//// @formatter:off
			this.annotations = Arrays.asList(javaMethod.getAnnotations())
					.stream()
					.map(EAnnotationProxy::new)
					.collect(Collectors.toSet());
			// @formatter:on
		}
		return this.annotations;
	}

}
