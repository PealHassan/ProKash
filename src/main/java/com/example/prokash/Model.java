package com.example.prokash;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model extends RecursiveTreeObject<Model> {
    StringProperty name, age, gender,occupation,phonenumber,email,accountid,index;

    public Model(String name, String age, String gender, String occupation, String phonenumber,String email, String accountid, String index) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleStringProperty(age);
        this.gender = new SimpleStringProperty(gender);
        this.occupation = new SimpleStringProperty(occupation);
        this.phonenumber = new SimpleStringProperty(phonenumber);
        this.accountid = new SimpleStringProperty(accountid);
        this.email = new SimpleStringProperty(email);
        this.index = new SimpleStringProperty(index);
    }

    public String getAge() {
        return age.get();
    }
    public String getIndex() {return index.get();}
    public String getOccupation() {
        return occupation.get();
    }
    public String getGender() {
        return gender.get();
    }
    public String getName() {
        return name.get();
    }



    public String getEmail() {
        return email.get();
    }

    public String getAccountId() {
        return accountid.get();
    }
    public String getPhoneNumber() {
        return phonenumber.get();
    }

    public void setAccountid(String accountid) {
        this.accountid.set(accountid);
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

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public void setAge(String age) {
        this.age.set(age);
    }



    public StringProperty ageProperty() {
        return age;
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
    public StringProperty accountidProperty() {
        return accountid;
    }
    public StringProperty OccupationProperty() {
        return occupation;
    }
    public StringProperty genderProperty() {
        return gender;
    }
    public StringProperty nameProperty() {
        return name;
    }

}
