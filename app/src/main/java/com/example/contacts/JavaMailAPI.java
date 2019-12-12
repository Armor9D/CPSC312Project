package com.example.contacts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Sets up the Java mail API, which allows for an email to be sent using a user's personal GMail account
 * Sources: https://www.youtube.com/watch?v=RahBCY5BfS0 - Youtube tutorial on using JavaMail in Android Studio
 *          https://github.com/Musfick/JavaMailAPIDemo - Github repository for Youtube Tutorial
 *          https://www.tutorialspoint.com/javamail_api/javamail_api_tutorial.pdf - Guide to using JavaMail for regular Java
 */

public class JavaMailAPI extends AsyncTask<Void,Void,Void>  {

    private Context mContext;
    private Session mSession;

    private String mEmail;
    private String mSubject;
    private String mMessage;

    private ProgressDialog mProgressDialog;

    /**
     * EVC for the JavaMailAPI class
     *
     * @param mContext - the Activity calling this class
     * @param mEmail - the email address to which the email is being sent
     * @param mSubject - the header line of the email
     * @param mMessage - the main body of the email
     */
    public JavaMailAPI(Context mContext, String mEmail, String mSubject, String mMessage) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
    }

    /**
     * Activates before the background UI thread sends the email
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Shows a progress bar while the  email is being sent
        mProgressDialog = ProgressDialog.show(mContext,"Sending message", "Please wait...",false,false);
    }

    /**
     * Activates after the background UI thread finishes sending the email
     *
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Removes the progress bar
        mProgressDialog.dismiss();

        // Notifies user that email has been sent
        Toast.makeText(mContext,"Message Sent",Toast.LENGTH_SHORT).show();
    }

    /**
     * Background thread that actually sends email
     *
     * @param params
     */
    @Override
    protected Void doInBackground(Void... params) {
        // Create properties to allow sending of email
        Properties props = new Properties();

        // Configure properties to send from a GMail address
        // This will not work if sending from a non-GMail address
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Create a new session and authenticate user's email address and password
        mSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
                    }
                });

        try {
            // Create MimeMessage object
            MimeMessage mm = new MimeMessage(mSession);

            // Set address of email's sender
            mm.setFrom(new InternetAddress(Utils.EMAIL));
            // Set address of email's recipient
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
            // Add email's subject line
            mm.setSubject(mSubject);
            // Add actual message of email
            mm.setText(mMessage);
            // Send the email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace(); // in case of errors with message process
        }
        return null;
    }
}
