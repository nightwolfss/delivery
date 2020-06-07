package entity;

public class Login {

	private Integer id;
	private String nome;
	private String senha;
	private String admin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "Login [id=" + id + ", nome=" + nome + ", senha=" + senha + ", admin=" + admin + "]";
	}

	
	
}
