package com.chuckwagon.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by branden on 4/7/16 at 13:14.
 */
public class EmailUtils {


    /**
     *http://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
     *Thanks Stacky.
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


}