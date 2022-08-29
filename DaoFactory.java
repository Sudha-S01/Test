package com.mindtree.dao;

import com.mindtree.dao.impl.CovidDaoJDBC;
import com.mindtree.db.DB;

public class DaoFactory {

	
	public static CovidDao createCovidDao() {
		return new CovidDaoJDBC(DB.getConnection());
	}
}