package br.sk.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FOO")
public class Foo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	/**
	 * 
	 * @label Nome
	 */
	@Column(name = "NAME", unique = true, length = 1024, nullable = false)
	private String name;

	@ManyToMany
	private List<Bar> bars;

	@OneToOne(mappedBy = "bars", orphanRemoval = true)
	private Car car;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bar> getBars() {
		return bars;
	}

	public void setBars(List<Bar> bars) {
		this.bars = bars;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
