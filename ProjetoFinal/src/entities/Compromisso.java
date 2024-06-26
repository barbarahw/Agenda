package entities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Compromisso {

	private int id;
	private String titulo;
	private String descricao;
	private Date dataInicio;
	private Date dataTermino;
	private Time horarioInicio;
	private Time horarioTermino;
	private String Local;
	private Agenda agenda;
	private List<Usuario> convidados;
	private Date dataNotif;
	private Time horaNotif;
	
	public Compromisso () {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date date) {
		this.dataInicio = date;
	}


	public Date getDataTermino() {
		return dataTermino;
	}


	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
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


	public Date getDataNotif() {
		return dataNotif;
	}


	public void setDataNotif(Date dataNotif) {
		this.dataNotif = dataNotif;
	}


	public Time getHoraNotif() {
		return horaNotif;
	}


	public void setHoraNotif(Time horaNotif) {
		this.horaNotif = horaNotif;
	}



	
	
	
	
}
