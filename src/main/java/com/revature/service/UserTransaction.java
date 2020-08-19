package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;

public class UserTransaction{
	
	
	private static IUserDAO uDao = new UserDAO();
	private static IAccountDAO aDao = new AccountDAO();
	private static final Logger log = LogManager.getLogger(UserTransaction.class);
	

	public List<Account> getAllAccs() {
		log.info("Retrieving all accounts...");
		List<Account> list = aDao.getAllAccs();
		return list;
	} 

	public Account getAccByID(int accID) {
		log.info("Retreiving account info with accID: "+ accID);
		Account a = aDao.getAccByID(accID);
		return a;
	}

	public boolean updateAcc(Account acc) {
		log.info("Updating Account: "+acc);
		if(aDao.updateAcc(acc)) {
			return true;
		}
		return false;	
	}

	public boolean deleteAcc(Account acc) {
		log.info("Canceling Account: "+acc);
		if(aDao.deleteAcc(acc.getAccID())) {
			return true;
		}
		return false;		
	}




}
