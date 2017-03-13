package br.sk;

import java.io.IOException;

import br.sk.factory.EntityContext;

public class EntitiesTest {
	public static void main(String[] args) throws IOException {
		EntityContext context = EntityContext.of("/home/jcruz/workspace-neon/aelis2016/src/main/java");
		
		System.out.println(context.findAll());

	}
}
