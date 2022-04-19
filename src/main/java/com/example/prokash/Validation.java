


public class Main {

    static boolean CheckAllChatecterWithSpace(String s1){
        int n= s1.length();
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


    static boolean CheckAllChatecter(String s1){
        int n= s1.length();
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
        return s1.equals(s2);
    }

    static boolean CheckValidPhoneNumber(String s1)
    {
        int n=s1.length();
        if(n!=10)
            return false;
        if(s1.charAt(0)!='1')
            return false;

        return true;
    }


    public static void main(String[] args) {
        String s1="Pulok",s2="pulok";
        System.out.println(CheckValidPhoneNumber("1750333646"));
    }
}