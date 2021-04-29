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
	private List<PowerOutages> partenza;
	
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
	
	public String trovaSequenza(Nerc nerc,Integer ore,Integer anni) {
		
		List<PowerOutages> parziale = new ArrayList();
		this.best = null;
		personeMigliore = 0;
		poSelezionati = new ArrayList<PowerOutages>(podao.getPowerOutagesperNerc(selezionatoNerc));
		PowerOutageDAO dao = new PowerOutageDAO();
		partenza = this.podao.getPowerOutagesperNerc(nerc);
		X=anni;
		Y=ore;
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
	/*
	 * 
	 * 
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
	
	*/
	public void cerca2(List<PowerOutages> parziale, int livello,int y) {
		
		int oreTotali = calcolaOre(parziale);
		
		if(oreTotali > y) {
			return;
		}
		
			int personeTotali = calcolaPersone(parziale);
			if(personeTotali > personeMigliore) {
				best = new ArrayList<PowerOutages>(parziale);
				personeMigliore= personeTotali;
			
			return;
		}
		
		if(livello == partenza.size()) {
			return;
		}else {
			PowerOutages nuovoPo = partenza.get(livello);
			if(parziale.size()==0||nuovoPo.getDate_event_finished().compareTo(parziale.get(0).getDate_event_began().plusYears(X))<=0) {
				parziale.add(nuovoPo);
				cerca2(parziale,livello+1,y);
				parziale.remove(nuovoPo);
				cerca2(parziale,livello+1,y);
			}else {
				return;
			}
		}
		/*
		parziale.add(poSelezionati.get(livello));
		cerca2(parziale,livello+poSelezionati.get(livello).getNumberHours(),y);
		
		parziale.remove(poSelezionati.get(livello));
		cerca2(parziale,livello+poSelezionati.get(livello).getNumberHours(),y);
		*/
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
