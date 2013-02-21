/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaatm;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ATM listener
 * @author Guillermo Antonio Toro Bayona
 */
public class ATMListener implements ActionListener {

    /**
     * ATMView
     */
    private ATMView atmView;
    /**
     * Bank
     */
    private Bank bank;
    /**
     * String account number
     */
    private String account;

    /**
     * Constructor for listener
     * @param atmView
     * @param bank
     * @param account
     */
    public ATMListener(ATMView atmView, Bank bank, String account) {
        this.atmView = atmView;
        this.bank = bank;
        this.account = account;
        this.atmView.getjLabelAccountNumber().setText(account);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Validate null and Component
        if (e != null && e.getSource() instanceof Component) {
            // Get component
            Component guiComponent = (Component) e.getSource();
            try {
                // Validate balance operation
                if (guiComponent.equals(this.atmView.getjButtonBalance())) {
                    // Call the bank
                    Integer balance = this.bank.balance(this.account);
                    // String message
                    String messageBalance = "Current Balance : " + balance;
                    // Set value
                    this.atmView.getjLabelResult().setText(messageBalance);
                } // Validate withdraw operation
                else if (guiComponent.equals(this.atmView.getjButtonWithDraw())) {
                    // Get the value
                    String ammount = this.atmView.getjTextFieldAmount().getText();
                    // Validate number
                    if (ammount.trim().isEmpty() || !ammount.matches("[0-9]+")) {
                        throw new Exception("Please provide a valid amount");
                    } else {
                        // Integer value
                        Integer value = Integer.valueOf(ammount);
                        // String message
                        String messageWithdraw = "Withdraw processed. Value: " + value;
                        // Call the bank
                        this.bank.withdraw(this.account, value);
                        // Set value
                        this.atmView.getjLabelResult().setText(messageWithdraw);
                    }
                } // Validate deposit operation
                else if (guiComponent.equals(this.atmView.getjButtonDeposit())) {
                    // Get the value
                    String ammount = this.atmView.getjTextFieldAmount().getText();
                    // Validate number
                    if (ammount.trim().isEmpty() || !ammount.matches("[0-9]+")) {
                        throw new Exception("Please provide a valid amount");
                    } else {
                        // Integer value
                        Integer value = Integer.valueOf(ammount);
                        // String message
                        String messageDeposit = "Deposit processed. Value: " + value;
                        // Call the bank
                        this.bank.deposit(this.account, value);
                        // Set value
                        this.atmView.getjLabelResult().setText(messageDeposit);
                    }
                }
                // Clean the input
                this.atmView.getjTextFieldAmount().setText(null);
            } catch (Exception ex) {
                // Set the message
                this.atmView.getjLabelResult().setText(ex.getMessage());
            }
        }
    }
}
