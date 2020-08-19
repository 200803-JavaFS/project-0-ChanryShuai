package com.revature.dao;

import java.math.BigDecimal;
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

public class AccountDAO implements IAccountDAO{

	
	@Override
	public List<Account> getAllAccs() {
		
		try (Connection con = ConnectionUtil.getConnection()) {
		
			String sql = "SELECT * FROM accounts";
			List<Account> accList = new ArrayList<>();

			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(sql);

			while (result.next()) {

				Account a = new Account();
				int accID = result.getInt("accID");
				String accType = result.getString("accType");
				float balance = result.getFloat("accBalance");
				Boolean approved = result.getBoolean("accStatus");
				String userFK = result.getString("user_fk");
				
				a.setAccID(accID);
				a.setAccType(accType);
				a.setBalance(balance);
				a.setApproved(approved);
				a.setUserFK(userFK);

				accList.add(a);
			}
			return accList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public boolean createAcc(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO accounts (accBalance, accType, accStatus, user_fk)"
					+ "VALUES (?, ?, ?, ?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setFloat(++index, a.getBalance());
			statement.setString(++index, a.getAccType());
			statement.setBoolean(++index, a.isApproved());
			statement.setString(++index, a.getUserFK());

			statement.execute();
			return true;
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean updateAcc(Account a) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts SET accID = ?, accBalance = ?, accType = ?, "
					+ "accStatus = ?, user_fk = ? WHERE accID = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setInt(++index, a.getAccID());
			statement.setFloat(++index, a.getBalance());
			statement.setString(++index, a.getAccType());
			statement.setBoolean(++index, a.isApproved());
			statement.setString(++index, a.getUserFK()); 
			statement.setInt(++index, a.getAccID());
			
			statement.execute();
			return true;
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteAcc(int accID) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM accounts WHERE accID =" + accID + ";";

			Statement statement = conn.createStatement();

			statement.execute(sql);
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}



	@Override
	public Account getAccByID(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE accID =" + id + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
			 Account a = new Account(result.getInt("accID"), result.getFloat("accBalance"), 
					 result.getString("accType"), result.getBoolean("accStatus"), result.getString("user_fk"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Account> getAccsByUser(String username) {
		try (Connection con = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM accounts WHERE user_fk = '" + username +"';";
			List<Account> accList = new ArrayList<>();

			Statement s = con.createStatement();
			ResultSet result = s.executeQuery(sql);

			while (result.next()) {

				Account a = new Account();
				int accID = result.getInt("accID");
				String accType = result.getString("accType");
				float balance = result.getFloat("accBalance");
				Boolean approved = result.getBoolean("accStatus");
				String userFK = result.getString("user_fk");
				
				a.setAccID(accID);
				a.setAccType(accType);
				a.setBalance(balance);
				a.setApproved(approved);
				a.setUserFK(userFK);

				accList.add(a);
			}
			return accList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}







	

}
