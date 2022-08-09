package com.example.prokash;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model2 extends RecursiveTreeObject<Model2> {
    StringProperty Name,  phonenumber,email,agentid,index,balance;
    public Model2(String index, String agentid, String name, String balance, String email, String phonenumber) {
        this.Name = new SimpleStringProperty(name);
        this.phonenumber= new SimpleStringProperty(phonenumber);
        this.email = new SimpleStringProperty(email);
        this.agentid = new SimpleStringProperty(agentid);
        this.index = new SimpleStringProperty(index);
        this.balance = new SimpleStringProperty(balance);
    }



    public void setName(String name) {
        this.Name.set(name);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }



    public String getAgentid() {
        return agentid.get();
    }

    public StringProperty agentidProperty() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid.set(agentid);
    }

    public String getBalance() {
        return balance.get();
    }

    public StringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }


    public String getIndex() {return index.get();}



    public String getEmail() {
        return email.get();
    }


    public String getPhoneNumber() {
        return phonenumber.get();
    }


    public void setIndex(String index) {
        this.index.set(index);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber.set(phonenumber);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }






    public StringProperty indexProperty() {
        return index;
    }
    public StringProperty phonenumberProperty() {
        return phonenumber;
    }

    public StringProperty emailProperty() {
        return email;
    }


}
