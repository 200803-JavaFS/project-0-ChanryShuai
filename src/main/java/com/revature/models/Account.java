package com.revature.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	private int accID;
	private float balance; 
	private String accType;
	private boolean approved; 
	private String userFK;
	
	public Account() {
		super();
	}

	public Account(float balance, String accType, boolean approved, String userFK) {
		super();
		this.balance = balance;
		this.accType = accType;
		this.approved = approved;
		this.userFK = userFK;
	}

	public Account(int accID, float balance, String accType, boolean approved, String userFK) {
		super();
		this.accID = accID;
		this.balance = balance;
		this.accType = accType;
		this.approved = approved;
		this.userFK = userFK;
	}
	
	
	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getUserFK() {
		return userFK;
	}

	public void setUserFK(String userFK) {
		this.userFK = userFK;
	} 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accID;
		result = prime * result + ((accType == null) ? 0 : accType.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((userFK == null) ? 0 : userFK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accID != other.accID)
			return false;
		if (accType == null) {
			if (other.accType != null)
				return false;
		} else if (!accType.equals(other.accType))
			return false;
		if (approved != other.approved)
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (userFK == null) {
			if (other.userFK != null)
				return false;
		} else if (!userFK.equals(other.userFK))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Account [accID=" + accID + ", balance=" + balance + ", accType=" + accType + ", approved=" + approved
				+ ", userFK=" + userFK + "]";
	}
}