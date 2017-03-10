package br.sk.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Bar {

	@Id
	private Long id;

	/**
	 * 
	 * @label Nome
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	@ManyToMany
	private List<Foo> foos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Foo> getFoos() {
		return foos;
	}

	public void setFoos(List<Foo> foos) {
		this.foos = foos;
	}

}
