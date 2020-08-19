package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.models.User;

public interface IUserDAO {
	
		public List<User> getAllUsers();
		
		public List<User> getAllClients();
		
		public User getUserByID(int id);
		
		public User getUserByName(String username);
		
		public boolean createUser(User user);
		
		public boolean updateUser(User user);
		
		public boolean deleteUser(int userId);
		
		
	}



