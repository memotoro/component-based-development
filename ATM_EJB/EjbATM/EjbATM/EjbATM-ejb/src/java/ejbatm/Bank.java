/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbatm;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Bank as Stateless EJB
 * Set the name of the EJB for JNDI lookup as a 'ejbatm.BankRemote'
 * @author Guillermo Antonio Toro Bayona
 */
@Stateless
public class Bank implements BankRemote {

    /**
     * EJB declaration and instantiated by reflexion
     */
    @EJB
    private AccountFacade accountFacade;

    /**
     * Find an account by account number
     * @param accno String account number
     * @return Account
     */
    private Account findAccountByAccno(String accno) {
        // Return the account
        return this.accountFacade.find(accno);
    }

    /**
     * Method to get the balance
     * @param accno String account number
     * @return Integer with the balance
     * @throws Exception
     */
    @Override
    public Integer balance(final String accno) throws Exception {
        // Account
        Account account = this.findAccountByAccno(accno);
        // Integer balance
        Integer balance = null;
        // Validate if null
        if (account != null) {
            // Get the balance
            balance = account.getBalance();
        } else {
            // Exception
            throw new Exception("Account not found.");
        }
        // Return
        return balance;
    }

    /**
     * Method to set a new deposit
     * @param accno String account number
     * @param amount Integer with the amount to deposit
     * @throws Exception
     */
    @Override
    public void deposit(final String accno, final Integer amount) throws Exception {
        // Account
        Account account = this.findAccountByAccno(accno);
        // Validate values
        if (amount > 0) {
            // Validate if null
            if (account != null) {
                // Set the new balance
                account.setBalance(account.getBalance() + amount);
                // Update
                this.accountFacade.edit(account);
            } else {
                // Exception
                throw new Exception("Account not found.");
            }
        } else {
            // Exception
            throw new Exception("Invalid amount.");
        }
    }

    /**
     * Method to set a withdraw
     * @param accno String account number
     * @param amount Integer with the amount to withdraw
     * @throws Exception
     */
    @Override
    public void withdraw(final String accno, final Integer amount) throws Exception {
        // Account
        Account account = this.findAccountByAccno(accno);
        // Validate values
        if (amount > 0 && amount <= account.getBalance()) {
            // Validate if null
            if (account != null) {
                // Set the new balance
                account.setBalance(account.getBalance() - amount);
                // Update
                this.accountFacade.edit(account);
            } else {
                // Exception
                throw new Exception("Account not found.");
            }
        } else {
            // Exception
            throw new Exception("Invalid amount.");
        }
    }
}
