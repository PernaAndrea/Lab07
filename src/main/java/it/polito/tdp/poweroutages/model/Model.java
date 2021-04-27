package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Nerc> nerci;
	private List<PowerOutages> po;
	private List<PowerOutages> best;
	
	public Model() {
		podao = new PowerOutageDAO();
		nerci = new ArrayList<Nerc>(podao.getNercList());
		po = new ArrayList<PowerOutages>(podao.getPowerOutagesList());
	}
	
	public List<Nerc> getNercList() {
		return nerci;
	}

	public List<PowerOutages> getPowerOutagesList() {
		return po;
	}
	
	public List<PowerOutages> trovaSequenza(Nerc nerc) {
		List<PowerOutages> parziale = new ArrayList();
		this.best = null;
		
		PowerOutageDAO dao = new PowerOutageDAO();
		
		// forse qualcosa quiiiii
		
		cerca(parziale,0);
		return best;
	}
	
	public void cerca(List<PowerOutages> parziale, int livello) {
		
	}
	
	public boolean validaEvento(PowerOutages powerOutages , List<PowerOutages> parziale) {
		
		return false;
	}
	
	public String calcolaDati(List<PowerOutages> parziale) {
		
		return null;
	}
}
