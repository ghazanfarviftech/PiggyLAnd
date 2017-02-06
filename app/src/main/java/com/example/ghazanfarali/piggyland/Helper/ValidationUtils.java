package com.example.ghazanfarali.piggyland.Helper;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amir.jehangir on 1/11/2017.
 */
public class ValidationUtils {
    public static boolean testEmpty(String str) {
        if ((str == null) || str.matches("^\\s*$")) {
            return true;
        } else {
            if (str.equalsIgnoreCase("null")) {
                return true;
            } else if (str.contains("null")) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    public static boolean isValidUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }


    public static boolean isNumeric(String str) {
        try {
            @SuppressWarnings("unused")
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumericFloat(String str) {
        try {
            @SuppressWarnings("unused")
            float f = Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean cardValidationMethod(String CardNo) {
        try {
            int twoDigit = Integer.parseInt(CardNo.substring(0, 2));
            System.out.println("----------twoDigit--" + twoDigit);
            int fourDigit = Integer.parseInt(CardNo.substring(0, 4));
            System.out.println("----------fourDigit--" + fourDigit);
            int oneDigit = Integer.parseInt(Character.toString(CardNo.charAt(0)));
            System.out.println("----------oneDigit--" + oneDigit);
            //'Check the first two digits first,for AmericanExpress
            if (CardNo.length() == 15 && (twoDigit == 34 || twoDigit == 37))
                return true;
            else
                //'Check the first two digits first,for MasterCard
                if (CardNo.length() == 16 && twoDigit >= 51 && twoDigit <= 55)
                    return true;
                else
                    //'None of the above - so check the 'first four digits collectively
                    if (CardNo.length() == 16 && fourDigit == 6011)//for DiscoverCard
                        return true;
                    else if (CardNo.length() == 16 || CardNo.length() == 13 && oneDigit == 4)//for VISA
                        return true;
                    else
                        return false;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isValidPassword(final String password) {

        /*Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
*/


        return password.length() >= 6 ? true : false;


    }

    public static boolean isValidPhone(final String phone) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(phone);

        return matcher.matches();


//        return password.length() >= 6 ? true : false;


    }


    public static boolean isValidCCV(final String ccv) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^[0-9]{3,4}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(ccv);

        return matcher.matches();

    }
}
