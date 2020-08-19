package com.revature;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.models.User;

public class BankTest {
	public static TransactionMethods tm;
	public static Account a;
	public static Account b;
	
	public static User user1;
	public static User admin;
	public static User emp;
	
	public static float i;
	public static float j;
	public static float k;
	
	@BeforeClass
	public static void setTest() {
		System.out.println("In BeforeClass");
		tm = new TransactionMethods();
		
	}

	@Before
	public void setAmount() {
		System.out.println("In Before");
		a = new Account(1, 100, "checking", true, "user1");
		b = new Account(2, 100, "saving", true, "user2");
		
		user1 = new User(1, "username", "password", "fFname", "Lname", "client");
		admin = new User(2, "admin", "admin_pass", "Business", "Administrator", "administrator");
		emp = new User(3, "employee", "emp_pass", "Bank", "Employee", "aemployee");
		
		i = 50;
		j = 200;
		k = -50;
	}

	@After
	public void clearResult() {
		System.out.println("In After");
		a = new Account(1, 100, "checking", true, "user1");
		b = new Account(2, 100, "checking", true, "user2");
		
		user1 = new User(1, "username", "password", "fFname", "Lname", "client");
		admin = new User(2, "admin", "admin_pass", "Business", "Administrator", "administrator");
		emp = new User(3, "employee", "emp_pass", "Bank", "Employee", "aemployee");
		
		i = 50;
		j = 200;
		k = -50;
	}

	@AfterClass
	public static void clearTest() {
		System.out.println("In AfterClass");
		user1 = null;
		admin = null;
		emp = null;
		a = null;
		b = null;
		i=0;
		j=0;
		
	}

	@Test
	public void testDeposit() {
		System.out.println("Testing deposit method: ");
		System.out.println("deposit to   : " + a);
		System.out.println("deposit mount: "+ i);
		tm.Deposit(a, i);
		assertTrue(a.equals(new Account(1, 150, "checking", true, "user1")));
	}
	@Test
	public void testDepositNeg() {
		System.out.println("Testing deposit negative amount method: ");
		System.out.println("deposit to   : " + a);
		System.out.println("deposit mount: "+ k);
		tm.Deposit(a, k);
		assertTrue(a.equals(new Account(1, 100, "checking", true, "user1")));
	}

	@Test
	public void testWithdraw() {
		System.out.println("Testing withdraw method:");
		System.out.println("withdraw from  : " + a);
		System.out.println("withdraw amount: "+ i);
		tm.Withdraw(a,i);
		assertTrue(a.equals(new Account(1, 50, "checking", true, "user1")));
		}
	
	@Test
	public void testWithdrawNeg() {
		System.out.println("Testing withdraw negative amount method:");
		System.out.println("withdraw from  : " + a);
		System.out.println("withdraw amount: "+ k);
		tm.Withdraw(a,k);
		assertTrue(a.equals(new Account(1, 100, "checking", true, "user1")));
		}
	
	@Test
	public void testOverdraw() {
		System.out.println("Testing overdraw amount:");
		System.out.println("withdraw from  : " + a);
		System.out.println("withdraw amount: "+ j);
		tm.Withdraw(a,j);
		assertTrue(a.equals(new Account(1, 100, "checking", true, "user1")));
		}

	@Test
	public void testTransfer() {
		System.out.println("Testing transfer method:");
		System.out.println("transfer from  : " + a);
		System.out.println("transfer to    : " + b);
		System.out.println("transfer amount: "+ i);
		
		System.out.println("deposit mount: "+ i);
		tm.Transfer(a, b, i);
			assertTrue(a.equals(new Account(1, 50, "checking", true, "user1")));
			assertTrue(a.equals(new Account(2, 150, "saving", true, "user2")));
			
	}
	
	@Test
	public void testTransferNeg() {
		System.out.println("Testing transfer negative amount method:");
		System.out.println("transfer from  : " + a);
		System.out.println("transfer to    : " + b);
		System.out.println("transfer amount: "+ k);
		
		System.out.println("deposit mount: "+ k);
		tm.Transfer(a, b, k);
			assertTrue(a.equals(new Account(1, 100, "checking", true, "user1")));
			assertTrue(a.equals(new Account(2, 100, "saving", true, "user2")));
			
		
	}
}
