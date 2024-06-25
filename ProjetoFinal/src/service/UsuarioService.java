package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.UsuarioDAO;
import entities.Usuario;

public class UsuarioService {

	public UsuarioService() {
		
	}
	
	public void cadastrar(Usuario usuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new UsuarioDAO(conn).cadastrar(usuario);
	}

	public Usuario buscarUsuario(String usuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).buscarUsuario();
	
	}

	public void excluirUsuario() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new UsuarioDAO(conn).excluirUsuario();
	}
	
	public Usuario verificarUsuario(String usuario, String senha) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).verificarUsuario(usuario, senha);
		
	}

}
