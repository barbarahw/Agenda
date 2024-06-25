package dao;

import java.sql.Connection;

import entities.Usuario;

public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Usuario usuario) {
		
	}
	
	public Usuario buscarUsuario() {
		return null;
		
	}
	
	public void excluirUsuario() {
		
	}
	
	public Usuario verificarUsuario(String usuari, String senha) {
		return null;
	}
}
