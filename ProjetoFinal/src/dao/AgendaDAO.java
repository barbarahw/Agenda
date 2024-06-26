package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Agenda;
import entities.Usuario;

public class AgendaDAO {

	private Connection conn;
	
	public AgendaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Agenda> buscarAgendas(String usuario) throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from agenda where usuario = ?");
		
			st.setString(1, usuario);
		
			rs = st.executeQuery();
		
			List<Agenda> agendas = new ArrayList<>();
		
			while (rs.next()) {
				Agenda agenda = new Agenda();
			
				agenda.setId(rs.getInt("id"));
				agenda.setNome(rs.getString("nome"));
				agenda.setDescricao(rs.getString("descricao"));
				//agenda.getUsuario().setUsuario(rs.getString("usuario"));
			
			agendas.add(agenda);
		}
		
			return agendas;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
	
	public void cadastrar(Agenda agenda) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("insert into agenda (nome, descricao, usuario) values (?, ?, ?)");

            st.setString(1, agenda.getNome());
            st.setString(2, agenda.getDescricao());
            st.setString(3, agenda.getUsuario().getUsuario());

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
	
	public void atualizar(Agenda agenda) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("update agenda set nome = ?, descricao = ? where id = ?");

            st.setString(1, agenda.getNome());
            st.setString(2, agenda.getDescricao());
            st.setInt(3, agenda.getId());

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
	
	public int excluir(int id) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("delete from agenda where id = ?");
            st.setInt(1, id);
            int linhasManipuldas = st.executeUpdate();

            return linhasManipuldas;

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
	
	public Agenda buscarAgendaPorId(int Id) throws SQLException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("select * from agenda where id = ?");
            st.setInt(1, Id);

            rs = st.executeQuery();
            if (rs.next()) {
            	Agenda agenda = new Agenda();

            	agenda.setId(rs.getInt("id"));
            	agenda.setNome(rs.getString("nome"));
            	agenda.setDescricao(rs.getString("descricao"));
            
            	return agenda;
            }
            return null;

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }
}
