package com.example.prokash;

public class usertransModel {
    String index,accountid,username,amount,charge,transtype,date;

    public usertransModel(String index, String accountid, String username, String amount, String charge, String transtype, String date) {
        this.index = index;
        this.accountid = accountid;
        this.username = username;
        this.amount = amount;
        this.charge = charge;
        this.transtype = transtype;
        this.date = date;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
