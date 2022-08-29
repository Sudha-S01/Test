package com.mindtree.dao.impl;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.mindtree.db.DB;
import com.mindtree.db.DbException;
import com.mindtree.entity.Covid;
import com.mindtree.dao.CovidDao;


public class CovidDaoJDBC implements CovidDao{

	private Connection conn;
	
	public CovidDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<String> findStates() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT State FROM covid_data");
						
			rs = st.executeQuery();
			List<String> listStates = new ArrayList<String>();
			while (rs.next()) {
				listStates.add(rs.getString("State"));				
			}
			return listStates;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	
	@Override
	public List<String> findDistricts(String str) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT District FROM covid_data where State = ?");
			
			st.setString(1, str);
			rs = st.executeQuery();
			List<String> listDistrict = new ArrayList<String>();
			while (rs.next()) {
				listDistrict.add(rs.getString("District"));
				
			}
			return listDistrict;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Covid> findByDate(String FromDt, String ToDt) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT date,state,FORMAT(TRIM(TRAILING '0' from SUM(confirmed)),0) as confirmed FROM covid_data where date between ? and ? group by date,state order by date");
			
			st.setString(1, FromDt);
			st.setString(2, ToDt);
			rs = st.executeQuery();
			
			List<Covid> listConfirmed = new ArrayList<>();
			while (rs.next()) {
				Covid cov = new Covid();
				cov.setState(rs.getString("State"));
				cov.setDate(rs.getDate("date"));
				cov.setConfirmed(rs.getString("Confirmed"));
				listConfirmed.add(cov);
				
			}
			return listConfirmed;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
		
	@Override
	public List<Covid> compareDtState(String FromDt, String ToDt, String FirstState, String SecState) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
					
			st = conn.prepareStatement("select t1.date, t1.state as firstState,FORMAT(t1.confirmed,0) as firstConfirmed, t2.state as secState, FORMAT(t2.confirmed,0) as secConfirmed from " 
					+ "(select date,state,sum(confirmed) as confirmed from covid_data where date between ? and ? and state =? group by date, state) as t1 join " 
					+ "(select date,state,sum(confirmed) as confirmed from covid_data where date between ? and ? and state =? group by date, state) as t2 where t1.date = t2.date order by t1.date");
		
			st.setString(1, FromDt);
			st.setString(2, ToDt);
			st.setString(3, FirstState);				
			st.setString(4, FromDt);
			st.setString(5, ToDt);			
			st.setString(6, SecState);	
			rs = st.executeQuery();
			List<Covid> listCompareDtState = new ArrayList<>();
			while (rs.next()) {
				Covid cov = new Covid();
				cov.setDate(rs.getDate("date"));
				cov.setfirstState(rs.getString("firstState"));
				cov.setfirstConfirmed(""+rs.getInt("firstConfirmed"));
				cov.setsecState(rs.getString("secState"));
				cov.setsecConfirmed(""+rs.getInt("secConfirmed"));
								
				listCompareDtState.add(cov);
				
			}
			return listCompareDtState;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	
	@Override
	public List<Covid> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM covid_data ORDER BY Id");
			rs = st.executeQuery();

			List<Covid> list = new ArrayList<>();

			while (rs.next()) {
				Covid cov = new Covid();
				cov.setId(rs.getInt("Id"));
				cov.setState(rs.getString("State"));
				list.add(cov);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
