package com.example.prokash;

public class agenttranshist {
    String accountid, type, amount, date,index;


    public agenttranshist(String accountid, String type, String amount, String date,String index) {
        this.accountid = accountid;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.index = index;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
