package br.sk.api;

import java.nio.file.Paths;

import br.sk.model.EMavenProject;
import br.sk.proxy.EMavenProjectProxy;
import br.sk.utils.JSON;

public class Project {

	public String findAllClasses(String projectDir) {
		EMavenProject mavenProject = new EMavenProjectProxy(Paths.get(projectDir));
		return JSON.transform(mavenProject.getClasses());
	}
}
