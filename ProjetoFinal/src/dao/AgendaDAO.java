package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Agenda;

public class AgendaDAO {

	private Connection conn;
	
	public AgendaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Agenda> buscarAgendas(String usuario) throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		//st = conn.prepareStatement("select id, nome from agenda");
		st = conn.prepareStatement("select nome, descricao from agenda where usuario = ?");
		
		st.setString(0, usuario);
		rs = st.executeQuery();
		
		List<Agenda> agendas = new ArrayList<>();
		
		while (rs.next()) {
			Agenda agenda = new Agenda();
			
			agenda.setId(rs.getInt("id"));
			agenda.setNome(rs.getString("nome"));
			
			agendas.add(agenda);
		}
		
			return agendas;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
	
	public void cadastrar(Agenda agenda) {
		
	}
	
	public void atualizar(Agenda agenda) {
		
	}
	
	public void excluir(Agenda agenda) {
		
	}
	
	public Agenda buscarAgendaPorId(int Id) {
		return null;
	}
}
