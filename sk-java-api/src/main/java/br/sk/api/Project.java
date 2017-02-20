package br.sk.api;

import java.nio.file.Paths;
import java.util.stream.Collectors;

import br.sk.model.EMavenProject;
import br.sk.proxy.EMavenProjectProxy;
import br.sk.utils.JSON;

public class Project {

	public String findClassesByAnnotationName(String projectDir, String annotationName) {
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
}
