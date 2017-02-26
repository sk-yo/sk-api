package br.sk.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = "name")
public class EPackage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String name;

	protected String directory;

	protected String classParentPackageName;

	protected String classParentPackageDirectory;

	public EPackage() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectory() {
		return directory;
	}

	public String getClassParentPackageName() {
		return classParentPackageName;
	}

	public String getClassParentPackageDirectory() {
		return classParentPackageDirectory;
	}

}
