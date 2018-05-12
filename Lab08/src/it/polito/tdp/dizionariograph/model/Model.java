package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private List<String> parole ;
	private Graph<String, DefaultEdge> graph; //= new Multigraph<>();
	
	
	public void createGraph(int numeroLettere) {
		//1) leggi la lista di parole dal DB
		WordDAO dao = new WordDAO();
		parole = dao.getAllWordsFixedLength(numeroLettere);
		
		//2) crea il grafo
		graph = new Multigraph<String, DefaultEdge>(DefaultEdge.class);
		
		//3) aggiungi i vertici
		Graphs.addAllVertices(this.graph, this.parole);
		
		//4) aggiungi gli archi
		for(String sp:parole) {
			for(String sa:parole) {
				if(!sp.equals(sa)) {
					if(differiscePerUnaLettera(sp,sa)) {
						this.graph.addEdge(sp, sa);
						System.out.format("(%s, %s)\n", sp, sa);
					}
				}
			}
		}
	}

	private boolean differiscePerUnaLettera(String sp, String sa) {
		
		int count = 0;
		for(int i = 0; i < sp.length(); i++) {
			if(sp.charAt(i)!=sa.charAt(i))
				count++;
		}
		if(count == 1)
			return true;
		return false;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		List<String> neighbor = new ArrayList<>();
		if(this.graph.containsVertex(parolaInserita)) {
			neighbor = Graphs.neighborListOf(this.graph, parolaInserita);
		}
		
		return neighbor;
	}

	public int findMaxDegree() {
		
			
		return -1;
	}

	public List<String> neighborMaxDegree() {
		int max = 0;
		String wMax = null;
		List<String> neighbor = new ArrayList<>();
		
		for(String s:graph.vertexSet()) {
			if(this.graph.degreeOf(s) > max) {
				max = this.graph.degreeOf(s);
				wMax = s;
			}
		}
		neighbor.add(wMax);
		neighbor.addAll(this.displayNeighbours(wMax));
		return neighbor;
	}
}
