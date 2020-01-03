package com.bruang.bookingruang.Util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    /**Convert String to Date with Default Format is yyyy MM dd*/
    public static Date stringToDateConverter(String stringDate){
        return stringToDateConverter(stringDate, "yyyy-MM-dd");
    }
    /**Convert String to Date As Format*/
    public static Date stringToDateConverter(String stringDate, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(stringDate);
            return date;
        } catch (Exception ex) {
        }

        return new Date(1995,1, 1);
    }

    /**Get Formated Short Date Ex: "17 Nov+ 2020"*/
    public static String getFormatedShortDate(String date){
        return DateFormat.getDateInstance(DateFormat.MEDIUM)
                .format(stringToDateConverter(date))
                .toString();
    }

    /**Get Formated Long Date Ex: Saturday, "17 November 2020"*/
    public static String getFormatedLongDate(String date){
        return DateFormat.getDateInstance(DateFormat.FULL)
                .format(stringToDateConverter(date))
                .toString();
    }


    /**Get Formated Date From Format 2019-12-24*/
    public static String convertDate(String inputDate, String toFormat){
        return convertDate(inputDate, "yyyy-MM-dd", toFormat);
    }

    //TODO: Fix Date Conver Error
    /**Get Formated Date With From Format And To Format*/
    public static String convertDate(String inputDate, String fromFormat, String toFormat) {

        DateFormat theDateFormat = new SimpleDateFormat(fromFormat);
        Date date = null;

        try {
            date = theDateFormat.parse(inputDate);
        } catch (ParseException parseException) {
            Log.d("Date Convert Error", "Date is invalid. ");
        } catch(Exception exception) {
            Log.d("Date Convert Error", "Generic catch.");
        }

        theDateFormat = new SimpleDateFormat(toFormat);

        return theDateFormat.format(date);
    }

}
