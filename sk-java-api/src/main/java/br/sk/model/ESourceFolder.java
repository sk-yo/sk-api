package br.sk.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ESourceFolder {
	/**
	 * 
	 */
	SRC_MAIN_JAVA("/src/main/java/"),
	/**
	 * 
	 */
	SRC_MAIN_RESOURCES("/src/main/resources/"),
	/**
	 * 
	 */
	SRC_TEST_JAVA("/src/test/java/"),

	/**
	 * 
	 */
	SRC_TEST_RESOURCES("/src/test/resources/"),

	/**
	 * 
	 */
	SRC_MAIN_WEBAPP("/src/main/webapp/"),

	/**
	 * 
	 */
	SRC_SITE("/src/site/"),

	/**
	 * 
	 */
	TARGET("/target/");

	private String path;

	private ESourceFolder(String path) {
		this.path = path;
	}

	@JsonValue
	public String getPath() {
		return path;
	}
}