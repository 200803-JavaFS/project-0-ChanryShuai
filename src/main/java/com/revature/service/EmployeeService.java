package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.User;

public class EmployeeService {
	
	private static IUserDAO udao = new UserDAO();
	private static IAccountDAO aDao = new AccountDAO();
	private static final Logger log = LogManager.getLogger(EmployeeService.class);
	
	private String name;
	private String password;
	private float balance; 
	
	public List<User> getAllUsers() {
			log.info("Retrieving all users...");
			List<User> list = udao.getAllUsers();
			
			return list;
		}

	public User getUserByID(int userID) {
		log.info("Retreiving user info with userID "+ userID);
		return udao.getUserByID(userID);	
	}

	public List<User> getAllClients() {
	
		log.info("Retrieving all users...");
		List<User> list = udao.getAllClients();
		
		return list;		
	}

}
