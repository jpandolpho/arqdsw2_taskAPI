package br.edu.ifsp.arqdsw2.taskAPI.model;

public class Tarefa {
	private int id;
	private String titulo;
	private String descricao;
	private boolean concluida;

	public Tarefa() {
	}

	public Tarefa(int id, String titulo, String descricao, boolean concluida) {
		this.setId(id);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setConcluida(concluida);
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}