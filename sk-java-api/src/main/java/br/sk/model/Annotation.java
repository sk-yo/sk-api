package br.sk.model;

import java.util.Map;

import com.thoughtworks.qdox.model.JavaAnnotation;

public interface Annotation {

	public String getName();

	public Map<String, String> getParameters();

	/**
	 * 
	 * @return
	 */
	JavaAnnotation getJavaAnnotation();

}
