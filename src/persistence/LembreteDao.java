package persistence;

import java.util.ArrayList;

import entity.Lembrete;

public class LembreteDao extends Dao{

	public String gravarLembrete(Lembrete lembrete) {
		try {
			conectarBanco();
			stmt = con.prepareStatement("insert into lembrete (titulo, texto, ano, mes, dia, hora, minuto, idDono) values (?,?,?,?,?,?,?,?)");
			stmt.setString(1, lembrete.getTitulo());
			stmt.setString(2, lembrete.getTexto());
			stmt.setString(3, lembrete.getAno());
			stmt.setString(4, lembrete.getMes());
			stmt.setString(5, lembrete.getDia());
			stmt.setString(6, lembrete.getHora());
			stmt.setString(7, lembrete.getMin());
			stmt.setInt(8, lembrete.getIdDono());
			stmt.execute();
			
			con.close();
			
			return lembrete.getTitulo() + " salvo com sucesso!";
			
		} catch (Exception e) {
			System.out.println("LembreteDao | gravarLembrete: " + e.getMessage());
			e.printStackTrace();
			return "Erro ao salvar o lembrete '"+ lembrete.getTitulo()+"': " + e.getMessage();
		}
	}
	
	public String deletarLembrete(Integer id) {
		try {
			conectarBanco();
			stmt = con.prepareStatement("delete from lembrete where id=?");
			stmt.setInt(1, id);
			stmt.execute();
			
			con.close();
			
			return "Lembrete excluído com sucesso!";
			
		} catch (Exception e) {
			System.out.println("LembreteDao | deletarLembrete: " + e.getMessage());
			return "Erro ao deletar lembrete: " + e.getMessage();
		}
	}
	
	public Lembrete buscarLembretePorId(Integer id){
		Lembrete lembrete = null;
		try {
			conectarBanco();
			stmt = con.prepareStatement("select * from lembrete where id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				lembrete = new Lembrete();
				lembrete.setId(rs.getInt("id"));
				lembrete.setTitulo(rs.getString("titulo"));
				lembrete.setTexto(rs.getString("texto"));
				lembrete.setAno(rs.getString("ano"));
				lembrete.setMes(rs.getString("mes"));
				lembrete.setDia(rs.getString("dia"));
				lembrete.setHora(rs.getString("hora"));
				lembrete.setMin(rs.getString("minuto"));
				
				
			}
			con.close();
			return lembrete;
			
		} catch (Exception e) {
			System.out.println("LembreteDao | buscarLembretePorId: " + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Lembrete> listarTodosLembretes(){
		ArrayList<Lembrete> lista = new ArrayList<Lembrete>();
		Lembrete lembrete;
		try {
			conectarBanco();
			stmt = con.prepareStatement("select * from lembrete");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				lembrete = new Lembrete();
				lembrete.setId(rs.getInt("id"));
				lembrete.setTitulo(rs.getString("titulo"));
				lembrete.setTexto(rs.getString("texto"));
				lembrete.setAno(rs.getString("ano"));
				lembrete.setMes(rs.getString("mes"));
				lembrete.setDia(rs.getString("dia"));
				lembrete.setHora(rs.getString("hora"));
				lembrete.setMin(rs.getString("minuto"));
				lista.add(lembrete);
			}
			con.close();
			return lista;
			
		} catch (Exception e) {
			System.out.println("LembreteDao | listarTodosLembretes: " + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Lembrete> listarTodosLembretesPorLogin(Integer idDono){
		ArrayList<Lembrete> lista = new ArrayList<Lembrete>();
		Lembrete lembrete;
		try {
			conectarBanco();
			stmt = con.prepareStatement("select * from lembrete where idDono = ?");
			stmt.setInt(1, idDono);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				lembrete = new Lembrete();
				lembrete.setId(rs.getInt("id"));
				lembrete.setTitulo(rs.getString("titulo"));
				lembrete.setTexto(rs.getString("texto"));
				lembrete.setAno(rs.getString("ano"));
				lembrete.setMes(rs.getString("mes"));
				lembrete.setDia(rs.getString("dia"));
				lembrete.setHora(rs.getString("hora"));
				lembrete.setMin(rs.getString("minuto"));
				lista.add(lembrete);
			}
			con.close();
			return lista;
			
		} catch (Exception e) {
			System.out.println("LembreteDao | listarTodosLembretes: " + e.getMessage());
			return null;
		}
	}
	
	
}
