package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	//il modello usera il metodo del DAO per dare i valori
	private CorsoDAO corsoDao;
	
	
	public Model() {
		
		corsoDao = new CorsoDAO();
	}
	
	
	public List<Corso> getCorsiByPeriodo(Integer pd){
		return corsoDao.getCorsiByPeriodo(pd);
	}
	
	public Map<Corso,Integer> getIscrittiByPeriodo(Integer pd){
		return corsoDao.getIscrittiByPeriodo(pd);
	}
	public List<Studente> getStudentiByCorso(String codice){
		return corsoDao.getStudentiByCorso(new Corso(codice,null,null,null));
	}

	//posso addirittura non scrivere niente del dao per l ultima richiesta e creare un metodo qui di sotto un po particolare
	public Map<String,Integer> getDivisioneCDS (String codice) {
		//cosa ci aspettiamo 
		//dato il corso ABC
		//GEST --> 50
		//INF --> 20
		//MEC --< 35
		Map<String,Integer> divisione = new HashMap<String,Integer>();
		List<Studente> studenti = this.getStudentiByCorso(codice);
		for(Studente s : studenti) {
			if(s.getCDS()!=null && !s.getCDS().equals("")) {
			if(divisione.get(s.getCDS())==null) {
				divisione.put(s.getCDS(),1);
			}else {
				divisione.put(s.getCDS(), divisione.get(s.getCDS()+1));
				}//nel git di monge è stato fatto un altro metoso nel corso dao con un altra query sql
			}
		}
		return divisione;
	}
	public boolean esisteCorso(String codice) {
		// TODO Auto-generated method stub
		return corsoDao.esisteCorso(new Corso(codice,null,null,null));
	}
}
