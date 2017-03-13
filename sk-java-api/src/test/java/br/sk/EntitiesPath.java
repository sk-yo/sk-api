package br.sk;

import java.io.IOException;

import br.sk.factory.EntityContext;

public class EntitiesPath {
	public static void main(String[] args) throws IOException {
		EntityContext context = EntityContext.of("/home/jcruz/workspace-neon/aelis2016/src/main/java");

		long startTime = System.currentTimeMillis();
		context.findAll().forEach(entity -> {
			System.out.println(entity.getName());
		});
		long endTime = System.currentTimeMillis();
		System.out.println("Tempo de Execução: " + (endTime - startTime) + "ms");

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

		startTime = System.currentTimeMillis();
		context.getNames().forEach(entityName -> {
			System.out.println(entityName);
		});
		endTime = System.currentTimeMillis();
		System.out.println("Tempo de Execução: " + (endTime - startTime) + "ms");

	}
}
