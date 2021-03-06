package br.sk.context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

import br.sk.model.Entity;
import br.sk.model.impl.EntityImpl;

public class EntityContext {

	private Map<String, File> context;

	private EntityContext() {
		super();
	}

	public Optional<Entity> findEntityByName(String name) {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		JavaSource javaSource;
		try {
			javaSource = builder.addSource(context.get(name));
			return Optional.of(new EntityImpl(this, javaSource.getClasses().get(0), false));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Entity> findEntityByName(String name, boolean backReference) {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		JavaSource javaSource;
		try {
			javaSource = builder.addSource(context.get(name));
			return Optional.of(new EntityImpl(this, javaSource.getClasses().get(0), backReference));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Entity> findAll() {
		List<Entity> entities = new ArrayList<>();
		try {
			JavaProjectBuilder builder = new JavaProjectBuilder();
			Iterator<String> iter = this.context.keySet().iterator();
			while (iter.hasNext()) {
				String name = iter.next();
				JavaSource javaSource = builder.addSource(context.get(name));
				JavaClass javaClass = javaSource.getClasses().get(0);
				if (javaClass.getAnnotations().stream().anyMatch(ann -> ann.getType().getValue().equals("Entity"))) {
					entities.add(new EntityImpl(this, javaClass, false));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entities;

	}
	
	public List<String> getNames() {
		List<String> entities = new ArrayList<>();
		try {
			JavaProjectBuilder builder = new JavaProjectBuilder();
			Iterator<String> iter = this.context.keySet().iterator();
			while (iter.hasNext()) {
				String name = iter.next();
				JavaSource javaSource = builder.addSource(context.get(name));
				JavaClass javaClass = javaSource.getClasses().get(0);
				if (javaClass.getAnnotations().stream().anyMatch(ann -> ann.getType().getValue().equals("Entity"))) {
					entities.add(javaClass.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entities;
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
