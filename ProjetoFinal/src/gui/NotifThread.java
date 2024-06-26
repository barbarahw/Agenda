package gui;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import entities.Compromisso;

public class NotifThread extends Thread{
	private String nome;
	private int tempo;
	private List<Compromisso> compromissos;
	
	public NotifThread(String nome, int tempo) {
		
		this.nome = nome;
		this.tempo = tempo;
		
	}
	
	@Override
	public void run() {
		try {
			
			LocalDate localDate = LocalDate.now();
	        Date data = java.sql.Date.valueOf(localDate);
	        
	        for (Compromisso compromisso : compromissos) {
				if (compromisso.getDataNotif() == data) {
					 LocalTime horaAtual = LocalTime.now().withSecond(0).withNano(0);
					 
					 Time hora = java.sql.Time.valueOf(horaAtual);
					if (compromisso.getHoraNotif() == hora) {
						JOptionPane.showMessageDialog(null, compromisso.getTitulo() + "\n" + compromisso.getDescricao());
					}
				}
	        }
		} finally {
			
		}
	}
}
