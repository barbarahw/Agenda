package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.CompromissoDAO;
import entities.Compromisso;

public class CompromissoService {

	
	public CompromissoService() {
		
	}
	
	public void cadastrar(Compromisso compromisso) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new CompromissoDAO(conn).cadastrar(compromisso);
	}
	
	public void excluir(int id) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new CompromissoDAO(conn).excluirCompromisso(id);
	}
	
	public void atualizar(Compromisso compromisso) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new CompromissoDAO(conn).atualizar(compromisso);
	}
	
	public List<Compromisso> buscarTodos() throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).buscarTodos();
	}
}
