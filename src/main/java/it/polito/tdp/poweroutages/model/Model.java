package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Nerc> nerci;
	private List<PowerOutages> po;
	private List<PowerOutages> best;
	int X;
	int Y;
	private int personeMigliore;
	private List<PowerOutages> poSelezionati;
	Nerc selezionatoNerc ;
	
	public Model() {
		podao = new PowerOutageDAO();
		nerci = new ArrayList<Nerc>(podao.getNercList());
		po = new ArrayList<PowerOutages>(podao.getPowerOutagesList());
		
		X=0;
		Y=0;
		personeMigliore = 0;
		
	}
	
	public List<Nerc> getNercList() {
		return nerci;
	}

	public List<PowerOutages> getPowerOutagesList() {
		return po;
	}
	
	public String trovaSequenza(Nerc nerc,Integer yy,Integer xx) {
		List<PowerOutages> parziale = new ArrayList();
		this.best = null;
		poSelezionati = new ArrayList<PowerOutages>(podao.getPowerOutagesperNerc(selezionatoNerc));
		PowerOutageDAO dao = new PowerOutageDAO();
		X=xx;
		Y=yy;
		// forse qualcosa quiiiii
		String tot="";
		
		cerca2(parziale,0,Y);
		tot="Tot people affected: "+calcolaPersone(best)+"\n";
		tot+="Tot hours of outage: "+calcolaOre(best)+"\n";
		for(PowerOutages p : best) {
			tot+=p.toString()+"\n";
		}
		return tot;
	}
	// CI METTE TROPPO , NON RIESCO A TROVARE UNA SOLUZIONE ....
	public void cerca(List<PowerOutages> parziale, int livello) {
		// CASO TERMINALE
		if(livello==Y) {
			int costoOre = calcolaOre(parziale);
			int persone = calcolaPersone(parziale);
			if(best==null || persone > calcolaPersone(best)) {
				best = new ArrayList<PowerOutages>(parziale);
			}
		}else {
			for(PowerOutages p : poSelezionati) {
				if(validaEvento(p,parziale)) {
					parziale.add(p);
					cerca(parziale,livello+p.getNumberHours());
					parziale.remove(parziale.size()-1);
				}
			}
		}
	}
	public void cerca2(List<PowerOutages> parziale, int livello,int y) {
		int oreTotali = calcolaOre(parziale);
		if(oreTotali > y) {
			return;
		}
		if(oreTotali == y) {
			int personeTotali = calcolaPersone(parziale);
			if(personeTotali > personeMigliore) {
				best = new ArrayList<PowerOutages>(parziale);
				personeMigliore= personeTotali;
			}
			return;
		}
		if(livello == poSelezionati.size()) {
			return;
		}
		parziale.add(poSelezionati.get(livello));
		cerca2(parziale,livello+poSelezionati.get(livello).getNumberHours(),y);
		
		parziale.remove(poSelezionati.get(livello));
		cerca2(parziale,livello+poSelezionati.get(livello).getNumberHours(),y);
	}
	
	public boolean validaEvento(PowerOutages powerOutages , List<PowerOutages> parziale) {
		
		for(PowerOutages p :parziale) {
			if(powerOutages.getYear()<(p.getYear()-X) || powerOutages.getYear()>(p.getYear()+X)) {
				return false;
			}
		}
		return true;
	}
	
	public int calcolaOre(List<PowerOutages> l) {
		int oreTot=0;
		
		for(PowerOutages p : l) {
			oreTot+=p.getNumberHours();
		}
		return oreTot;
	}
	public int calcolaPersone(List<PowerOutages> l) {
		int personeTot=0;
		
		for(PowerOutages p : l) {
			personeTot+=p.getCustomers_affected();
		}
		return personeTot;
	}

	public void daiNerc(Nerc value) {
		// TODO Auto-generated method stub
		selezionatoNerc =value;
	}
}
