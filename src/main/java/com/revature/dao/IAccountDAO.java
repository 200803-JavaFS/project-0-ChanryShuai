package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface IAccountDAO {
	
			public List<Account> getAllAccs();
			
			public boolean createAcc(Account acc);
			
			public boolean updateAcc(Account acc);
			
			public boolean deleteAcc(int userId);

			public Account getAccByID(int accID);
			
			public List<Account> getAccsByUser(String username);
			
		}




