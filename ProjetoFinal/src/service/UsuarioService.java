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

	public void excluirUsuario(String usuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new UsuarioDAO(conn).excluirUsuario(usuario);
	}
	
	public Usuario verificarUsuario(String usuario, String senha) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).verificarUsuario(usuario, senha);
		
	}
	
	public void atualizar(Usuario usuario) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new UsuarioDAO(conn).atualizar(usuario);
	}

}
