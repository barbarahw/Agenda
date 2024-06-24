	package gui;

import java.awt.EventQueue;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTable table;
	private JSpinner spId;
	
	private AgendaService agendaService;
	
	private String usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaWindow frame = new AgendaWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void buscarAgendas() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);
		
		try {
			
			List<Agenda> agendas = this.agendaService.buscarAgendas();
			
			for (Agenda agenda : agendas) {
				modelo.addRow(new Object[] {
				agenda.getId(),
				agenda.getNome()
				});
			}
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Nenhuma agenda encontrada", "busca", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void criarAgenda() {
		try {
			Agenda agenda = new Agenda();
			
			agenda.setNome(txtNome.getText());
			agenda.setDescricao(txtDescricao.getText());
			
			this.agendaService.criarAgenda(agenda);
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
		}catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, "erro ao atualizar agenda", "erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void verCompromissos() {
		
	}
	
	public AgendaWindow() {
		
		initComponents();
		
		agendaService = new AgendaService();
		
		this.buscarAgendas();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Agendas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 523, 180);
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
		btnAtualizar.setBounds(53, 113, 85, 21);
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
		btnCompromissos.setBounds(53, 144, 147, 26);
		panel.add(btnCompromissos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 523, 150);
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
	}
}
