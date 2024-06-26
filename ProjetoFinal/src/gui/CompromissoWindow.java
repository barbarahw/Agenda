package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import entities.Agenda;
import entities.Compromisso;
import service.CompromissoService;
import service.UsuarioService;
import gui.AgendaWindow;

public class CompromissoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtDescricao;
	private JTextField txtLocal;
	private JTable table;
	private JTextField txtConvite;
	private JDateChooser dateInicio;
	private JDateChooser dateTermino;
	private JDateChooser dateNotif;
	private JFormattedTextField txtHoraInicio;
	private JFormattedTextField txtHoraTermino;
	private JFormattedTextField txtHoraNotif;
	private MaskFormatter mascara;
	private JSpinner spId;
	
	private UsuarioService usuarioService;
	private CompromissoService compromissoService;
	private Agenda agenda;
	private AgendaWindow agendaWindow;

	/**
	 * Launch the application.
	 */
	
	
	private void criarMascara() {
		try {
			this.mascara = new MaskFormatter("##:##");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void buscarTodos() {
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
			
			List<Compromisso> compromissos;
			
			compromissos = this.compromissoService.buscarTodos();
			
			for (Compromisso compromisso : compromissos) {
				modelo.addRow(new Object [] {
						compromisso.getId(),
						compromisso.getTitulo(),
						compromisso.getLocal(),
						compromisso.getDataInicio(),
						compromisso.getDataTermino(),
						compromisso.getHorarioInicio(),
						compromisso.getHorarioTermino()
				});
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	private void convidar() {
		/*try {
			
			Usuario usuario = new Usuario();
			usuario = this.usuarioService.buscarUsuario(this.txtConvite.getText());
			if (usuario != null) {
				
			}
			
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "usuario não encontrado");
		}*/
	}
	
	private void cadastrar() {
		try {
			Compromisso compromisso = new Compromisso();
		
			java.util.Date a = this.dateInicio.getDate();
		
			java.sql.Date dataNova = new java.sql.Date(a.getTime());
			
			compromisso.setTitulo(this.txtTitulo.getText());
			compromisso.setDescricao(this.txtDescricao.getText());
			compromisso.setDataInicio(dataNova);
			
			a = this.dateTermino.getDate();
			dataNova = new java.sql.Date(a.getTime());
			
			compromisso.setDataTermino(dataNova);
			
			String hora = this.txtHoraInicio.getText();
			LocalTime horaCerta = LocalTime.parse(hora);
			
			
			compromisso.setHorarioInicio(Time.valueOf(horaCerta));
			
			hora = this.txtHoraTermino.getText();
			horaCerta = LocalTime.parse(hora);
			
			compromisso.setHorarioTermino(Time.valueOf(horaCerta));
			compromisso.setLocal(this.txtLocal.getText());
			compromisso.setAgenda(this.agenda);
			
			a = this.dateNotif.getDate();
			dataNova = new java.sql.Date(a.getTime());
			compromisso.setDataNotif(dataNova);
			
			hora = this.txtHoraNotif.getText();
			horaCerta = LocalTime.parse(hora);
			
			compromisso.setHoraNotif(Time.valueOf(horaCerta));
			
			
			this.compromissoService.cadastrar(compromisso);
			
			
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "não foi possível cadastrar");
		}
	}
	
	private void atualizar() {
		try {
			Compromisso compromisso = new Compromisso();
			
			java.util.Date a = this.dateInicio.getDate();
		
			java.sql.Date dataNova = new java.sql.Date(a.getTime());
			
			compromisso.setTitulo(this.txtTitulo.getText());
			compromisso.setDescricao(this.txtDescricao.getText());
			compromisso.setDataInicio(dataNova);
			
			a = this.dateTermino.getDate();
			dataNova = new java.sql.Date(a.getTime());
			
			compromisso.setDataTermino(dataNova);
			
			String hora = this.txtHoraInicio.getText();
			LocalTime horaCerta = LocalTime.parse(hora);
			
			
			compromisso.setHorarioInicio(Time.valueOf(horaCerta));
			
			hora = this.txtHoraTermino.getText();
			horaCerta = LocalTime.parse(hora);
			
			compromisso.setHorarioTermino(Time.valueOf(horaCerta));
			compromisso.setLocal(this.txtLocal.getText());
			compromisso.setAgenda(this.agenda);
			
			a = this.dateNotif.getDate();
			dataNova = new java.sql.Date(a.getTime());
			compromisso.setDataNotif(dataNova);
			
			hora = this.txtHoraNotif.getText();
			horaCerta = LocalTime.parse(hora);
			
			compromisso.setHoraNotif(Time.valueOf(horaCerta));
			compromisso.setId(Integer.parseInt(this.spId.getValue().toString()));
			
			this.compromissoService.atualizar(compromisso);
			JOptionPane.showMessageDialog(null, "atualizado com sucesso");
			
			this.buscarTodos();
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void abrirAgenda() {
		this.agendaWindow.setVisible(true);
		this.setVisible(false);
	}
	
	private void excluir() {
		try {
			
			this.compromissoService.excluir(Integer.parseInt(this.spId.getValue().toString()));
			JOptionPane.showMessageDialog(null, "excluído com sucesso!");
		} catch (NumberFormatException | SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "não foi possível excluir");
		}
	}
	
	public CompromissoWindow(Agenda agenda, AgendaWindow agendaWindow) {
		
		this.agendaWindow = agendaWindow;
		this.agenda = agenda;
		this.criarMascara();
		initComponents();
		
		compromissoService = new CompromissoService();
		
		this.buscarTodos();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 545, 278);
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
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(75, 39, 210, 41);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblDataInicio = new JLabel("Data Início: ");
		lblDataInicio.setBounds(295, 10, 89, 13);
		panel.add(lblDataInicio);
		
		JLabel lblDataTermino = new JLabel("Data Término: ");
		lblDataTermino.setBounds(295, 42, 114, 13);
		panel.add(lblDataTermino);
		
		dateInicio = new JDateChooser();
		dateInicio.setDateFormatString("dd/MM/yyyy");
		dateInicio.setBounds(409, 10, 106, 19);
		panel.add(dateInicio);
		
		dateTermino = new JDateChooser();
		dateTermino.setDateFormatString("dd/MM/yyyy");
		dateTermino.setBounds(419, 42, 106, 19);
		panel.add(dateTermino);
		
		JLabel lblLocal = new JLabel("Local: ");
		lblLocal.setBounds(10, 105, 45, 13);
		panel.add(lblLocal);
		
		txtLocal = new JTextField();
		txtLocal.setBounds(65, 102, 220, 19);
		panel.add(txtLocal);
		txtLocal.setColumns(10);
		
		JLabel lblHoraInicio = new JLabel("Hora Inicial: ");
		lblHoraInicio.setBounds(294, 105, 78, 13);
		panel.add(lblHoraInicio);
		
		txtHoraInicio = new JFormattedTextField(mascara);
		txtHoraInicio.setText("");
		txtHoraInicio.setBounds(402, 102, 114, 19);
		panel.add(txtHoraInicio);
		
		JLabel lblHoraTermino = new JLabel("Hora final: ");
		lblHoraTermino.setBounds(295, 139, 89, 13);
		panel.add(lblHoraTermino);
		
		txtHoraTermino = new JFormattedTextField(mascara);
		txtHoraTermino.setText("");
		txtHoraTermino.setBounds(401, 136, 114, 19);
		panel.add(txtHoraTermino);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Notifica\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(295, 165, 240, 77);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDataNotif = new JLabel("Data: ");
		lblDataNotif.setBounds(10, 21, 118, 13);
		panel_1.add(lblDataNotif);
		
		dateNotif = new JDateChooser();
		dateNotif.setDateFormatString("dd/MM/yyyy");
		dateNotif.setBounds(89, 21, 106, 19);
		panel_1.add(dateNotif);
		
		JLabel lblHoraNotif = new JLabel("Hora: ");
		lblHoraNotif.setBounds(10, 54, 45, 13);
		panel_1.add(lblHoraNotif);
		
		txtHoraNotif = new JFormattedTextField(mascara);
		txtHoraNotif.setText("");
		txtHoraNotif.setBounds(89, 51, 106, 19);
		panel_1.add(txtHoraNotif);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(440, 247, 85, 21);
		panel.add(btnCadastrar);
		
		spId = new JSpinner();
		spId.setBounds(10, 222, 30, 20);
		panel.add(spId);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
			
		});
		btnAtualizar.setBounds(50, 221, 85, 21);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(50, 247, 85, 21);
		panel.add(btnExcluir);
		
		txtConvite = new JTextField();
		txtConvite.setBounds(10, 175, 96, 19);
		panel.add(txtConvite);
		txtConvite.setColumns(10);
		
		JButton btnNConvite = new JButton("Convidar usuário");
		btnNConvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convidar();
			}
		});
		btnNConvite.setBounds(128, 174, 157, 21);
		panel.add(btnNConvite);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 294, 545, 114);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Titulo", "Local", "Data Inicio", "Data Termino", "Hora inicio", "Hora termino"
			}
		));
		
		JButton btnAgenda = new JButton("Voltar para a agenda");
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAgenda();
			}
		});
		btnAgenda.setBounds(402, 448, 153, 21);
		contentPane.add(btnAgenda);
	}
}
