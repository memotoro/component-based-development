/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbatm;

import javax.ejb.Remote;

/**
 * Interface with @Remote for Bank EJB
 * @author Guillermo Antonio Toro Bayona
 */
@Remote
public interface BankRemote {

    /**
     * Method to get the balance
     * @param accno String account number
     * @return Integer with the balance
     * @throws Exception
     */
    public Integer balance(final String accno) throws Exception;

    /**
     * Method to set a new deposit
     * @param accno String account number
     * @param amount Integer with the amount to deposit
     * @throws Exception
     */
    public void deposit(final String accno, final Integer amount) throws Exception;

    /**
     * Method to set a withdraw
     * @param accno String account number
     * @param amount Integer with the amount to withdraw
     * @throws Exception
     */
    public void withdraw(final String accno, final Integer amount) throws Exception;
}
