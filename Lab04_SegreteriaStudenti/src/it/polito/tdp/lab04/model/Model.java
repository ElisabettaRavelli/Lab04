package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

//il model deve fare da ponte tra DAO e il controller quindi qui devo implementare tutti i metodi 
//devo chiamare nel controller
public class Model {
	
	private CorsoDAO dao = new CorsoDAO();
	private StudenteDAO daos = new StudenteDAO();
	
	
	
	public List<Corso> getCorsi(){
		return dao.getTuttiICorsi();
	}

	public List<Studente> getStudenti(){
		return daos.getTuttiStudenti();
	}
	
	public Studente getStudente(int matricola) {
		return daos.getStudente(matricola);
	}
	
	public List<Studente> getIscritti(String nomecorso) {
		return dao.getStudentiIscrittiAlCorso(nomecorso);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return daos.getCorsiStudente(matricola);
	}
	
	public boolean getStudenteIscrittoCorso(int matricola, String nomecorso) {
		return daos.getStudenteIscrittoCorso(matricola, nomecorso);
	}
	
}
