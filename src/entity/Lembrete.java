package entity;

import java.util.ArrayList;

import persistence.LembreteDao;

public class Lembrete {
	
	private Integer id;
	private String titulo;
	private String texto;
	private String dia;
	private String mes;
	private String ano;
	private String hora;
	private String min;
	private Integer idDono;
	
	ArrayList<Lembrete> lista;
	ArrayList<Lembrete> listaPorLogin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public ArrayList<Lembrete> getLista() {
		LembreteDao ld = new LembreteDao();
		return this.lista = ld.listarTodosLembretes();
	}
	public void setLista(ArrayList<Lembrete> lista) {
		this.lista = lista;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	
	public Integer getIdDono() {
		return idDono;
	}
	public void setIdDono(Integer idDono) {
		this.idDono = idDono;
	}
	public ArrayList<Lembrete> getListaPorLogin(Integer idDono) {
		LembreteDao ld = new LembreteDao();
		return this.listaPorLogin = ld.listarTodosLembretesPorLogin(idDono);
	}
	
	@Override
	public String toString() {
		return "Lembrete [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", dia=" + dia + ", mes=" + mes
				+ ", ano=" + ano + ", hora=" + hora + ", min=" + min + "]";
	}
	
	
}
