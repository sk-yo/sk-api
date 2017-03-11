package br.sk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	private Long id;

	private Integer ano;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
