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
public interface PaymentService {
    
    PaymentAccount createPaymentAccount(PaymentAccount paymentAccount);
    boolean deletePaymentAccount(String iban);

    double checkAccountBalance(String iban);
    List<PaymentTransaction> getTransactions();
    
    boolean transferMoney(String senderIban, String receiverIban, double amount, CurrencyCode Currency);
}
