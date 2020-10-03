package com.uesc.lif.i2ot.model;

import javax.transaction.Transactional;

import com.google.gson.Gson;

@Transactional
public class RfidReader {
	private long id;
	private String nome;
	private String local;
	private String tag_lida;
	
	public RfidReader(long id, String nome, String local, String tag_lida) {
		super();
		this.id = id;
		this.nome = nome;
		this.local = local;
		this.tag_lida = tag_lida;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTag_lida() {
		return tag_lida;
	}

	public void setTag_lida(String tag_lida) {
		this.tag_lida = tag_lida;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
