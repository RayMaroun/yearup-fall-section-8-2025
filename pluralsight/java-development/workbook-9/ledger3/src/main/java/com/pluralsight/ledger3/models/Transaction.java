package com.pluralsight.ledger3.models;

public class Transaction {
    private Integer transactionId;
    private double amount;
    private String vendor;

    public Transaction(double amount, String vendor) {
        this.amount = amount;
        this.vendor = vendor;
    }

    public Transaction(Integer transactionId, double amount, String vendor) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.vendor = vendor;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", vendor='" + vendor + '\'' +
                '}';
    }
}
