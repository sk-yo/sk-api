package br.sk;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.sk.factory.EntityFactory;
import br.sk.model.jpa.Entity;

public class EntityFactoryTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Entity entity = EntityFactory.create("src/test/java", "br.sk.model.Foo");
		//System.out.println("NOME: " + entity.getName());
		//System.out.println("NOME QUALIFICADO: " + entity.getFullyQualifiedName());
		
		/*
		entity.getAttributes().forEach(attr -> {
			System.out.println("ATTRIBUTE NAME: " + attr.getName());
			System.out.println("ATTRIBUTE IS_ID: " + attr.isId());
			System.out.println("ATTRIBUTE GETTER NAME: " + attr.getGetterName());
			System.out.println("ATTRIBUTE SETTER NAME: " + attr.getSetterName());
			System.out.println("ATTRIBUTE LABEL: " + attr.getLabel());
			System.out.println("ATTRIBUTE IS_COLLECTION: " + attr.isList());
			System.out.println("*********************************");
		});
		*/

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		//System.out.println(entity);
		String json = objectMapper.writeValueAsString(entity);
		System.out.println(json);
	}

}
