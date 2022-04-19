package com.example.prokash;


import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Validation {
    static int validationFlag = 1;
    static boolean CheckAllCharacterWithSpace(String s1) {
        int n= s1.length();
        if(n == 0) return false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(! ((a >= 'a' &&  a <= 'z') || (a >= 'A'  &&  a <= 'Z') || a == ' ') ) return  false;
        }
        return true;
    }

    static boolean CheckAllCharacterWithoutSpace(String s1) {
        int n= s1.length();
        if(n == 0) return false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(! ((a >= 'a' &&  a <= 'z') || (a >= 'A'  &&  a <= 'Z')) ) return  false;
        }
        return true;
    }

    static boolean CheckTwoStringEqual(String s1,String s2) {
        if(s1.length()<6)
            return false;
        return s1.equals(s2);
    }

    static boolean CheckValidPhoneNumber(String s1) {
        int n=s1.length();
        if(n!=10)
            return false;
        if(s1.charAt(0)!='1')
            return false;

        for(int i=0;i<n;i++) {
            char ch1=s1.charAt(i);
            if(!(ch1 >= '0' && ch1 <= '9'))  return false;
        }
        return true;
    }

    static boolean CheckValidPostalCode(String s1) {
        int n=s1.length();
        if(n!=4)
            return false;
        for(int i=0;i<n;i++) {
            char ch1=s1.charAt(i);
            if(!(ch1 >= '0' && ch1 <= '9')) return false;
        }
        return true;
    }

    static boolean CheckValidNid(String s1) {
        int n= s1.length();
        if(n <= 8) return  false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(!(a>='0' &&  a<='9')) return  false;
        }
        return true;
    }
    static void valid(TextField firstname, TextField lastname, TextField mothername, TextField fathername, TextField occupation, TextField postoffice, TextField city, TextField district, TextField nationality, TextField phonenumber, TextField postalcode, TextField nid, TextField password, TextField confirmpassword, ComboBox<String> religion, ComboBox<String> maritalstatus, ComboBox<String> gender, DatePicker dateofbirth, ComboBox<String> income) {
        if(!CheckAllCharacterWithSpace(firstname.getText())) {
            validationFlag=0;
            showingError.changeStyle(firstname);
        }
        if(!CheckAllCharacterWithSpace(lastname.getText())) {
            validationFlag=0;
            showingError.changeStyle(lastname);
        }
        if(!CheckAllCharacterWithSpace(mothername.getText())) {
            validationFlag=0;
            showingError.changeStyle(mothername);
        }
        if(!CheckAllCharacterWithSpace(fathername.getText())) {
            validationFlag=0;
            showingError.changeStyle(fathername);
        }
        if(!CheckAllCharacterWithSpace(occupation.getText())) {
            validationFlag=0;
            showingError.changeStyle(occupation);
        }
        if(!CheckAllCharacterWithoutSpace(postoffice.getText())) {
            validationFlag=0;
            showingError.changeStyle(postoffice);
        }
        if(!CheckAllCharacterWithoutSpace(city.getText())) {
            validationFlag=0;
            showingError.changeStyle(city);
        }
        if(!CheckAllCharacterWithoutSpace(district.getText())) {
            validationFlag=0;
            showingError.changeStyle(district);
        }
        if(!CheckAllCharacterWithoutSpace(nationality.getText())) {
            validationFlag=0;
            showingError.changeStyle(nationality);
        }
        if(!CheckValidPhoneNumber(phonenumber.getText())) {
            validationFlag=0;
            showingError.changeStyle(phonenumber);
        }
        if(!CheckValidPostalCode(postalcode.getText())) {
            validationFlag=0;
            showingError.changeStyle(postalcode);
        }
        if(!CheckAllCharacterWithSpace(occupation.getText())) {
            validationFlag=0;
            showingError.changeStyle(occupation);
        }
        if(!CheckValidNid(nid.getText())) {
            validationFlag=0;
            showingError.changeStyle(nid);
        }
        if(!CheckTwoStringEqual(password.getText(), confirmpassword.getText())) {
            validationFlag=0;
            showingError.changeStyle(password);
            showingError.changeStyle(confirmpassword);
        }
        if(religion.getValue()==null) {
            validationFlag=0;
            showingError.changeStyle(religion);
        }
        if(maritalstatus.getValue()==null) {
            validationFlag=0;
            showingError.changeStyle(maritalstatus);
        }
        if(gender.getValue()==null) {
            validationFlag=0;
            showingError.changeStyle(gender);
        }
        if(dateofbirth.getValue()==null) {
            validationFlag=0;
            showingError.changeStyle(dateofbirth);
        }
        if(income.getValue()==null) {
            validationFlag=0;
            showingError.changeStyle(income);
        }
    }

}