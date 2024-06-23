package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import javax.swing.JSpinner;
import com.toedter.components.JSpinField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Choice;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class PerfilWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblFotoPerfil;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilWindow frame = new PerfilWindow();
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
	public PerfilWindow() {
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
		btnFotoPerfil.setBounds(10, 199, 172, 21);
		contentPane.add(btnFotoPerfil);
		
		JDateChooser dateChooser = new JDateChooser();
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
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(6, 23, 103, 21);
		panel.add(rdbtnFeminino);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(6, 46, 103, 21);
		panel.add(rdbtnMasculino);
		
		JRadioButton rdbtnNaoInformar = new JRadioButton("Não Informar");
		rdbtnNaoInformar.setBounds(6, 69, 103, 21);
		panel.add(rdbtnNaoInformar);
		
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
		
		txtSenha = new JTextField();
		txtSenha.setBounds(77, 47, 298, 19);
		panel_1.add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(429, 251, 85, 21);
		contentPane.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir Perfil");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBackground(new Color(255, 0, 0));
		btnExcluir.setBounds(429, 307, 113, 27);
		contentPane.add(btnExcluir);
	}
}
