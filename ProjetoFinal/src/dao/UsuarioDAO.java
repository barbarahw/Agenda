package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Agenda;
import entities.Usuario;

public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Usuario usuario) throws SQLException {
		PreparedStatement st = null;
		
        try{
        	
            st = conn.prepareStatement("insert into usuario (usuario, senha, nome, sexo, email, dataNascimento) values (?, ?, ?, ?, ?, ?)");

            st.setString(1, usuario.getUsuario());
            st.setString(2, usuario.getSenha());
            st.setString(3, usuario.getNome());
            st.setString(4, usuario.getSexo());
            st.setString(5, usuario.getEmail());
            st.setDate(6, usuario.getDataNascimento());

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

	
	public void atualizar(Usuario usuario) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("update usuario set usuario = ?, senha = ?, nome = ?, sexo = ?, email = ?, dataNascimento = ? where usuario = ?");

            st.setString(1, usuario.getUsuario());
            st.setString(2, usuario.getSenha());
            st.setString(3, usuario.getNome());
            st.setString(4, usuario.getSexo());
            st.setString(5, usuario.getEmail());
            st.setDate(6, usuario.getDataNascimento());
            st.setString(7, usuario.getUsuario());

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
	
	public void excluirUsuario(String usuario) throws SQLException {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("delete from usuario where usuario = ?");
            st.setString(1, usuario);

            st.executeUpdate();

        }finally{
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

    }
	
	public Usuario verificarUsuario(String usuario, String senha) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from usuario where usuario = ? and senha = ?");
			st.setString(1, usuario);
			st.setString(2, senha);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
			Usuario user = new Usuario();
			
				user.setUsuario(rs.getString("usuario"));
				user.setSenha(rs.getString("senha"));
				user.setSexo(rs.getString("sexo"));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				user.setDataNascimento(rs.getDate("dataNascimento"));
			
				return user;
			}
			return null;
			
		} finally {
			BancoDados.desconectar();
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}
	}
}
