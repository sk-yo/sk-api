package br.sk.model.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.qdox.model.Annotation;

import br.sk.model.core.EAnnotation;

public class EAnnotationImpl implements EAnnotation {

	private Annotation annotation;

	protected Map<String, String> parameters;

	public EAnnotationImpl(Annotation annotation) {
		super();
		this.annotation = annotation;
	}

	@Override
	public String getName() {
		return annotation.getType().getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getParameters() {
		if (this.parameters == null) {
			this.parameters = new HashMap<>();
			if (this.annotation.getNamedParameterMap() != null) {
				Iterator<String> keys = annotation.getNamedParameterMap().keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					this.parameters.put(key, String.valueOf(this.annotation.getNamedParameter(key)));
				}
			}
		}
		return this.parameters;
	}

}
