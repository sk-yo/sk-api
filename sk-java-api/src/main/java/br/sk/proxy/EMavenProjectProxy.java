package br.sk.proxy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import br.sk.model.EClass;
import br.sk.model.EDirectory;
import br.sk.model.EMavenProject;
import br.sk.model.EPom;
import br.sk.utils.FS;

public class EMavenProjectProxy extends EMavenProject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Path path;

	private FS fs = new FS();

	public EMavenProjectProxy(Path path) {
		super();
		this.path = path;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = path.toFile().getName();
		}
		return this.name;
	}

	@Override
	public String getAbsolutePath() {
		if (this.absolutePath == null) {
			this.absolutePath = path.toFile().getAbsolutePath();
		}
		return this.absolutePath;
	}

	@Override
	public SortedSet<EClass> getClasses() {
		if (this.classes == null) {
			this.classes = new TreeSet<>();
			Optional<Stream<Path>> files = fs.listFilesRecursively(this.path);
			if (files.isPresent()) {
				//// @formatter:off
				this.classes = files.get()
									.filter(_path -> _path.toFile().getName().endsWith("java"))
									.map(EClassProxy::createJavaClass)
									.filter(javaClass -> javaClass.isPresent())
									.map(javaClass -> javaClass.get())
									.map(EClassProxy::new)
									.collect(Collectors.toCollection(TreeSet::new));
				// @formatter:on

			}

		}
		return this.classes;
	}

	@Override
	public EPom getPom() {
		if (this.pom == null) {
			try {
				this.pom = new EPomProxy(new File(FilenameUtils.normalize(String.format("%s/pom.xml", this.getAbsolutePath()))));
			} catch (SAXException | IOException | ParserConfigurationException e) {
				System.out.println(e.getMessage());
			}
		}
		return this.pom;
	}

	@Override
	public Set<EDirectory> getDirectories() {
		if (this.directories == null) {
			this.directories = new HashSet<>();
			Optional<Stream<Path>> directories = fs.listDirectoriesRecursively(this.getAbsolutePath());
			if (directories.isPresent()) {
				this.directories = directories.get().filter(_path -> !_path.toFile().getAbsolutePath().contains(".svn"))
						.filter(_path -> !_path.toFile().getAbsolutePath().contains("target"))
						.map(_path -> new EDirectory(_path.toFile().getAbsolutePath().replaceAll(path.toFile().getAbsolutePath(), "")))
						.collect(Collectors.toSet());
			}
		}
		return this.directories;
	}

}
