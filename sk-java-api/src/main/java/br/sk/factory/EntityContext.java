package br.sk.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import br.sk.model.Entity;
import br.sk.model.impl.EntityImpl;

public class EntityContext {

	private Map<String, File> context;

	private EntityContext() {
		super();
	}

	public Entity findByName(String name) throws IOException {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		JavaSource javaSource = builder.addSource(context.get(name));
		return new EntityImpl(this, javaSource.getClasses().get(0));
	}

	public static EntityContext of(String path) throws IOException {
		EntityContext entityContext = new EntityContext();
		//// @formatter:off
		Files.walk(Paths.get(path))
			.filter(p -> p.getFileName().toFile().getName().endsWith(".java"))
			.forEach(p -> {
				String name = p.getFileName().toFile().getName().replaceAll(".java", "");
				entityContext.getContext().put(name, p.toFile());
			});
		// @formatter:on
		return entityContext;
	}

	public Map<String, File> getContext() {
		if (this.context == null) {
			this.context = new HashMap<>();
		}
		return context;
	}

}
