package edu.espe.controlGastos.model;

import java.util.Date;

public class MyCreditCard {
    private String title;
    private String description;
    private int limit;
    private CreditCardType type;
    private Date closing;
    private Date payment;
    private MyAccount myAccount;
}
