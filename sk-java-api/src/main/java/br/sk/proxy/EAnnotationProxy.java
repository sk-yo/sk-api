package br.sk.proxy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.qdox.model.Annotation;

import br.sk.model.EAnnotation;
import lombok.ToString;

@ToString(callSuper = true)
public class EAnnotationProxy extends EAnnotation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Annotation annotation;

	public EAnnotationProxy(Annotation annotation) {
		super();
		this.annotation = annotation;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = annotation.getType().getValue();
		}
		return this.name;
	}

	@Override
	@SuppressWarnings("unchecked")
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
