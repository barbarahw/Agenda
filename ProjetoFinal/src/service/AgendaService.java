package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AgendaDAO;
import dao.BancoDados;
import entities.Agenda;
import entities.Usuario;

public class AgendaService {
	
	public AgendaService () {
		
	}
	
	public List<Agenda> buscarAgendas(String usuario) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
	
		return new AgendaDAO(conn).buscarAgendas(usuario);
	}
}
