package br.sk;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.sk.factory.EntityFactory;
import br.sk.model.jpa.Entity;

public class EntityFactoryTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Entity entity = EntityFactory.create("src/test/java/br/sk/model/Foo.java");
		System.out.println("NOME: " + entity.getName());
		System.out.println("NOME QUALIFICADO: " + entity.getFullyQualifiedName());
		entity.getAttributes().forEach(attr -> {
			System.out.println("ATTRIBUTE NAME: " + attr.getName());
			System.out.println("ATTRIBUTE GETTER NAME: " + attr.getGetterName());
			System.out.println("ATTRIBUTE SETTER NAME: " + attr.getSetterName());
			System.out.println("ATTRIBUTE LABEL: " + attr.getLabel());
			System.out.println("*********************************");
		});
	}

}
