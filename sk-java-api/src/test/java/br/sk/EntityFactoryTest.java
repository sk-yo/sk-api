package br.sk;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.sk.factory.EntityFactory;
import br.sk.model.Entity;

public class EntityFactoryTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Entity entity = EntityFactory.create("src/test/java", "br.sk.model.Foo");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		//System.out.println(entity);
		String json = objectMapper.writeValueAsString(entity);
		System.out.println(json);
	}

}
