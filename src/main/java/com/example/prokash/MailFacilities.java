package com.example.prokash;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class MailFacilities {
    public static void sendMail(String recepient, String Subject, String Text) throws Exception {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "joyhassan877@gmail.com";
        String password = "peal?peal?";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(myAccountEmail,password);

            }
        });

        Message message = prepareMessage(session,myAccountEmail,recepient,Subject,Text);
        assert message != null;
        Transport.send(message);

    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String Subject, String Text) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(Subject);
            message.setText(Text);
            return  message;
        } catch (Exception e) {
            Logger.getLogger(MailFacilities.class.getName()).log(Level.SEVERE,null,e);
        }
        return  null;

    }

    static void sendAccountId(String AccountId, String user, String useremail) throws Exception {
        String subject = "Your ProKash Account Id";
        String text = "Welcome " + user + ",\n\n\n\n\n" + "Thanks for signing up with ProKash. We're always working to make ProKash what exactly you need";
        text += (" to safely deal with your money. We provide our users a lot of services and offers.\n\n\n");
        text+=("To help us make Prokash the best it can be, we want your feedback. So, contact with us via email if needed. We are always ready to fix your problems in such a way that you want.\n\n\n\n\n\n\n");
        text+=("Your Account Id for ProKash is " + AccountId + ". Try to keep it safely.\n\n\n\n\n");
        text+=("Best wishes with ProKash");

        sendMail(useremail,subject,text);
    }
    static void sendVerificationCode(String code, String useremail) throws Exception {
        String subject = "ProKash Verification Code";
        String text = "Your verification code for ProKash is " + code + ". To verify your ProKash Account put down the code.";
        sendMail(useremail,subject,text);
    }
    private static int hear( BufferedReader in ) throws IOException {
        String line = null;
        int res = 0;
        while ( (line = in.readLine()) != null ) {
            String pfx = line.substring( 0, 3 );
            try {
                res = Integer.parseInt( pfx );
            }
            catch (Exception ex) {
                res = -1;
            }
            if ( line.charAt( 3 ) != '-' ) break;
        }
        return res;
    }
    private static void say(BufferedWriter wr, String text )
            throws IOException {
        wr.write( text + "\r\n" );
        wr.flush();
        return;
    }
    private static ArrayList getMX(String hostName )
            throws NamingException {

        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial",
                "com.sun.jndi.dns.DnsContextFactory");
        DirContext ictx = new InitialDirContext( env );
        Attributes attrs = ictx.getAttributes
                ( hostName, new String[] { "MX" });
        Attribute attr = attrs.get( "MX" );
        // if we don't have an MX record, try the machine itself
        if (( attr == null ) || ( attr.size() == 0 )) {
            attrs = ictx.getAttributes( hostName, new String[] { "A" });
            attr = attrs.get( "A" );
            if( attr == null )
                throw new NamingException
                        ( "No match for name '" + hostName + "'" );
        }

        ArrayList res = new ArrayList();
        NamingEnumeration en = attr.getAll();
        while ( en.hasMore() ) {
            String x = (String) en.next();
            String f[] = x.split( " " );
            if ( f[1].endsWith( "." ) )
                f[1] = f[1].substring( 0, (f[1].length() - 1));
            res.add( f[1] );
        }
        return res;
    }
    public static boolean isAddressValid( String address ) {

        int pos = address.indexOf( '@' );

        if ( pos == -1 ) return false;

        String domain = address.substring( ++pos );
        ArrayList mxList = null;
        try {
            mxList = getMX( domain );
        }
        catch (NamingException ex) {
            return false;
        }
        if ( mxList.size() == 0 ) return false;
        for ( int mx = 0 ; mx < mxList.size() ; mx++ ) {
            boolean valid = false;
            try {
                int res;
                Socket skt = new Socket( (String) mxList.get( mx ), 25 );
                BufferedReader rdr = new BufferedReader
                        ( new InputStreamReader( skt.getInputStream() ) );
                BufferedWriter wtr = new BufferedWriter
                        ( new OutputStreamWriter( skt.getOutputStream() ) );
                res = hear( rdr );
                if ( res != 220 ) throw new Exception( "Invalid header" );
                say( wtr, "EHLO orbaker.com" );
                res = hear( rdr );
                if ( res != 250 ) throw new Exception( "Not ESMTP" );
                // validate the sender address
                say( wtr, "MAIL FROM: <tim@orbaker.com>" );
                res = hear( rdr );
                if ( res != 250 ) throw new Exception( "Sender rejected" );
                say( wtr, "RCPT TO: <" + address + ">" );
                res = hear( rdr );
                // be polite
                say( wtr, "RSET" ); hear( rdr );
                say( wtr, "QUIT" ); hear( rdr );
                if ( res != 250 )
                    throw new Exception( "Address is not valid!" );
                valid = true;
                rdr.close();
                wtr.close();
                skt.close();
            }
            catch (Exception ex) {
                // Do nothing but try next host
            }
            finally {
                if ( valid ) return true;
            }
        }
        return false;
    }
    public  static String call_this_to_validate( String email ) {
        String testData[] = {email};
        String return_string="";
        for ( int ctr = 0 ; ctr < testData.length ; ctr++ ) {
            return_string=( testData[ ctr ] + " is valid? " +
                    isAddressValid( testData[ ctr ] ) );
        }
        return return_string;
    }

}
