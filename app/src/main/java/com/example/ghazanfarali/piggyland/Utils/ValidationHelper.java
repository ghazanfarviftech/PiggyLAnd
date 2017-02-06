package com.example.ghazanfarali.piggyland.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amir.jehangir on 1/2/2017.
 */
public class ValidationHelper {
    //    static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    static String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}";

    public ValidationHelper() {

    }

   /* public boolean isEmailValid(String email) {
        if (email.matches(emailPattern))
            return true;
        else
            return false;
    }*/

    public boolean isPasswordValid(String pasword) {
        if (pasword.length() < 6)
            return false;
        else if (pasword.contains("[a-zA-Z]+") && pasword.contains("[0-9]+"))
            return true;
        else
            return false;
    }

    public static boolean isValidName(final String str) {

        if (str == null || str.isEmpty()) return false;

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
                found = true;
            } else if (found) {
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }
        if (sb.length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /* public static String getMd5(String s) {
         try {
             // Create MD5 Hash
             MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
             digest.update(s.getBytes());
             byte messageDigest[] = digest.digest();

             // Create Hex String
             StringBuffer hexString = new StringBuffer();
             for (int i = 0; i < messageDigest.length; i++)
                 hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
             return hexString.toString();

         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }

         return "";
     }*/
    private static String convertedToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; i++) {
            int halfOfByte = (data[i] >>> 4) & 0x0F;
            int twoHalfBytes = 0;

            do {
                if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
                    buf.append((char) ('0' + halfOfByte));
                } else {
                    buf.append((char) ('a' + (halfOfByte - 10)));
                }

                halfOfByte = data[i] & 0x0F;

            } while (twoHalfBytes++ < 1);
        }
        return buf.toString();
    }

    public static String getMd5(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5 = new byte[64];
        try {
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        md5 = md.digest();
        return convertedToHex(md5);
    }

    public static boolean isEmailValid(String email) {

        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isAlphabetical(String s){
//        String pattern= "^[^a-zA-Z0-9\\s\\u0600-\\u06ff\\u0750-\\u077f\\ufb50-\\ufc3f\\ufe70-\\ufefc]*$";
        String pattern = "^[(^a-zA-Z)(\\u0600-\\u06FF)(\\s)]*";
        if(s.matches(pattern)){
            return true;
        }
        return false;
    }

    public static boolean isValidPassword(String s){
        String pattern = "^(?=.*\\d).{6,40}$";
        if(s.matches(pattern)){
            return true;
        }
        return false;
    }
}
