package br.sk.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = { "name", "type" })
@ToString(of = { "name", "type" })
public class EMethodParameter implements Serializable {

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
	protected String type;

	/**
	 * 
	 */
	protected Set<EAnnotation> annotations;

	public EMethodParameter() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<EAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<EAnnotation> annotations) {
		this.annotations = annotations;
	}

}
