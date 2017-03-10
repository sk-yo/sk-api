package br.sk.model.jpa;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thoughtworks.qdox.JavaProjectBuilder;

/**
 * Representação de uma entidade JPA.
 * 
 * @author jcruz
 *
 */
@JsonPropertyOrder(value = { "name", "fullyQualifiedName", "instanceName", "pluralizedInstanceName" })
public interface Entity {

	/**
	 * 
	 * @return
	 */
	JavaProjectBuilder getBuilder();

	/**
	 * Nome da classe.
	 * 
	 * Ex: Foo.java -> Foo
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Nome de instância (nome da classe com a primeira letra em caractere
	 * minusculo) da classe.
	 * 
	 * Ex: Foo.java -> foo
	 * 
	 * @return
	 */
	String getInstanceName();

	/**
	 * Nome de instância da classe no plural.
	 * 
	 * Ex: Foo.java -> foos
	 * 
	 * @return
	 */
	String getPluralizedInstanceName();

	/**
	 * Nome qualificado (Nome da classe mais o nome do pacote) da classe.
	 * 
	 * Ex: Foo.java -> br.foo
	 * 
	 * @return
	 */
	String getFullyQualifiedName();

	/**
	 * 
	 * @return
	 */
	String getTableName();

	/**
	 * 
	 * @return
	 */
	Set<EntityAttribute> getAttributes();

}
