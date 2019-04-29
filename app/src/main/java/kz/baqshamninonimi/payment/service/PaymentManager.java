/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.baqshamninonimi.payment.service;

import kz.baqshamninonimi.payment.entity.CurrencyCode;
import kz.baqshamninonimi.payment.entity.PaymentAccount;
import kz.baqshamninonimi.payment.entity.PaymentTransaction;
import java.util.List;

/**
 *
 * @author assylbek
 */
public class PaymentManager implements PaymentService {

    @Override
    public PaymentAccount createPaymentAccount(PaymentAccount paymentAccount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePaymentAccount(String iban) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double checkAccountBalance(String iban) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaymentTransaction> getTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean transferMoney(String senderIban, String receiverIban, double amount, CurrencyCode Currency) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
