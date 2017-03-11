package br.sk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.sk.factory.EntityContext;
import br.sk.model.Entity;

public class EntityFactoryTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		EntityContext context = EntityContext.of("src/test/java");

		Optional<Entity> entity = context.findByName("Foo");
		if (entity.isPresent()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			// System.out.println(entity);
			String json = objectMapper.writeValueAsString(entity.get());
			System.out.println(json);
		}
	}

}
