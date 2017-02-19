package br.sk.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EPersistence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ESourceFolder sourceFolder;

	protected String persistenceUnitName;

	protected String persistenceUnitTransactionType;

	protected Set<String> classes;

	protected Map<String, String> properties;

	public ESourceFolder getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(ESourceFolder sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}

	public String getPersistenceUnitTransactionType() {
		return persistenceUnitTransactionType;
	}

	public void setPersistenceUnitTransactionType(String persistenceUnitTransactionType) {
		this.persistenceUnitTransactionType = persistenceUnitTransactionType;
	}

	public Set<String> getClasses() {
		return classes;
	}

	public void setClasses(Set<String> classes) {
		this.classes = classes;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
