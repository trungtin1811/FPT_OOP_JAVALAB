package util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author User TinNTSE150116
 */
public class MyToys {

    private static Scanner sc = new Scanner(System.in);
    static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Nhập double có min max
    public static double getDouble(double min, double max, String inputMsg, String errorMsg) {
        double n;
        do {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n >= min && n <= max) {
                    return n;
                }
                System.out.println(errorMsg);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        } while (true);
    }

    // Nhập int có minmax
    public static int getInteger(int min, int max, String inputMsg) {
        int n;
        do {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n >= min && n <= max) {
                    return n;
                }
                System.out.println("Your input must be between " + min + " and " + max + "!~~");
            } catch (NumberFormatException e) {
                System.out.println("Your input must be between " + min + " and " + max + "!~~");
            }
        } while (true);
    }

    // Nhập int có minmax
    public static int getInteger(int min, int max, String inputMsg, String errorMsg) {
        int n;
        do {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n >= min && n <= max) {
                    return n;
                }
                System.out.println(errorMsg);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        } while (true);
    }

    // Nhập chuỗi khác rỗng
    public static String getString(String inputMsg, String errorMsg) {
        String str;
        do {
            System.out.print(inputMsg);
            str = sc.nextLine();
            if (str.length() == 0) {
                System.out.println(errorMsg);
            } else {
                return str.trim();
            }
        } while (true);
    }

    // Nhập chuỗi có kích thước
    public static String getString(String inputMsg, int minLength, int maxLength, String errorMsg) {
        String str = "";
        do {
            System.out.print(inputMsg);
            str = sc.nextLine();
            if (str.length() < minLength || str.length() > maxLength) {
                System.out.println(errorMsg);
            } else {
                return str.trim();
            }
        } while (true);
    }

    // Nhập chuỗi có điều kiện
    public static String getString(String inputMsg, String errorMsg, String format) {
        String str;
        boolean match;
        do {
            System.out.print(inputMsg);
            str = sc.nextLine();
            match = str.matches(format);
            if (str.length() == 0 || !match) {
                System.out.println(errorMsg);
            } else {
                return str.trim();
            }
        } while (true);
    }

    public static boolean isValidDate(String date) {
        boolean status = false;
        if (checkDate(date)) {
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                status = true;
            } catch (Exception e) {
                status = false;
            }
        }
        return status;
    }

    public static boolean checkDate(String date) {
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
        boolean flag = false;
        if (date.matches(datePattern)) {
            flag = true;
        }
        return flag;
    }

    public static int compareDate(String date1, String date2) {
        while (true) {
            try {
                return dateFormat.parse(date1).compareTo(dateFormat.parse(date2));
            } catch (Exception e) {

            }
        }
    }

    //compare to now
    public static int compareDate(String date) {
        java.util.Date now = new java.util.Date();
        while (true) {
            try {
                return dateFormat.parse(date).compareTo(now);
            } catch (Exception e) {

            }
        }
    }
    
    public static int compareDate(String date, int year) {
        java.util.Date now = new java.util.Date();
        int yearNow = now.getYear() + 1900;
        String dateAfter = "01/01/" + (yearNow + year);
        while (true) {
            try {
                return dateFormat.parse(date).compareTo(dateFormat.parse(dateAfter));
            } catch (Exception e) {

            }
        }
    }
    
    

    //main test format (shift + f6)
    public static void main(String[] args) {
        dateFormat.setLenient(false);

        try {
            System.out.println(compareDate("13/09/2021", 50));
        } catch (Exception e) {

        }
    }

    // Định dạng tiền Việt Nam cho đẹp
    public static String convertCurrencyVN(int price) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(price);
    }

}
