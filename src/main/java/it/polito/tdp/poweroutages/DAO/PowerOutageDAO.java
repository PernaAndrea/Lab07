package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.*;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutagesList() {

		String sql = "SELECT * "
				+ "FROM PowerOutages";
		
		List<PowerOutages> powerOutagesList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalDateTime db = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime df = res.getTimestamp("date_event_finished").toLocalDateTime();
				PowerOutages p = new PowerOutages(res.getInt("id"),res.getInt("event_type_id"),res.getInt("tag_id"),res.getInt("area_id"),
						res.getInt("nerc_id"),res.getInt("responsible_id"),res.getInt("customers_affected"),db,
						df,res.getInt("demand_loss"));
				powerOutagesList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutagesList;
	}
	
	public List<PowerOutages> getPowerOutagesperNerc(Nerc nerc){
		
		String sql = "SELECT * "
				+ "FROM PowerOutages "
				+ "WHERE nerc_id=?";
		
		List<PowerOutages> powerOutagesList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalDateTime db = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime df = res.getTimestamp("date_event_finished").toLocalDateTime();
				PowerOutages p = new PowerOutages(res.getInt("id"),res.getInt("event_type_id"),res.getInt("tag_id"),res.getInt("area_id"),
						res.getInt("nerc_id"),res.getInt("responsible_id"),res.getInt("customers_affected"),db,
						df,res.getInt("demand_loss"));
				powerOutagesList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutagesList;
	}
	
}
