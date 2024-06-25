package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import entities.Usuario;
import service.UsuarioService;
import javax.swing.JPasswordField;

public class CadastroWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JDateChooser dataNasc;
	
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnNaoInformar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private UsuarioService usuarioService;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroWindow frame = new CadastroWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroWindow() {
		
		initComponents();
		
		this.usuarioService = new UsuarioService();
	}
	
	private void cadastrar() {
		try {
			Usuario usuario = new Usuario();
		
			usuario.setUsuario(this.txtUsuario.getText());
			usuario.setSenha(this.txtSenha.getText());
			usuario.setNome(this.txtNome.getText());
			usuario.setSexo(verificarSelecaoRadioButtonSexo());
			usuario.setEmail(this.txtEmail.getText());
			usuario.setDataNascimento((Date) this.dataNasc.getDate());
		
		
			this.usuarioService.cadastrar(usuario);
			
			AgendaWindow agendaWindow = new AgendaWindow(usuario.getUsuario());
			agendaWindow.setVisible(true);
			this.setVisible(false);
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário", "Cadastro", JOptionPane.ERROR_MESSAGE);
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
	
	private void abrirLogin() {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.setVisible(true);
		this.setVisible(false);
	}
	
	private void initComponents() {
		setTitle("Cadastro\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 476, 190);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setBounds(10, 21, 102, 13);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(122, 18, 344, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JPanel panelSexo = new JPanel();
		panelSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBounds(10, 59, 121, 113);
		panel.add(panelSexo);
		panelSexo.setLayout(null);
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(6, 17, 103, 21);
		panelSexo.add(rdbtnFeminino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(6, 48, 103, 21);
		panelSexo.add(rdbtnMasculino);
		
		rdbtnNaoInformar = new JRadioButton("Não Informar");
		rdbtnNaoInformar.setBounds(6, 82, 103, 21);
		panelSexo.add(rdbtnNaoInformar);
		
		buttonGroup.add(rdbtnFeminino);
		buttonGroup.add(rdbtnMasculino);
		buttonGroup.add(rdbtnNaoInformar);
		
		JLabel lblDataNasc = new JLabel("Data de Nascimento:");
		lblDataNasc.setBounds(152, 59, 127, 13);
		panel.add(lblDataNasc);
		
		dataNasc = new JDateChooser();
		dataNasc.setDateFormatString("dd/MM/yyyy");
		dataNasc.setBounds(289, 59, 177, 19);
		panel.add(dataNasc);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(152, 98, 45, 13);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(207, 95, 259, 19);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dados da Conta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 210, 476, 121);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nome de Usuário:");
		lblUsuario.setBounds(10, 34, 103, 13);
		panel_1.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(134, 31, 332, 19);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 77, 45, 13);
		panel_1.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(65, 74, 401, 19);
		panel_1.add(txtSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(387, 341, 99, 21);
		contentPane.add(btnCadastrar);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirLogin();
			}
		});
		btnLogin.setBounds(401, 386, 85, 21);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Já possui uma conta?");
		lblNewLabel.setBounds(313, 367, 173, 13);
		contentPane.add(lblNewLabel);
	}
}
