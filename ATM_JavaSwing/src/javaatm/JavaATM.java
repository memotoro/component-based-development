/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaatm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cuong Tran
 */
public class JavaATM {

    /**
     * The class simulate the card reader device in ATM machine
     */
    private class CardReader {

        /**
         * Simulate card reading operation.
         * 
         * @return an account number
         */
        public String readAccountNo() {
            String[] accno = {"1111", "2222"};

            Random r = new Random();

            return accno[r.nextInt(2)];
        }
    }

    public void run() {
        System.out.println("ATM System Start");

        Bank bank = new Bank();

        CardReader reader = this.new CardReader();
        String accno = reader.readAccountNo();


        // begin loop asking for service 
        // and dispatch the selected service to Bank
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            System.out.println("Account number: " + accno);

            System.out.println("Available services: ");
            System.out.println("  (B) or (b) Balance");
            System.out.println("  (W) or (w) Withdraw");
            System.out.println("  (D) or (d) Deposit");
            System.out.println("  (C) or (c) Cancel");
            System.out.print("Choice: ");

            try {
                // Create variable message
                String message = "";
                String service = in.readLine();
                service = service.trim().toUpperCase();

                // Processing the service choice
                if (service.equals("B")) {
                    // Call the balance via balance method and print it
                    Integer balance = bank.balance(accno);
                    // String message
                    message = "Current Balance: ";
                    // Print
                    System.out.println(message + balance);
                } else if (service.equals("D")) {
                    System.out.print("Amount: ");
                    String s = in.readLine();
                    Integer val = new Integer(s);
                    try {
                        // Call the balance via deposit method
                        bank.deposit(accno, val);
                        // String message
                        message = "Deposit processed.";
                        // Print
                        System.out.println(message);
                    } catch (Exception ex) {
                        // Get the message
                        message = ex.getMessage();
                        // Print
                        System.out.println(message);
                        break;
                    }
                } else if (service.equals("W")) {
                    System.out.print("Amount: ");
                    String s = in.readLine();
                    Integer val = new Integer(s);
                    try {
                        // Call the balance via withdraw method
                        bank.withdraw(accno, val);
                        // String message
                        message = "Withdraw processed.";
                        // Print
                        System.out.println(message);
                    } catch (Exception ex) {
                        // Get the message
                        message = ex.getMessage();
                        // Print
                        System.out.println(message);
                        break;
                    }
                } else if (service.equals("C")) {
                    break;
                } else {
                    System.out.println("Unrecognised service.");
                }
            } catch (Exception ex) {
                Logger.getLogger(JavaATM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // end loop
        System.out.println("ATM System Stop");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JavaATM client = new JavaATM();
        client.run();
    }
}
