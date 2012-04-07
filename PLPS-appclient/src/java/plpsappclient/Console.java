/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plpsappclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zen9
 */
public class Console {

    public static boolean askYesNo(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String yn = null;
        try {
            while (true) {
                System.out.print(message + " (Y/N) ");
                yn = br.readLine();
                if (!yn.equals("")) {
                    if (yn.toUpperCase().equals("Y")) {
                        return true;
                    }
                    if (yn.toUpperCase().equals("N")) {
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("\nERROR: " + ex.getMessage());
        }
        return false;
    }

    public static int getInt(String attrString) {

        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        int val = 0;
        try {
            System.out.print("Enter " + attrString + " : ");
            line = is.readLine();
            val = Integer.parseInt(line);
            if (val < 0) {
                System.out.println("Invalid " + val + "...");
            }
        } catch (Exception ex) {
            System.out.println("\nSystem Error Message: " +
                    ex.getMessage() + "\n");
        }
        return val;
    }

    public static Long getLong(String attrString) {

        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        Long val = 0L;
        try {
            System.out.print("Enter " + attrString + " : ");
            line = is.readLine();
            val = Long.parseLong(line);
            if (val < 0) {
                System.out.println("Invalid " + val + "...");
            }
        } catch (Exception ex) {
            System.out.println("\nSystem Error Message: " +
                    ex.getMessage() + "\n");
        }
        return val;
    }

    public static String getString(String attrName,String oldValue) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String stringValue = null;

        try {
            while (true) {
                System.out.print(
                        "Enter " + attrName + (oldValue == null ? "" : "(" + oldValue + ")") + " : ");
                stringValue = br.readLine();
                if (stringValue.length() != 0) {
                    break;
                } else if (stringValue.length() == 0 &&
                        oldValue != null) {
                    stringValue = oldValue;
                    break;
                }
                System.out.println("Invalid " + attrName + "...");
            }
        } catch (Exception ex) {
            System.out.println("\nSystem Error Message: " +
                    ex.getMessage() + "\n");
        }
        return stringValue.trim();
    }

    public static Date getDateFromString(String attrName) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String s = null;
        Date dateValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            while (true) {
                System.out.print("Enter the " + attrName + " (yyyy-mm-dd) : ");
                s = br.readLine();
                if (s.length() != 0) {
                    try {
                        dateValue = sdf.parse(s);
                        break;
                    } catch (ParseException ex) {
                        System.out.println("\nInvalid Date \n");
                    }
                }
                System.out.println("Invalid " + attrName + "...");
            }
        }//end while
        catch (Exception ex) {
            System.out.println("]\nERROR: " + ex.getMessage());
        }
        return dateValue;
    }
}
