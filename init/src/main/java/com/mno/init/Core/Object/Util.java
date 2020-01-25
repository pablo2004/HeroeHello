package com.mno.init.Core.Object;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import com.mno.init.Core.Module.ActivityBase;

/**
 * Created by pablo on 4/09/17.
 */

public class Util {

    public static boolean inArray(String [] array, String value){

        boolean found = false;

        if(array != null && array.length > 0){
            for(String element : array){
                if(element.trim().contentEquals(value)){
                    found = true;
                    break;
                }
            }
        }

        return found;

    }

    public static String hourToSimpleHour(String hour){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", new Locale("es", "ES"));
        String dateReturn = "";

        try {
            Date date1 = sdf.parse(hour);
            dateReturn = getDate(date1.getTime(), "hhaa");
        } catch (ParseException e) {
            dateReturn = "";
        }

        return dateReturn.toLowerCase();
    }

    public static String getDate(long milliSeconds, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, new Locale("es", "ES"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static float dpFromPx(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int getDpi(ActivityBase activityBase){
        DisplayMetrics metrics = activityBase.getResources().getDisplayMetrics();
        return metrics.densityDpi;
    }

    public static float getDensity(ActivityBase activityBase){
        DisplayMetrics metrics = activityBase.getResources().getDisplayMetrics();
        return metrics.density;
    }


    public static int getHeight(ActivityBase activityBase){
        int height = activityBase.getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    public static int getWidth(ActivityBase activityBase){
        int width = activityBase.getWindowManager().getDefaultDisplay().getWidth();
        return width;
    }

    public static HashMap<String, String> getSmallMonths(){
        return getMonths(true);
    }

    public static HashMap<String, String> getCompleteMonths(){
        return getMonths(false);
    }

    public static HashMap<String, String> getMonths(boolean small){
        HashMap<String, String> months = new HashMap<>();

        if(small) {
            months.put("1", "Ene");
            months.put("2", "Feb");
            months.put("3", "Mar");
            months.put("4", "Abr");
            months.put("5", "May");
            months.put("6", "Jun");
            months.put("7", "Jul");
            months.put("8", "Ago");
            months.put("9", "Sep");
            months.put("10", "Oct");
            months.put("11", "Nov");
            months.put("12", "Dic");
        }
        else{
            months.put("1", "Enero");
            months.put("2", "Febrero");
            months.put("3", "Marzo");
            months.put("4", "Abril");
            months.put("5", "Mayo");
            months.put("6", "Junio");
            months.put("7", "Julio");
            months.put("8", "Agosto");
            months.put("9", "Septiembre");
            months.put("10", "Octubre");
            months.put("11", "Noviembre");
            months.put("12", "Diciembre");
        }

        return months;
    }

    public static Calendar dateToCalendar(String date, String inFormatString){
        SimpleDateFormat inFormat = new SimpleDateFormat(inFormatString, new Locale("es", "ES"));
        Calendar calendar = Calendar.getInstance();

        try {
            Date parsed = inFormat.parse(date);
            calendar.setTime(parsed);
        } catch (ParseException e) {
            MLog.e("getFormatedDate", e.getMessage());
        }

        return calendar;
    }

    public static String getFormatedDate(String date, String inFormatString, String outFormatString) {
        //"yyyy-MM-dd hh:mm:ss"
        SimpleDateFormat inFormat = new SimpleDateFormat(inFormatString, new Locale("es", "ES"));
        SimpleDateFormat outFormat = new SimpleDateFormat(outFormatString, new Locale("es", "ES"));

        String formatedDate = "";

        try {
            Date parsed = inFormat.parse(date);
            formatedDate =  outFormat.format(parsed);
        } catch (Exception e) {
            MLog.e("getFormatedDate", e.getMessage());
        }

        return formatedDate;
    }

    public static String getFormatedDay(String date) {
        return getFormatedDate(date, "yyyy-MM-dd", "dd");
    }

    public static String getFormatedMonth(String date) {
        return getFormatedDate(date, "yyyy-MM-dd", "M");
    }

    public static String getFormatedYear(String date) {
        return getFormatedDate(date, "yyyy-MM-dd", "yyyy");
    }

    public static String getFormatedSpanish(String date) {
        return getFormatedDay(date)+" de "+getCompleteMonths().get(getFormatedMonth(date))+" del "+getFormatedYear(date);
    }

    public static String getFormatedHour(String date, boolean lowerCase) {

        String hour = getFormatedDate(date, "HH:mm", "hh a");

        if(lowerCase){
            hour = hour.toLowerCase();
        }

        return hour;
    }

    public static final int CREDITCARD_VISA = 1;
    public static final int CREDITCARD_MASTERCARD = 2;
    public static final int CREDITCARD_AMERICANEXPRESS = 3;

    public static int creditCardType(String cardNumber){

        int cardType = 0;

        String visaRegexp = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterCardRegexp = "^5[1-5][0-9]{14}$";
        String americanExpressRegexp = "^3[47][0-9]{13}$";

        if(cardNumber.matches(visaRegexp)){
            cardType = CREDITCARD_VISA;
        }
        else if(cardNumber.matches(masterCardRegexp)){
            cardType = CREDITCARD_MASTERCARD;
        }
        else if(cardNumber.matches(americanExpressRegexp)){
            cardType = CREDITCARD_AMERICANEXPRESS;
        }

        return cardType;
    }

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static String doubleToTwoDecimal(double number){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(number);

    }

    public static String doubleToTwoDecimalMoney(double number){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return "$ "+decimalFormat.format(number);

    }

    public static String numberToMoney(double number){

        return "$ " + numberToMoneyNoSign(number);

    }

    public static String numberToMoneyNoSign(String number){

        double value = 0;

        try{
            value = Double.parseDouble(number);
        }
        catch (NumberFormatException e){

        }

        return numberToMoneyNoSign(value);
    }

    public static String numberToMoney(String number){

        return "$ " + numberToMoneyNoSign(number);
    }

    public static String numberToMoneyNoSign(double number){

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String result = "";

        result = formatter.format(number).replace("$", "").replace("€", "");

        return result;

    }


    public static Intent newTwitterIntent(String text, String url) {

        Uri uri = Uri.parse(url);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpeg");
        intent.setPackage("com.twitter.android");

        return intent;
    }

    public static Intent newEmailIntent(String subject, String body, String bodyHtml){

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        if(body != null) {
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        }

        if(bodyHtml != null) {
            emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, bodyHtml);
        }

        return Intent.createChooser(emailIntent, "Send email...");
    }

    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public static String[] splitChars(String chars){
        String [] parts = new String[0];

        if(chars != null){

            chars = chars.trim();
            int length = chars.length();
            parts = new String[length];

            for (int i = 0; i < length; i++){
                parts[i] = chars.charAt(i)+"";
            }

        }

        return parts;

    }

    public static int findIndex(String search, String [] data){

        int index = -1;
        int i = 0;

        for (String value : data){

            if(value.compareTo(search) == 0){
                index = i;
                break;
            }

            i++;
        }

        return index;
    }

    public static String[] listToArray(ArrayList<String> array){
        String [] data = new String[array.size()];
        int i = 0;

        for (String value : array){
            data[i] = value;
            i++;
        }

        return data;
    }


    public static int DATE_ORDER_LEFT = 0;
    public static int DATE_ORDER_RIGHT = 1;

    public static String convertDate(String date, String inLimiter, String outLimiter, int inOrder, int outOrder){

        String format = "";

        if(date != null && !date.isEmpty()){

            String [] parts = date.split(inLimiter);

            if(parts != null && parts.length == 3){

                if(inOrder == outOrder){
                    format = parts[0]+outLimiter+parts[1]+outLimiter+parts[2];
                }
                else {
                    format = parts[2]+outLimiter+parts[1]+outLimiter+parts[0];
                }

            }

        }

        return format;

    }

}
