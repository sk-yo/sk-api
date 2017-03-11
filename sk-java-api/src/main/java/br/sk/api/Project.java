package br.sk.api;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

import br.sk.utils.JSON;

public class Project {
	
	/*
	public static String findClassesByAnnotationName(String projectDir, String annotationName) {
		EMavenProject mavenProject = new EMavenProjectProxy(Paths.get(projectDir));
		//// @formatter:off
		return JSON.transform(
				mavenProject.getClasses()
					.stream()
					.filter(c -> c.getAnnotations()
									.stream()
									.anyMatch(a -> a.getName().equals(annotationName)))
					.collect(Collectors.toList()));
		// @formatter:on
	}

	public static String findClassesNamesByAnnotationName(String projectDir, String annotationName) {
		EMavenProject mavenProject = new EMavenProjectProxy(Paths.get(projectDir));
		//// @formatter:off
		return JSON.transform(
				mavenProject.getClasses()
					.stream()
					.filter(c -> c.getAnnotations()
									.stream()
									.anyMatch(a -> a.getName().equals(annotationName)))
					.map(c -> c.getName())
					.collect(Collectors.toList()));
		// @formatter:on
	}

	public static String findClassByName(String projectDir, String className) {
		EMavenProject mavenProject = new EMavenProjectProxy(Paths.get(projectDir));
		//// @formatter:off
		Optional<EClass> eClass= 
				mavenProject.getClasses()
					.stream()
					.filter(c -> c.getName().equals(className))
					.findFirst();
		return eClass.isPresent() ? JSON.transform(eClass.get()) : null;
		// @formatter:on
	}*/
}
