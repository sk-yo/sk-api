package br.sk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import br.sk.context.EntityContext;
import br.sk.model.Entity;
import br.sk.utils.JSON;

public class EntityFactoryTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		EntityContext context = EntityContext.of("/home/jcruz/workspace-test/app2");

		Optional<Entity> entity = context.findEntityByName("Car");
		if (entity.isPresent()) {
			System.out.println(JSON.stringify(entity.get()));
		}
	}

}
