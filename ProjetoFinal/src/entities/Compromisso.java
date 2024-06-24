package entities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Compromisso {

	private String titulo;
	private String descricao;
	private Date date;
	private Time horarioInicio;
	private Time horarioTermino;
	private String Local;
	private Agenda agenda;
	private List<Usuario> convidados;
	private Timestamp notificacao;
	
	
	public Compromisso(String titulo, String descricao, Date date, Time horarioInicio, Time horarioTermino,
			String local, Agenda agenda, List<Usuario> convidados, Timestamp notificacao) {
	
		this.titulo = titulo;
		this.descricao = descricao;
		this.date = date;
		this.horarioInicio = horarioInicio;
		this.horarioTermino = horarioTermino;
		Local = local;
		this.agenda = agenda;
		this.convidados = convidados;
		this.notificacao = notificacao;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Time getHorarioInicio() {
		return horarioInicio;
	}


	public void setHorarioInicio(Time horarioInicio) {
		this.horarioInicio = horarioInicio;
	}


	public Time getHorarioTermino() {
		return horarioTermino;
	}


	public void setHorarioTermino(Time horarioTermino) {
		this.horarioTermino = horarioTermino;
	}


	public String getLocal() {
		return Local;
	}


	public void setLocal(String local) {
		Local = local;
	}


	public Agenda getAgenda() {
		return agenda;
	}


	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}


	public List<Usuario> getConvidados() {
		return convidados;
	}


	public void setConvidados(List<Usuario> convidados) {
		this.convidados = convidados;
	}


	public Timestamp getNotificacao() {
		return notificacao;
	}


	public void setNotificacao(Timestamp notificacao) {
		this.notificacao = notificacao;
	}
	
	
	
	
}
