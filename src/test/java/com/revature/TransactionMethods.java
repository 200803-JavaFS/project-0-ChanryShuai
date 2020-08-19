package com.revature;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;

public class TransactionMethods {

	private static final Scanner sc = new Scanner(System.in);

	public void Deposit(Account a, float amount) {

		System.out.println("Please enter the accID you are depositting into: ");
		int x = a.getAccID();
		System.out.println(x);

		if (a.isApproved() == false) {
			System.out.println("Sorry, account is closed.");
		}

		float balance = a.getBalance();

		System.out.println("Please enter an amount to deposit: ");
		System.out.println(amount);

		if (amount > 0) {
			balance += amount;
			a.setBalance(balance);
			System.out.println("You have successfully deposited " + amount + ". " + "Your balance is now: " + balance);
		} else {
			System.out.println("Please deposit a positive amount.");
		}
		System.out.println("Your new account info: ");
		System.out.println(a);
	}

	public void Withdraw(Account a, float amount) {

		System.out.println("Please enter the accID you are withdrawing from: ");
		int x = a.getAccID();
		System.out.println(x);

		float balance = a.getBalance();

		System.out.println("Please enter amount to withdraw: ");
		System.out.println(amount);

		if (amount > 0) {
			if (balance >= amount) {
				balance -= amount;
				a.setBalance(balance);
				System.out.println("You have withdrawn " + amount + ". " + "Your balance is now: " + balance);
			} else {
				System.out.println("Sorry, withdrawl can't be processed due to insufficient funds.");
			}

		} else {
			System.out.println("Please enter a positive amount to withdrawl.");
		}

		System.out.println("Your new account info: ");
		System.out.println(a);

	}

	public void Transfer(Account a, Account b, Float amount) {

		System.out.println("Please enter the accID you are transferring from: ");
		int x = a.getAccID();
		System.out.println(x);
		
		System.out.println("Please enter the accID you are transferring to: ");
		int y = b.getAccID();
		System.out.println(y);
		
		float fbalance = a.getBalance();
		float tbalance = b.getBalance();

		System.out.println("Enter the amount you would like to transfer: ");
		System.out.println(amount);

		if (fbalance >= amount) {
			
			fbalance -= amount;
			tbalance += amount;
			a.setBalance(fbalance);
			b.setBalance(tbalance);
			System.out.println("You have successfully transferred" + amount + " from account " + a.getAccID() + " to "
					+ b.getAccID());
		}

		System.out.println("Your new accounts' info: ");
		System.out.println(a);
		System.out.println(b);

	}

}
