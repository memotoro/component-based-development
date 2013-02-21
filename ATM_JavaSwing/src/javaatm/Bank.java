/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaatm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cuong Tran
 */
public class Bank {
    private List<Account> accounts;
    
    public Bank () {
        try {
            // Initialize the bank account list
            this.accounts = new ArrayList<Account>();
            // Create new account
            Account acc1 = new Account();
            // Set account number
            acc1.setAccountNo("1111");
            // Set initial balance
            acc1.setBalance(1000);
            // Create new account
            Account acc2 = new Account();
            // Set account number
            acc2.setAccountNo("2222");
            // Set initial balance
            acc2.setBalance(2000);
            // Add account to the list
            this.accounts.add(acc1);
            this.accounts.add(acc2);
        } catch (Exception ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdraw ( String accno, Integer amount ) throws Exception {
        // Get the account
        Account account = this.retrieve(accno);
        // Set the withdraw
        account.withdraw(amount);
    }
    
    public void deposit ( String accno, Integer amount ) throws Exception {
        // Get the account
        Account account = this.retrieve(accno);
        // Set the deposit
        account.deposit(amount);
    }
    
    public Integer balance ( String accno ) throws Exception {
        // Get the account
        Account account = this.retrieve(accno);
        // Return the balance
        return account.balance;
    }
    
    /**
     * Retrieve the account associated to the provided account number
     * 
     * @param accno account number
     * @return Account
     * @throws Exception 
     */
    private Account retrieve ( String accno ) throws Exception
    {
        Iterator<Account> i = accounts.iterator();
        while ( i.hasNext() ) {
            Account acc = i .next();
            if ( acc.getAccountNo().equals( accno ) )
                return acc;
        }
//        for (Account acc : accounts) {
//            if ( acc.getAccountNo().equals( accno ) )
//                return acc;
//        }
        throw new Exception ( "Account not found." );
    }
}
