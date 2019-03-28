package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	public List<Studente> getTuttiStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");

				System.out.println(matricola + " " + cognome + " " + nome + " " + cds);
				
				Studente s = new Studente(matricola, cognome,nome,cds);
				studenti.add(s);
				
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public Studente getStudente(int matricola) {
		
		final String sql= "select * from studente where matricola = ? ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				Studente s = new Studente(matricola, cognome, nome, "null");
				System.out.println("nome: "+nome+" cognome: "+cognome);
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		final String sql= "select c.nome, c.codins\n" + 
				"from studente s,iscrizione i,corso c\n" + 
				"where s.matricola = i.matricola\n" + 
				"and c.codins = i.codins\n" + 
				"and s.matricola= ? ";
		
		List<Corso> listacorsi= new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
				String codins = rs.getString("codins");
				String nome = rs.getString("nome");
				
				Corso c = new Corso(codins, 0, nome, 0);
				listacorsi.add(c);
			}
			return listacorsi;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public boolean getStudenteIscrittoCorso(int matricola, String nomecorso) {
		final String sql= "select s.matricola, c.nome\n" + 
				"from studente s,iscrizione i,corso c\n" + 
				"where s.matricola = i.matricola\n" + 
				"and c.codins = i.codins\n" + 
				"and s.matricola= ? \n" + 
				"and c.nome = ? \n" + 
				"";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, nomecorso);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	
}
