package br.sk.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EPom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected EPomParent parent;

	@JsonIgnore
	protected Set<EPomDependency> dependecies;

	public EPom() {
		super();
	}

	public EPomParent getParent() {
		return parent;
	}

	public void setParent(EPomParent parent) {
		this.parent = parent;
	}

	public Set<EPomDependency> getDependecies() {
		return dependecies;
	}

	public void setDependecies(Set<EPomDependency> dependecies) {
		this.dependecies = dependecies;
	}

}
