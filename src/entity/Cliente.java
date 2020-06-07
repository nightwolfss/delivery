package entity;

import java.io.Serializable;
import java.util.ArrayList;

import persistence.ClienteDao;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	private String enderecoCliente;
	private String telefoneCliente;
	private String observacao;
	private Integer idDono;
	
	private ArrayList<Cliente>lista;
	private ArrayList<Cliente>listaPorLogin;
		
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEnderecoCliente() {
		return enderecoCliente;
	}
	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdDono() {
		return idDono;
	}
	public void setIdDono(Integer idDono) {
		this.idDono = idDono;
	}
	
	public ArrayList<Cliente> getLista() {
		ClienteDao cl = new ClienteDao();
		return this.lista = cl.listaCliente();
		
	}
	public void setLista(ArrayList<Cliente> lista) {
		this.lista = lista;
	}
	
	public ArrayList<Cliente> getListaPorLogin(Integer idDono) {
		ClienteDao cl = new ClienteDao();
		return this.listaPorLogin = cl.listaClientePorLogin(idDono);
	}
	
	public void setListaPorLogin(ArrayList<Cliente> listaPorLogin) {
		this.listaPorLogin = listaPorLogin;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", enderecoCliente=" + enderecoCliente + ", telefoneCliente="
				+ telefoneCliente + ", observacao=" + observacao + "]";
	}
	
	
}
