package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Compromisso;

public class CompromissoDAO {
	private Connection conn;

	public CompromissoDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	public List<Compromisso> buscarTodos() throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from compromisso order by id");
			rs = st.executeQuery();
			
			List<Compromisso> compromissos = new ArrayList<>();
			
			while (rs.next()) {
				Compromisso compromisso = new Compromisso();
				
				compromisso.setTitulo(rs.getString("titulo"));
				compromisso.setDescricao(rs.getString("descricao"));
				compromisso.setDataInicio(rs.getDate("dataInicio"));
				compromisso.setDataTermino(rs.getDate("dataTermino"));
				compromisso.setHorarioInicio(rs.getTime("horaInicio"));
				compromisso.setHorarioTermino(rs.getTime("horaTermino"));
				compromisso.setLocal(rs.getString("local"));
				compromisso.setDataNotif(rs.getDate("dataNotificacao"));
				compromisso.setHoraNotif(rs.getTime("horaNotificacao"));
				
				compromissos.add(compromisso);
			}
			return compromissos;
		} finally {
			BancoDados.desconectar();
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}
	}
	
	public void cadastrar(Compromisso compromisso) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("insert into compromisso (titulo, descricao, dataInicio, dataTermino, horaInicio, horaTermino, local,dataNotificacao, horaNotificacao, idAgenda) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, compromisso.getTitulo());
            st.setString(2, compromisso.getDescricao());
            st.setDate(3, compromisso.getDataInicio());
            st.setDate(4, compromisso.getDataTermino());
            st.setTime(5, compromisso.getHorarioInicio());
            st.setTime(6, compromisso.getHorarioTermino());
            st.setString(7, compromisso.getLocal());
            st.setDate(8, compromisso.getDataNotif());
            st.setTime(9, compromisso.getHoraNotif());
            st.setInt(10, compromisso.getAgenda().getId());

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);;
            BancoDados.desconectar();
        }
    }
	
	public void atualizar(Compromisso compromisso) throws SQLException {
        PreparedStatement st = null;

        try{
        	st = conn.prepareStatement("UPDATE compromisso SET titulo = ?, descricao = ?, dataTermino = ?, dataInicio = ?, horaInicio = ?, horaTermino = ?, local = ?, idAgenda = ?, dataNotificacao = ?, horaNotificacao = ? WHERE id = ?");

            st.setString(1, compromisso.getTitulo());
            st.setString(2, compromisso.getDescricao());
            st.setDate(3, compromisso.getDataTermino());
            st.setDate(4, compromisso.getDataInicio());
            st.setTime(5, compromisso.getHorarioInicio());
            st.setTime(6, compromisso.getHorarioTermino());
            st.setString(7, compromisso.getLocal());
            st.setInt(8, compromisso.getAgenda().getId());
            st.setDate(9, compromisso.getDataNotif());
            st.setTime(10, compromisso.getHoraNotif());
            st.setInt(11, compromisso.getId());

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
	
	public void excluirCompromisso(int id) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("delete from compromisso where id = ?");
            st.setInt(1, id);

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }


    }
}


