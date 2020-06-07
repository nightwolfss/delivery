package persistence;

import java.util.ArrayList;

import entity.Login;
import entity.Prato;

public class PratoDao extends Dao{
	
	public String gravarPrato(Prato prato) {
		try {
			conectarBanco();
			stmt = con.prepareStatement("insert into prato (descricao, nome, categoria, idDono) values (?,?,?,?)");
			stmt.setString(1, prato.getDescricaoPrato());
			stmt.setString(2, prato.getNome());
			stmt.setString(3, prato.getCategoria());
			stmt.setInt(4, prato.getIdDono());
			stmt.execute();
			con.close();
			return "Prato salvo om sucesso.";
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return "Falha ao salvar prato";
		}
	}
	
	public String deletarPrato(Integer id) {
		try {
			conectarBanco();
			stmt = con.prepareStatement("delete from prato where id=?");
			stmt.setInt(1, id);
			stmt.execute();
			
			con.close();
			
			return "Item deletado com sucesso!!";
		} catch (Exception e) {
			return "O ítem não pôde ser excluído: " + e.getMessage();
		}
	}
	
	public ArrayList<Prato> listaPrato(){
		try {
			ArrayList<Prato> lista = new ArrayList<Prato>();
			conectarBanco();
			stmt = con.prepareStatement("select * from prato");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Prato prato = new Prato();
				prato.setId(rs.getLong("id"));
				prato.setNome(rs.getString("nome"));
				prato.setDescricaoPrato(rs.getString("descricao"));
				prato.setCategoria(rs.getString("categoria"));
				prato.setIdDono(rs.getInt("idDono"));
				
				lista.add(prato);
			}
			con.close();
			return lista;
			
		} catch (Exception e) {
			System.out.println("Erro ao obter lista de pratos:" + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Prato> listaPratoPorLogin(Integer idDono){
		try {
			ArrayList<Prato> lista = new ArrayList<Prato>();
			conectarBanco();
			stmt = con.prepareStatement("select * from prato where idDono = ?");
			stmt.setInt(1, idDono);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Prato prato = new Prato();
				prato.setId(rs.getLong("id"));
				prato.setNome(rs.getString("nome"));
				prato.setDescricaoPrato(rs.getString("descricao"));
				prato.setCategoria(rs.getString("categoria"));
				prato.setIdDono(rs.getInt("idDono"));
				
				lista.add(prato);
			}
			con.close();
			return lista;
			
		} catch (Exception e) {
			System.out.println("Erro ao obter lista de pratos:" + e.getMessage());
			return null;
		}
	}

}
