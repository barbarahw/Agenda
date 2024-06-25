package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

public class CompromissoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompromissoWindow frame = new CompromissoWindow();
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
	public CompromissoWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 545, 202);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(10, 10, 45, 13);
		panel.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(65, 7, 220, 19);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setBounds(10, 42, 70, 13);
		panel.add(lblDescricao);
		
		textField = new JTextField();
		textField.setBounds(75, 39, 210, 41);
		panel.add(textField);
		textField.setColumns(10);
	}
}
