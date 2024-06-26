	package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Agenda;
import entities.Usuario;
import service.AgendaService;
import service.UsuarioService;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTable table;
	private JSpinner spId;
	
	private AgendaService agendaService;
	private UsuarioService usuarioService;
	private Usuario usuario;
	private String username;
	private String senha;


	private void criarAgenda() {
		try {
			Agenda agenda = new Agenda();
			
			agenda.setNome(txtNome.getText());
			agenda.setDescricao(txtDescricao.getText());
			agenda.setUsuario(this.usuario);
			
			this.agendaService.criarAgenda(agenda);
			
			JOptionPane.showMessageDialog(null, "agenda criada com sucesso");
			this.buscarAgendas(username);
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, "erro ao cadastrar agenda", "erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizarAgenda() {
		try {
			Agenda agenda = new Agenda();
			
			agenda.setId(Integer.parseInt(this.spId.getValue().toString()));
			agenda.setNome(txtNome.getText());
			agenda.setDescricao(txtDescricao.getText());
			
			this.agendaService.atualizar(agenda);
			
			this.buscarAgendas(username);
		}catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, "erro ao atualizar agenda", "erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void verCompromissos() {
		try {
			
			Agenda agenda = this.agendaService.buscarAgendaPorId(Integer.parseInt(this.spId.getValue().toString()));
			if (agenda!=null) {
				abrirCompromissos(agenda.getId());
			}
			
		} catch (NumberFormatException | SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "agenda não encontrada", "erro", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	private void abrirCompromissos(int id) {
		
		CompromissoWindow compromissoWindow = new CompromissoWindow(this.buscarAgendaPorId(), this);
		compromissoWindow.setVisible(true);
		this.setVisible(false);
	}
	
	private void excluir() {
		try {
			this.agendaService.excluirAgenda(Integer.parseInt(this.spId.getValue().toString()));
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null,"erro ao deletar agenda, só é possível deletar uma agenda sem compromissos", "erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Agenda buscarAgendaPorId() {
		int id = Integer.parseInt(this.spId.getValue().toString());
		
		try {
			
			return this.agendaService.buscarAgendaPorId(id);
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "agenda não encontrada", "erro", JOptionPane.ERROR_MESSAGE);
			
			return null;
		}
		
	}
	
	private Usuario buscarUsuario(String usuario) {
		try {
			return this.usuarioService.verificarUsuario(this.usuario.getUsuario(), this.usuario.getSenha());
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "usuario não encontrado");
			return null;
		}
	}
	
	private void abrirPerfil() {
		
		PerfilWindow perfilwindow;
		perfilwindow = new PerfilWindow(this.usuario.getUsuario(), this.usuario.getSenha());
		
		perfilwindow.setVisible(true);
		this.setVisible(false);

		
	}
	
	private void buscarAgendas(String username) {
		try {
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
			
			List<Agenda> agendas = this.agendaService.buscarAgendas(username);
			
			for (Agenda agenda : agendas ) {
				modelo.addRow(new Object [] {
						agenda.getId(),
						agenda.getNome()
				});
			}
		} catch(SQLException | IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public AgendaWindow(Usuario usuario) {
		
		this.usuario = usuario;
		this.username = usuario.getUsuario();
		
		initComponents();
		
		agendaService = new AgendaService();
		usuarioService = new UsuarioService();
		
		this.buscarAgendas(usuario.getUsuario());
		
	}
	
	
	/*public static void main(String[] args) {
		NotifThread notificacao = new NotifThread("thread", 1000);
		notificacao.start();
	}*/
	
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Agendas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 44, 523, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNomeAgenda = new JLabel("Nome: ");
		lblNomeAgenda.setBounds(10, 22, 45, 13);
		panel.add(lblNomeAgenda);
		
		txtNome = new JTextField();
		txtNome.setBounds(68, 19, 445, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setBounds(10, 59, 68, 13);
		panel.add(lblDescricao);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(78, 56, 435, 42);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JButton btnCriarAgenda = new JButton("Criar Agenda");
		btnCriarAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarAgenda();
			}
		});
		btnCriarAgenda.setBounds(390, 113, 123, 21);
		panel.add(btnCriarAgenda);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarAgenda();
			}
		});
		btnAtualizar.setBounds(53, 108, 85, 21);
		panel.add(btnAtualizar);
		
		spId = new JSpinner();
		spId.setBounds(10, 135, 30, 20);
		panel.add(spId);
		
		JButton btnCompromissos = new JButton("Ver Compromissos");
		btnCompromissos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCompromissos();
			}
		});
		btnCompromissos.setBounds(50, 134, 147, 21);
		panel.add(btnCompromissos);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(53, 164, 85, 21);
		panel.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 261, 523, 150);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnPerfil = new JButton("Ver Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPerfil();
			}
		});
		btnPerfil.setBounds(411, 10, 122, 21);
		contentPane.add(btnPerfil);
	}
}
