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
		return null; 
		
		/*PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		st = conn.prepareStatement("select nome, descricao from agenda where usuario = ?");
		
		st.setString(0, usuario);
		rs = st.executeQuery();
		
		List<Agenda> agendas = new ArrayList<>();
		
		while (rs.next()) {
			Agenda agenda = new Agenda();
			
			agenda.setNome(rs.getString("nome"));
			agenda.setDescricao(rs.getString("descricao"));
			
			agendas.add(agenda);
		}
		
			return agendas;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}*/
	}
}
