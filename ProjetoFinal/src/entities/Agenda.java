package entities;

public class Agenda {

	private String nome;
	private String descricao;
	private Usuario usuario;
	
	public Agenda(String nome, String descricao, Usuario usuario) {
		
		this.nome = nome;
		this.descricao = descricao;
		
	}
	
	public Agenda() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
