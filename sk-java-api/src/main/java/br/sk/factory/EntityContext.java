package br.sk.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import br.sk.model.Entity;
import br.sk.model.impl.EntityImpl;

public class EntityContext {

	private Map<String, File> context;

	private EntityContext() {
		super();
	}

	public Optional<Entity> findByName(String name) {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		JavaSource javaSource;
		try {
			javaSource = builder.addSource(context.get(name));
			return Optional.of(new EntityImpl(this, javaSource.getClasses().get(0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
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
