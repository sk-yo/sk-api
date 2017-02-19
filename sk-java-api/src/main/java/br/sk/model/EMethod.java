package br.sk.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = { "name", "parameters" })
@ToString(of = { "name", "parameters" })
@JsonTypeName("EMethod")
public class EMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected String name;

	/**
	 * 
	 */
	protected String body;

	/**
	 * 
	 */
	protected Map<Integer, EMethodParameter> parameters;

	/**
	 * 
	 */
	protected Set<EAnnotation> annotations;

	public EMethod() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<Integer, EMethodParameter> getParameters() {
		return parameters;
	}

	public void setParameters(Map<Integer, EMethodParameter> parameters) {
		this.parameters = parameters;
	}

	public Set<EAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<EAnnotation> annotations) {
		this.annotations = annotations;
	}

}
