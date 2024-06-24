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
	
	public void buscarUsuario() {
		
	}
	
	public void excluirUsuario() {
		
	}
}
