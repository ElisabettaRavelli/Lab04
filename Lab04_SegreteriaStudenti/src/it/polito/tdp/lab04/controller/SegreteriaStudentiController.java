package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> menu;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox boxCheck;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

	private Model model;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    //l'utente inserisce una matricola e devo stampare tutti i corsi a cui è iscritta questa matricola
    	txtResult.clear();
    	
    	if(menu.getValue()==null && txtMatricola.getText()!=null) {
    	
    	List<Corso> l = model.getCorsiStudente(Integer.parseInt(txtMatricola.getText()));
    	if( l == null) {
    		txtResult.appendText("ERRORE\n");
    	} else if ( l.isEmpty()) {
    		txtResult.appendText("Matricola non esistente\n");
    	} else {
    		for(Corso c : l) 
    			txtResult.appendText(c.getCodins()+" "+c.getNome()+"\n");		
    		}
    	}
    	
    	else if(menu.getValue()!= null && txtMatricola.getText()!= null){
    		
    		if(model.getStudenteIscrittoCorso((Integer.parseInt(txtMatricola.getText())), menu.getValue())==true)
    			txtResult.appendText("Studente già iscritto al corso\n");
    		
    			else if (model.getStudenteIscrittoCorso((Integer.parseInt(txtMatricola.getText())), menu.getValue())==false)
    			txtResult.appendText("Lo studente non è iscritto al corso");
    	}
    }
    		
    

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtResult.clear();
    	//dobbiamo arrivare a poter chiamare model.getIscritti(menu.getValue());
    	
    	for(Studente s: model.getIscritti(menu.getValue())) {
    		txtResult.appendText(s.getMatricola()+ " "+s.getNome()+" "+s.getCognome()+"\n");
    	}
    	
    	
    }

    @FXML
    void doCheck(ActionEvent event) {
    	
    	//facendo clik sul bottone verde devo inserire il nome e il cognome della matricola inserita
//    	String matricolaS = txtMatricola.getText();
//    	int matricola = Integer.parseInt(matricolaS);
//    	model.getStudente(matricola);
    	Studente s = model.getStudente(Integer.parseInt(txtMatricola.getText()));
    	if (s!= null) {
	    	txtNome.appendText(s.getNome());
	    	txtCognome.appendText(s.getCognome());
    	} else txtResult.appendText("La matricola inserita non è valida\n");
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doMenu(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();

    }

    @FXML
    void initialize() {
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert boxCheck != null : "fx:id=\"boxCheck\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model m) {
		this.model = m;
		menu.getItems().add(""); //aggiungo un campo vuoto
		for(Corso c: model.getCorsi()) {
			menu.getItems().add(c.getNome());
		}
	
		
	}
}
