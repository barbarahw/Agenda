package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(10, 58, 96, 13);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(72, 55, 224, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 101, 45, 13);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(72, 98, 224, 19);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(255, 136, 85, 21);
		contentPane.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(244, 213, 96, 21);
		contentPane.add(btnCadastrar);
		
		JLabel lblCadastrar = new JLabel("Não possui uma conta?");
		lblCadastrar.setBounds(156, 190, 147, 13);
		contentPane.add(lblCadastrar);
		
		JLabel lblBemVindo = new JLabel("Bem vindo!");
		lblBemVindo.setBounds(10, 10, 76, 13);
		contentPane.add(lblBemVindo);
	}
}