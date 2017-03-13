package br.sk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.sk.context.EntityContext;
import br.sk.model.Entity;

public class EntityFactoryTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		EntityContext context = EntityContext.of("/home/jcruz/workspace-test/app2/src/main/java");

		Optional<Entity> entity = context.findEntityByName("Car");
		if (entity.isPresent()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String json = objectMapper.writeValueAsString(entity.get());
			System.out.println(json);
		}
	}

}
