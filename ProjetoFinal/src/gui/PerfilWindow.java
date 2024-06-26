package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import entities.Usuario;
import service.UsuarioService;
import javax.swing.JPasswordField;

public class PerfilWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblFotoPerfil;
	private JTextField txtEmail;
	private final ButtonGroup buttongroup = new ButtonGroup();
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnNaoInformar;
	private JDateChooser dataNasc;
	private JLabel lblUsuario;

	private UsuarioService usuarioService;
	private JPasswordField txtSenha;
	
	private String username;
	private Usuario usuario;
	

	private void excluirUsuario() {
		int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário?", "Confirmação", JOptionPane.YES_NO_OPTION);
		if (confirmacao == 0) {
			try {
			
				this.usuarioService.excluirUsuario(this.lblUsuario.getText());
				CadastroWindow cadastro = new CadastroWindow();
				cadastro.setVisible(true);
				this.setVisible(false);
			
			} catch (SQLException | IOException e) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir Usuario", "Excluir", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "o usuário não foi excluído");
		}
		
	}
	
	private void checarSexo() {
		if (this.usuario.getSexo() == "Masculino") {
			this.rdbtnMasculino.setSelected(true);
		} else if (this.usuario.getSexo() == "Feminino") {
			this.rdbtnFeminino.setSelected(true);
		} else {
			this.rdbtnNaoInformar.setSelected(true);
		}
	}
	
	private void mudarFoto() {
		JFileChooser seletor = new JFileChooser();
		File novaFoto = new File("\\ProjetoFinal\\src\\gui\\Perfil.png");
		
		int valorRetorno = seletor.showSaveDialog(null);
		seletor.getSelectedFile().renameTo(novaFoto);
	}
	
	public PerfilWindow(String username, String senha) {
		
		try {
			initComponents(); 
			this.username = username;
			
			this.usuarioService = new UsuarioService();
			
			this.usuario = usuarioService.verificarUsuario(username, senha);
		
			this.mudar();
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initComponents();
		this.mudar();
	}
	
	private void mudar() {
		this.lblUsuario.setText(this.usuario.getUsuario());
		txtNome.setText(this.usuario.getNome());
		txtEmail.setText(this.usuario.getEmail());
		dataNasc.setDate(this.usuario.getDataNascimento());
		txtSenha.setText(this.usuario.getSenha());
		
		this.checarSexo();
	}
	
	private void atualizar() {
		try {
			Usuario usuario = new Usuario();
			
			java.util.Date a = this.dataNasc.getDate();
			
			java.sql.Date dataNova = new java.sql.Date(a.getTime());
		
			usuario.setUsuario(this.lblUsuario.getText());
			usuario.setSenha(this.txtSenha.getText());
			usuario.setNome(this.txtNome.getText());
			usuario.setSexo(verificarSelecaoRadioButtonSexo());
			usuario.setEmail(this.txtEmail.getText());
			usuario.setDataNascimento(dataNova);
		
		
			this.usuarioService.atualizar(usuario);
			this.mudar();
			JOptionPane.showMessageDialog(null, "atualizado com sucesso!");
		
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cadastro", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	private String verificarSelecaoRadioButtonSexo() {

		if (this.rdbtnMasculino.isSelected()) {
			return this.rdbtnMasculino.getText();
		} else if (this.rdbtnFeminino.isSelected()) {
			return this.rdbtnFeminino.getText();
		} else {
			return this.rdbtnNaoInformar.getText();
		}
	}
	
	private void abrirAgendaWindow() {
		AgendaWindow agendaWindow = new AgendaWindow(usuario);
		agendaWindow.setVisible(true);
		this.setVisible(false);
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(216, 47, 326, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblFotoPerfil = new JLabel("New label");
		lblFotoPerfil.setIcon(new ImageIcon(PerfilWindow.class.getResource("/gui/Perfil.png")));
		lblFotoPerfil.setBounds(10, 13, 196, 176);
		contentPane.add(lblFotoPerfil);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(216, 108, 326, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(216, 24, 45, 13);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(216, 85, 45, 13);
		contentPane.add(lblEmail);
		
		JButton btnFotoPerfil = new JButton("Mudar Foto de Perfil");
		btnFotoPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudarFoto();
			}
		});
		btnFotoPerfil.setBounds(10, 199, 172, 21);
		contentPane.add(btnFotoPerfil);
		
		dataNasc = new JDateChooser();
		dataNasc.setBounds(226, 171, 130, 19);
		contentPane.add(dataNasc);
		
		JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
		lblDataNascimento.setBounds(216, 148, 130, 13);
		contentPane.add(lblDataNascimento);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(419, 135, 126, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(6, 23, 103, 21);
		panel.add(rdbtnFeminino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(6, 46, 103, 21);
		panel.add(rdbtnMasculino);
		
		rdbtnNaoInformar = new JRadioButton("Não Informar");
		rdbtnNaoInformar.setBounds(6, 69, 103, 21);
		panel.add(rdbtnNaoInformar);
		
		buttongroup.add(rdbtnFeminino);
		buttongroup.add(rdbtnMasculino);
		buttongroup.add(rdbtnNaoInformar);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(10, 251, 385, 110);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblUsuario = new JLabel("Nome de Usuário:");
		lblUsuario.setBounds(10, 20, 121, 13);
		panel_1.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 50, 45, 13);
		panel_1.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(76, 47, 299, 19);
		panel_1.add(txtSenha);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnAtualizar.setBounds(429, 251, 85, 21);
		contentPane.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir Perfil");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBackground(new Color(255, 0, 0));
		btnExcluir.setBounds(429, 282, 113, 27);
		contentPane.add(btnExcluir);
		
		JButton btnAgendas = new JButton("Ver agendas");
		btnAgendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAgendaWindow();
			}
		});
		btnAgendas.setBounds(429, 340, 113, 21);
		contentPane.add(btnAgendas);
	}
}
