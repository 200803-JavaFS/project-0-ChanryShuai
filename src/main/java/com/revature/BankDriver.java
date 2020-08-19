package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.utils.ConnectionUtil;
import com.revature.utils.ConsoleUtil;

public class BankDriver {
	public static void main(String[] args) { 
		
		Logger log = LogManager.getRootLogger();
		Connection con = null;
		try {
			con = ConnectionUtil.getConnection();
			log.info(con.getMetaData().getDriverName());
		} catch (SQLException e) {
			log.error(e);
		}

		ConsoleUtil start = new ConsoleUtil();
		start.beginApp();
	}
}
