package br.sk.api;

import java.io.IOException;

import br.sk.context.EntityContext;
import br.sk.utils.JSON;

public class Entities {

	/**
	 * 
	 * @param sourcePath
	 * @param entityName
	 * @return
	 * @throws IOException
	 */
	public static String findByName(String sourcePath, String entityName) throws IOException {
		EntityContext context = EntityContext.of(sourcePath);
		//// @formatter:off
		return context.findEntityByName(entityName)
				.map(entity -> JSON.stringify(entity))
				.orElse(null);
		// @formatter:on
	}

	/**
	 * 
	 * @param sourcePath
	 * @return
	 * @throws IOException
	 */
	public static String findAll(String sourcePath) throws IOException {
		EntityContext context = EntityContext.of(sourcePath);
		return JSON.stringify(context.findAll());
	}

	/**
	 * 
	 * @param sourcePath
	 * @return
	 * @throws IOException
	 */
	public static String getNames(String sourcePath) throws IOException {
		EntityContext context = EntityContext.of(sourcePath);
		return JSON.stringify(context.getNames());
	}

}
