package br.sk.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.thoughtworks.qdox.JavaProjectBuilder;

import br.sk.model.impl.EntityImpl;
import br.sk.model.jpa.Entity;

public class EntityFactory {

	public static Entity create(String path, String className) throws FileNotFoundException, IOException {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		builder.addSourceFolder(new File(path));
		return new EntityImpl(builder, builder.getClassByName(className));
	}
}
