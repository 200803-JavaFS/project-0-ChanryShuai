package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAO implements IUserDAO {

	private IAccountDAO aDao = new AccountDAO();

	@Override
	public List<User> getAllUsers() {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users";
			List<User> userList = new ArrayList<>();

			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(sql);

			while (result.next()) {

				User u = new User();
				int userID = result.getInt("userID");
				String username = result.getString("username");
				String password = result.getString("password"); 
				String firstname = result.getString("first_name");
				String lastname = result.getString("last_name");
				String type = result.getString("user_type");

				u.setUserID(userID);
				u.setUsername(username);
				u.setPassword(password);
				u.setFirstname(firstname);
				u.setLastname(lastname);
				u.setType(type);
				userList.add(u);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByID(int userID) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE userID = " + userID + ";";

			Statement statement = conn.createStatement();
 
			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				User u = new User(result.getInt("userID"), result.getString("username"), result.getString("password"),
						result.getString("first_name"), result.getString("last_name"), result.getString("user_type"));

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createUser(User a) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO users (username, password, first_name, last_name, user_type)"
					+ "VALUES (?, ?, ?, ?, ?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, a.getUsername());
			statement.setString(++index, a.getPassword());
			statement.setString(++index, a.getFirstname());
			statement.setString(++index, a.getLastname());
			statement.setString(++index, a.getType());

			statement.execute();
			
	
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE users SET username = ?, password = ?, first_name= ?, "
					+ "last_name = ?, account_fk = ? WHERE userID = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setInt(++index, a.getUserID());
			statement.setString(++index, a.getUsername());
			statement.setString(++index, a.getPassword());
			statement.setString(++index, a.getFirstname());
			statement.setString(++index, a.getLastname());
			statement.setString(++index, a.getType());
			statement.setInt(++index, a.getUserID());
			
			statement.execute();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteUser(int userID) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM users WHERE userID = " + userID + ";";

			Statement statement = conn.createStatement();

			statement.execute(sql);
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}

	@Override
	public User getUserByName(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = '" + username + "';";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				User u = new User(result.getInt("userID"), result.getString("username"),
						result.getString("password"), result.getString("first_name"),
						result.getString("last_name"), result.getString("user_type"));
			return u;}
			} catch (SQLException e) {
			e.printStackTrace();
			}
		return null;
		}

	@Override
	public List<User> getAllClients() {
		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users WHERE user_type = 'client';";
			List<User> userList = new ArrayList<>();

			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(sql);

			while (result.next()) {

				User u = new User();
				int userID = result.getInt("userID");
				String username = result.getString("username");
				String password = result.getString("password");
				String firstname = result.getString("first_name");
				String lastname = result.getString("last_name");
				String type = result.getString("user_type");

				u.setUserID(userID);
				u.setUsername(username);
				u.setPassword(password);
				u.setFirstname(firstname);
				u.setLastname(lastname);
				u.setType(type);
				userList.add(u);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	}
