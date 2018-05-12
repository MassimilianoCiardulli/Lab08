/**
 * Sample Skeleton for 'DizionarioGraph.fxml' Controller Class
 */

package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtNumero"
    private TextField txtNumero; // Value injected by FXMLLoader

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="btnGenera"
    private Button btnGenera; // Value injected by FXMLLoader

    @FXML // fx:id="btnVicini"
    private Button btnVicini; // Value injected by FXMLLoader

    @FXML // fx:id="btnGradoMax"
    private Button btnGradoMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader
    
    private Model model ;

    @FXML
    void handleGeneraGrafo(ActionEvent event) {
    	int lunghezza = 0;
    	try {
    		lunghezza = Integer.parseInt(txtNumero.getText());
    		model.createGraph(lunghezza);
    	} catch(Exception e) {
    		this.txtArea.setText("Inserire un numero!");
    	}
    	
    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.txtArea.clear();
    	this.txtNumero.clear();
    	this.txtParola.clear();
    }

    @FXML
    void handleTrovaGradoMax(ActionEvent event) {
    	try {
    		List<String> listaParole = model.neighborMaxDegree();
	    	this.txtArea.clear();
	    	this.txtArea.setText("Il grado massimo è: "+listaParole.size()+"\n");
	    	this.txtArea.appendText("Il vertice di grado massimo è: "+listaParole.get(0)+"\n");
	    	this.txtArea.appendText("I vicini sono:\n");
	    	
	    	for(int i = 1; i < listaParole.size(); i++) {
	    		this.txtArea.appendText(listaParole.get(i)+"\n");
	    	}
    	} catch(Exception e) {
    		this.txtArea.setText("Ops! Qualcosa è andato storto...");
    	}
    	
    }

    @FXML
    void handleTrovaVicini(ActionEvent event) {
    	try {
    		int lunghezza = Integer.parseInt(this.txtNumero.getText());
    		String parola = this.txtParola.getText();
	    	if(parola.length()!=lunghezza) {
	    		txtArea.setText("Inserire una parola della stessa lunghezza specificata");
	    		return ;
	    	}
	    	List<String> listaParole = model.displayNeighbours(parola);
	    	for(String s:listaParole)
    		this.txtArea.appendText(s + "\n");
    	} catch(Exception e) {
    		this.txtArea.setText("Ops! Qualcosa è andato storto...");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model ;
	}
}
