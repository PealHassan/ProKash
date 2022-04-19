package com.example.prokash;


public class Validation {

    static boolean CheckAllChatecterWithSpace(String s1){
        int n= s1.length();
        if(n == 0) return false;
        for(int i=0;i<n;i++)
        {
            char a = s1.charAt(i);
            if(  a>='a' &&  a<='z')
            {
            }
            else if(  a>='A'  &&  a<='Z')
            {
            }
            else if(a==' ')
            {

            }
            else {
                return false;
            }
        }
        return true;
    }


    static boolean CheckAllChatecterWithoutSpace(String s1){
        int n= s1.length();
        if(n == 0) return  false;
        for(int i=0;i<n;i++)
        {
            char a = s1.charAt(i);
            if(  a>='a' &&  a<='z')
            {
            }
            else if(  a>='A'  &&  a<='Z')
            {
            }
            else {
                return false;
            }
        }
        return true;
    }

    static boolean CheckTwoStringEqual(String s1,String s2)
    {
        if(s1.length()<6)
            return false;
        return s1.equals(s2);
    }

    static boolean CheckValidPhoneNumber(String s1)
    {
        int n=s1.length();
        if(n!=10)
            return false;
        if(s1.charAt(0)!='1')
            return false;

        for(int i=0;i<n;i++)
        {
            char ch1=s1.charAt(i);
            if(  (ch1>='0'  &&  ch1<='9')  ==  false)
            {
                return false;
            }
        }

        return true;
    }

    static boolean CheckValidPostalCode(String s1)
    {
        int n=s1.length();
        if(n!=4)
            return false;


        for(int i=0;i<n;i++)
        {
            char ch1=s1.charAt(i);
            if(  (ch1>='0'  &&  ch1<='9')  ==  false)
            {
                return false;
            }
        }

        return true;
    }

    static boolean CheckValidNid(String s1)
    {
        int n= s1.length();
        if(n <= 8) return  false;
        for(int i=0;i<n;i++)
        {
            char a = s1.charAt(i);
            if(  a>='0' &&  a<='9')
            {
            }
            else {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        String s1="Pulok",s2="pulok";
        System.out.println(CheckValidPhoneNumber("1750333646"));
    }
}