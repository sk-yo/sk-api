package br.sk.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

import br.sk.model.impl.EntityImpl;
import br.sk.model.jpa.Entity;

public class EntityFactory {

	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Entity create(String path) throws FileNotFoundException, IOException {
		JavaDocBuilder doc = new JavaDocBuilder();
		JavaSource source = doc.addSource(new File(path));
		JavaClass javaClass = source.getClasses()[0];
		return new EntityImpl(javaClass);
	}
}
