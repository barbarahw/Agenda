package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JTextField txtUsuario;
	private final ButtonGroup buttongroup = new ButtonGroup();
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnNaoInformar;
	private JDateChooser dateChooser;

	private UsuarioService usuarioService;
	private JPasswordField txtSenha;
	
	private Usuario usuario;
	

	private void excluirUsuario() {
		int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário?", "Confirmação", JOptionPane.YES_NO_OPTION);
		if (confirmacao == 0) {
			try {
			
				this.usuarioService.excluirUsuario();
			
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
		int valorRetorno = seletor.showSaveDialog(null);
	}
	
	public PerfilWindow(Usuario usuario) {
		initComponents();
		
		this.usuarioService = new UsuarioService();
		this.usuario = usuario;
		
		this.mudar();
	}
	
	private void mudar() {
		txtNome.setText(this.usuario.getNome());
		txtEmail.setText(this.usuario.getEmail());
		dateChooser.setDate(this.usuario.getDataNascimento());
		txtUsuario.setText(this.usuario.getUsuario());
		txtSenha.setText(this.usuario.getSenha());
		
		this.checarSexo();
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
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(226, 171, 130, 19);
		contentPane.add(dateChooser);
		
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
		
		JLabel lblUsuario = new JLabel("Nome de Usuário:");
		lblUsuario.setBounds(10, 20, 121, 13);
		panel_1.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(126, 17, 249, 19);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 50, 45, 13);
		panel_1.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(76, 47, 299, 19);
		panel_1.add(txtSenha);
		
		JButton btnAtualizar = new JButton("Atualizar");
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
		btnExcluir.setBounds(429, 307, 113, 27);
		contentPane.add(btnExcluir);
	}
}
