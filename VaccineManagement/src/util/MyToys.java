package util;

import static data.InjectionList.ANSI_RESET;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author User TinNTSE150116
 */
public class MyToys {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    final static String DATE_FORMAT = "dd/MM/yyyy";
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
                System.out.println(MyToys.getRedColor(errorMsg));
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
                System.out.println(MyToys.getRedColor("Your input must be and integer and between " + min + " and " + max + "!~~"));
            } catch (NumberFormatException e) {
                System.out.println(MyToys.getRedColor("Your input must be and integer and between " + min + " and " + max + "!~~"));
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
                System.out.println(MyToys.getRedColor(errorMsg));
            } catch (NumberFormatException e) {
                System.out.println(MyToys.getRedColor(errorMsg));
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
                System.out.println(MyToys.getRedColor(errorMsg));
            } else {
                return str.trim().replaceAll("\\s", " ");
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
                System.out.println(MyToys.getRedColor(errorMsg));
            } else {
                return str.trim().replaceAll("\\s", " ");
            }
        } while (true);
    }

    // Nhập chuỗi có điều kiện
    public static String getString(String inputMsg, String errorMsg, String format) {
        String str;
        boolean match;
        do {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();
            match = str.matches(format);
            if (str.length() == 0 || !match) {
                System.out.println(MyToys.getRedColor(errorMsg));
            } else {
                return str;
            }
        } while (true);
    }

    public static String getDate(String inputMsg, String errorMsg) {
        String date;
        do {
            System.out.print(inputMsg);
            date = sc.nextLine().trim();
            if (!MyToys.isValidDate(date)) {
                System.out.println(MyToys.getRedColor(errorMsg));
            } else {
                return date;
            }
        } while (!MyToys.isValidDate(date));
        return null;
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

//    public static int compareDate(String date1, Strign date2) {
//        boolean status = false;
//        if (checkDate(date)) {
//            dateFormat.setLenient(false);
//            try {
//                dateFormat.parse(date);
//                status = true;
//            } catch (Exception e) {
//                status = false;
//            }
//        }
//        return status;
//    }
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
    public static int compareDateToNow(String date) {
        java.util.Date now = new java.util.Date();
        while (true) {
            try {
                return dateFormat.parse(date).compareTo(now);
            } catch (Exception e) {

            }
        }
    }

    //ss date với năm
    public static int compareDateToYear(String date, int year) {
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

    public static int countDaysBetween(String date1, String date2) {
        while (true) {
            try {
                return (int) ((dateFormat.parse(date1).getTime() - dateFormat.parse(date2).getTime()) / (1000 * 60 * 60 * 24));
            } catch (Exception e) {

            }
        }
    }

    public static int countDaysTillNow(String date) {
        java.util.Date now = new java.util.Date();
        while (true) {
            try {
                return (int) ((dateFormat.parse(date).getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
            } catch (Exception e) {

            }
        }
    }

    public static String getRedColor(String str) {
        return ANSI_RED + str + ANSI_RESET;
    }

    public static String getGreenColor(String str) {
        return ANSI_GREEN + str + ANSI_RESET;
    }

    //main test format (shift + f6)
    public static void main(String[] args) {
        System.out.println(countDaysTillNow("21/09/2021"));
    }

    // Định dạng tiền Việt Nam cho đẹp
    public static String convertCurrencyVN(int price) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(price);
    }

}
