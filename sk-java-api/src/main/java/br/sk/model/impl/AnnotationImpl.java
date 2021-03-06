package br.sk.model.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.qdox.model.JavaAnnotation;

import br.sk.model.Annotation;

public class AnnotationImpl implements Annotation {

	private JavaAnnotation annotation;

	protected Map<String, String> parameters;

	public AnnotationImpl(JavaAnnotation annotation) {
		super();
		this.annotation = annotation;
	}

	@Override
	public String getName() {
		return annotation.getType().getName();
	}

	@Override
	public Map<String, String> getParameters() {
		if (this.parameters == null) {
			this.parameters = new HashMap<>();
			if (this.annotation.getNamedParameterMap() != null) {
				Iterator<String> keys = annotation.getNamedParameterMap().keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					this.parameters.put(key, String.valueOf(this.annotation.getNamedParameter(key)).replaceAll("\"", "").trim());
				}
			}
		}
		return this.parameters;
	}

	@Override
	public JavaAnnotation getJavaAnnotation() {
		return this.annotation;
	}

}
