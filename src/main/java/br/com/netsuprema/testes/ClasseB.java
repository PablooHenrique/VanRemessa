package br.com.netsuprema.testes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="classeb")
public class ClasseB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
