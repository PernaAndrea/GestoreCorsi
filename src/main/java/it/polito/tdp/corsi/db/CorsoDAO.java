package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {
	
	//pattern nuovo che usiamo in anteprima 
	//creo la classe nel model
	public List<Corso> getCorsiByPeriodo(Integer periodo){
		// il pattern del metodo DAO e piu o meno sempre lo stesso
		//dopo aver provato la query su Sequel Pro le inserisco qui 
		String sql = "select * "
				+ "	from corso "
				+ "	where pd = ? ";
		//devo togliere \n e mettere uno spazio dopo ogni fine riga 
				
		//definisco la struttura dati da richiamare 
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);//1 sta a dire il primo parametro '?' nella query
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public Map<Corso,Integer> getIscrittiByPeriodo(Integer periodo){
		
		// il pattern del metodo DAO e piu o meno sempre lo stesso
				//dopo aver provato la query su Sequel Pro le inserisco qui 
				String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot "
						+ "FROM corso c, iscrizione i "
						+ "WHERE c.codins=i.codins AND c.pd=? "
						+ "GROUP BY c.codins, c.nome, c.crediti, c.pd ";//di solito nella group by si mettono tutti gli attributi citati nel select fino al count
				//devo togliere \n e mettere uno spazio dopo ogni fine riga 
						
				//definisco la struttura dati da richiamare 
				Map<Corso,Integer> result = new HashMap<Corso,Integer>();
				
				try {
					Connection conn = DBConnect.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);
					st.setInt(1, periodo);//1 sta a dire il primo parametro '?' nella query
					ResultSet rs = st.executeQuery();
					
					while(rs.next()) {
						Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
						Integer n = rs.getInt("tot");
						result.put(c, n);
					}
					
					rs.close();
					st.close();
					conn.close();
					
				}catch(SQLException e) {
					throw new RuntimeException(e);
				}
				
				return result;
		
	}
	
	public List<Studente> getStudentiByCorso(Corso corso){
		
		// il pattern del metodo DAO e piu o meno sempre lo stesso
		//dopo aver provato la query su Sequel Pro le inserisco qui 
		String sql = "SELECT s.matricola, s.nome, s.cognome,s.CDS FROM iscrizione i,studente s WHERE s.matricola = i.matricola AND i.codins = ?";//di solito nella group by si mettono tutti gli attributi citati nel select fino al count
		//devo togliere \n e mettere uno spazio dopo ogni fine riga 
				
		
		//definisco la struttura dati da richiamare 
		ArrayList<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());//1 sta a dire il primo parametro '?' nella query
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
				result.add(s);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}

	public boolean esisteCorso(Corso corso) {
		// TODO Auto-generated method stub
		String sql =" SELECT * FROM corso WHERE codins= ? ";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());//1 sta a dire il primo parametro '?' nella query
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				rs.close(); //DEVO CHIUDERE QUI TUTTI I FLUSSI PERCHE POI HO DELLE RETURN SUBITO DOPOOOOOO
				st.close();
				conn.close();
				return true;
			}else {
				rs.close();// LO STESSSO VALE QUI
				st.close();
				conn.close();
				return false;
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
