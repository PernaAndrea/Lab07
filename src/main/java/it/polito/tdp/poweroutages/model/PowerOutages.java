package it.polito.tdp.poweroutages.model;

import java.util.Date;

public class PowerOutages {
	
	private int id;
	private int event_type_id;
	private int tag_id;
	private int area_id;
	private int nerc_id;
	private int responsible_id;
	private int customers_affected;
	private Date date_event_began;
	private Date date_event_finished;
	private int demand_loss;
	private int numberHours;
	private int year;
	
	@SuppressWarnings("deprecation")
	public PowerOutages(int id, int event_type_id, int tag_id, int area_id, int nerc_id, int responsible_id,
			int customers_affected, Date date_event_began, Date date_event_finished, int demand_loss) {
		super();
		this.id = id;
		this.event_type_id = event_type_id;
		this.tag_id = tag_id;
		this.area_id = area_id;
		this.nerc_id = nerc_id;
		this.responsible_id = responsible_id;
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.demand_loss = demand_loss;
		this.year = date_event_began.getYear();
		this.numberHours = date_event_finished.getHours()-date_event_began.getHours();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNerc_id() {
		return nerc_id;
	}

	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}

	public Date getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(Date date_event_began) {
		this.date_event_began = date_event_began;
	}

	public Date getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(Date date_event_finished) {
		this.date_event_finished = date_event_finished;
	}

	public int getNumberHours() {
		return numberHours;
	}

	public void setNumberHours(int numberHours) {
		this.numberHours = numberHours;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PowerOutages [customers_affected=" + customers_affected + ", date_event_began=" + date_event_began
				+ ", date_event_finished=" + date_event_finished + ", numberHours=" + numberHours + ", year=" + year
				+ "]";
	}

	
	
	
	
}
