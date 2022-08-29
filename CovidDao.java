package com.mindtree.dao;

import java.util.Date;
import java.util.List;

import com.mindtree.entity.Covid;

public interface CovidDao {

	List<String> findStates();
	
	List<String> findDistricts(String str);

	List<Covid> findByDate(String dt1, String dt2);
	
	List<Covid> compareDtState(String dt1, String dt2, String state1, String state2 );
	
	List<Covid> findAll();
	
	
	
	
}