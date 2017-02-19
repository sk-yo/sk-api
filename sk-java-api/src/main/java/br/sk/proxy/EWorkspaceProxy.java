package br.sk.proxy;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.sk.model.EMavenProject;
import br.sk.model.EWorkspace;
import br.sk.utils.FS;

public class EWorkspaceProxy extends EWorkspace {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FS fs = new FS();

	public EWorkspaceProxy(String path) {
		super();
		this.path = path;
	}

	@Override
	public SortedSet<EMavenProject> getMavenProjects() {
		if (this.mavenProjects == null) {
			Optional<Stream<Path>> directories = fs.listDirectories(this.path);
			if (directories.isPresent()) {
				//// @formatter:off
				this.mavenProjects = directories.get()
						.map(path -> new EMavenProjectProxy(path))
						.filter(path -> !Arrays.asList(".metadata", ".recommenders", "RemoteSystemsTempFiles").contains(path.getName()))
						.collect(Collectors.toCollection(TreeSet::new));
				// @formatter:on
			}
		}
		return this.mavenProjects;
	}

}
