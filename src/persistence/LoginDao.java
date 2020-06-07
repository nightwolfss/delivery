package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Login;

public class LoginDao extends Dao{
	
	public String criarLogin(Login login) {
		try {
			conectarBanco();
			stmt = con.prepareStatement("insert into login (nome, senha, admin) values (?,?,?)");
			stmt.setString(1, login.getNome());
			stmt.setString(2, login.getSenha());
			stmt.setString(3, login.getAdmin());
			stmt.execute();
			System.out.println("Login salvo com sucesso.");
			con.close();
			return "Login salvo com sucesso.";
	
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return "Falha ao criar Login" + e.getMessage();
		}
	}
	
	public Login logar(String nome, String senha) {
		Login l = null;
		
		try {
		conectarBanco();
		stmt = con.prepareStatement("select * from login where nome = ? and senha = ?");
		stmt.setString(1, nome);
		stmt.setString(2, senha);
		rs = stmt.executeQuery();
		
			if(rs.next()) {
				l = new Login();
				l.setId(Integer.parseInt(rs.getString("id")));
				l.setNome(rs.getString("nome"));
				l.setAdmin(rs.getString("admin"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
		return l;
	}
	
	public Login consultarLoginPorId(Integer id) {
		Login l = null;
		
		try {
		conectarBanco();
		stmt = con.prepareStatement("select * from login where id = ?");
		stmt.setString(1, id.toString());
		rs = stmt.executeQuery();
		
			if(rs.next()) {
				l = new Login();
				l.setId(Integer.parseInt(rs.getString("id")));
				l.setNome(rs.getString("nome"));
				l.setAdmin(rs.getString("admin"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
		return l;
	}
	
	public String consultarLoginPorNome(String nome) {
		
		try {
		conectarBanco();
		stmt = con.prepareStatement("select * from login where nome = ?");
		stmt.setString(1, nome);
		rs = stmt.executeQuery();
		
			if(rs.next()) {
				return "Nome não está disponível";
			}
			con.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "";
	}
	
	public List<Login> consultarLoginTodos() {
		Login l = null;
		ArrayList<Login> lista = new ArrayList<Login>();
		try {
		conectarBanco();
		stmt = con.prepareStatement("select * from login");
		rs = stmt.executeQuery();
		
			while(rs.next()) {
				l = new Login();
				l.setId(Integer.parseInt(rs.getString("id")));
				l.setNome(rs.getString("nome"));
				l.setAdmin(rs.getString("admin"));
				lista.add(l);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
		return lista;
	}

}
