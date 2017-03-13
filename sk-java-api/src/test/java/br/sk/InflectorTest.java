package br.sk;

import br.sk.utils.Inflector;

public class InflectorTest {

	public static void main(String[] args) {
		System.out.println(Inflector.getForLocale("pt_BR").pluralize("material"));
		System.out.println(Inflector.getForLocale("pt_BR").pluralize("atividade"));
		System.out.println(Inflector.getForLocale("pt_BR").singularize("materiais"));
	}

}
