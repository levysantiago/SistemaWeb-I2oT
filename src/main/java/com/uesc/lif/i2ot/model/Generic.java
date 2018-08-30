package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
// Para informar que essa classe não é do hibernate, mas que vai ser usada como
// classe mãe de outras.
@MappedSuperclass
public class Generic implements Serializable {
	// Definindo 'codigo' como um id, todos que herdarem da classe vao ter um id.
	@Id
	// Definindo geração de valores automático do id, feito pelo banco (AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=true)
	private Character deleted = '0';

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Character getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Character deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Generic other = (Generic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
