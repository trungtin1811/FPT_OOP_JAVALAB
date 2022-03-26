/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import util.MyToys;

/**
 *
 * @author User
 */
public class InjectionList {

    ArrayList<Injection> injections = new ArrayList();
    static StudentList students;
    static VaccineList vaccines;
    final String THE_FIRST_VACCINE = "08/03/2021";
    ProvinceList provinces;
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public InjectionList() {

    }

    public void printMenu() {
        System.out.println("***********************************************************************́́́***************");
        System.out.println("*   Welcome to Injection Management - © 2021 by < SE150116 - Nguyen Trung Tin >      *");
        System.out.println("*   Select the option bellow (1 ~ 6):                                                *");
        System.out.println("*   1. Show information all students have been injected.                             *");
        System.out.println("*   2. Add student's vaccine injection information.                                  *");
        System.out.println("*   3. Updating information of students' vaccine injection.                          *");
        System.out.println("*   4. Delete student vaccine injection information.                                 *");
        System.out.println("*   5. Search for injection information by VaccineName.                              *");
        System.out.println("*   6. Quit.                                                                         *");
        System.out.println("*************************************************************************́́́*************́́́́́");
    }

    public void readFiles(String studentFile, String vaccineFile, String provinceFile, String injFile) {
        students = new StudentList(studentFile);
        vaccines = new VaccineList(vaccineFile);
        provinces = new ProvinceList(provinceFile);
        readFile(injFile);

    }

    public void printVaccineList() {

        System.out.println("_________________________________");
        System.out.printf("|%-10s|%-20s|\n", "VACCINE ID", "NAME");
        for (Vaccine vaccine : vaccines) {
            vaccine.display();
        }
        System.out.println("_________________________________");

    }

    public void printStudentList() {
        System.out.println("_________________________________");
        System.out.printf("|%-10s|%-20s|\n", "STUDENT ID", "NAME");
        for (Student student : students) {
            student.display();
        }
        System.out.println("_________________________________");

    }

    public void printAllInjectionList() {
        if (injections.isEmpty()) {
            System.out.println(MyToys.getRedColor("This Injection List is empty!!"));
        } else {
            System.out.println("_____________________________________________________________________________________________________________");

            //header-----
            System.out.println(getHeader());

            for (Injection injection : injections) {
                injection.display();
            }
            System.out.println("_____________________________________________________________________________________________________________");
        }
    }

    public void addNewInjection() {
        int choice;
        do {
            String injId, stuId, vacId, place, date;
            //INJECTION ID
            if (injections.size() == students.size()) {
                System.out.println(MyToys.getRedColor("There is no more student to add"));
            } else {
                do {
                    injId = MyToys.getString("+ Input Injection's ID: ", "Injection's ID cannot be empty!!\nPlease try again!~~~");
                    if (getInjectionById(injId) != null) {
                        System.out.println(MyToys.getRedColor("This Injection's ID existed!!\nPlease try again!~~~"));
                    }
                } while (getInjectionById(injId) != null);

                //STUDENT ID
                this.printStudentList();
                do {
                    stuId = MyToys.getString("+ Input Student's ID: ", "Student's ID cannot be empty!!\nPlease try again!~~~");
                    if (getStudentById(stuId) == null) {
                        System.out.println(MyToys.getRedColor("This Student's ID didn't existed!!\nPlease try again!~~~"));
                    }
                    if (getInjectionByStudentId(stuId) != null) {
                        System.out.println(MyToys.getRedColor("This Student was injected!!\nPlease try again!~~~"));
                    }
                } while (getStudentById(stuId) == null || getInjectionByStudentId(stuId) != null);

                //VACCINE ID
                this.printVaccineList();
                do {
                    vacId = MyToys.getString("+ Input Vaccine's ID: ", "Student's ID cannot be empty!!\nPlease try again!~~~");
                    if (getVaccineById(vacId) == null) {
                        System.out.println("This Vaccine's ID didn't exist!!\nPlease try again!~~~");
                    }
                } while (getVaccineById(vacId) == null);

                //PLACE
                do {
                    place = MyToys.getString("+ Input First Injection's Place (Province/City): ",
                            "Place cannot be empty!!\nPlease try again!~~~");
                    if ((place = getProvince(place)) == null) {
                        System.out.println(MyToys.getRedColor("Input place failed!!\nPlease try again!~~~"));
                    } else {
                        break;
                    }
                } while (true);

                //DATE
                do {
                    date = MyToys.getDate("+ Input Date of First Injection (dd/MM/yyyy): ", "Date must match with the format (dd/MM/yyyy)!!!");
                    if (!isFirstDateValid(date)) {
                        System.out.println(MyToys.getRedColor("This Date is INVALID!! "
                                + "(Date of First Injection must be after 08/03/2021 and before/equals today)\nPlease try again!~~~"));
                    }
                } while (!isFirstDateValid(date));

                //ADD
                injections.add(new Injection(injId, stuId, vacId, place, date));
                System.out.println(MyToys.getGreenColor("Added succesfully!!"));
                //CHOICE
            }
            System.out.println("\n--------------------------------");
            choice = MyToys.getInteger(0, 1, "Do you want to continue adding another Injection??\n"
                    + "0: NO    1: YES\n"
                    + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");

        } while (choice == 1);
    }

    // chưa check ngaày hợp lệ!! 
    // chưa thông báo warn đã full 2 mũi
    public void updateInjection() {
        int choice = 0;
        do {
            if (injections.isEmpty()) {
                System.out.println(MyToys.getRedColor("This Injection List is empty!!"));
            } else {
                printAllInjectionList();
                System.out.println("\n--------------------------------");
                String id = MyToys.getString("+ Input Injection's ID: ", "Injection's ID cannot be empty!!\nPlease try again!~~~");
                Injection inj = getInjectionById(id);
                if (inj != null) {
                    if (Math.abs(MyToys.countDaysTillNow(inj.getFiDate())) > (4 * 7)) {
                        System.out.println("_____________________________________________________________________________________________________________");
                        //header---
                        System.out.println(getHeader());
                        inj.display();
                        System.out.println("_____________________________________________________________________________________________________________");
                        if ((inj.getSeDate() == null && inj.getSePlace() == null) || (inj.getSeDate().equals("null") && inj.getSePlace().equals("null"))) {
                            String place;
                            do {
                                place = MyToys.getString("+ Input Second Injection's Place (Province/City): ",
                                        "Place cannot be empty!!\nPlease try again!~~~");
                                if ((place = getProvince(place)) == null) {
                                    System.out.println(MyToys.getRedColor("Input place failed!!\nPlease try again!~~~"));
                                } else {
                                    inj.setSePlace(place);
                                    break;
                                }
                            } while (true);
                            String date;
                            do {
                                date = MyToys.getDate("+ Input Date of Second Injection (dd/MM/yyyy): ", "Date must match with the format (dd/MM/yyyy)!!!");
                                if (!isSecondDateValid(inj.getFiDate(), date)) {
                                    System.out.println(MyToys.getRedColor("This Date is INVALID!! "
                                            + "(Date of Second Injection must be given 4 to 12 weeks after the First Injection and before/equals today)"));
                                }
                            } while (!isSecondDateValid(inj.getFiDate(), date));
                            inj.setSeDate(date);
                            System.out.println(MyToys.getGreenColor("Updated successfully!!"));
                        } else {
                            System.out.println(MyToys.getGreenColor("This Injection had been completed!!~~"));
                        }
                    } else {
                        System.out.println(MyToys.getRedColor("This Injection is not due for the Second shot"));
                    }
                } else {
                    System.out.println(MyToys.getRedColor("This Injection's ID did not exist!!~~"));
                }
                //CHOICE
                System.out.println("\n--------------------------------");
                choice = MyToys.getInteger(0, 1, "Do you want to continue updating another Injection??\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");
            }
        } while (choice == 1);
    }

    public void deleteInjection() {
        if (injections.isEmpty()) {
            System.out.println(MyToys.getRedColor("This Injection List is empty!!"));
        } else {
            printAllInjectionList();
            String id = MyToys.getString("+ Input Injection's ID: ", "Injection's ID cannot be empty!!\nPlease try again!~~~");
            Injection inj = getInjectionById(id);
            if (inj == null) {
                System.out.println(MyToys.getRedColor("This Injection's ID did not exist!!~~"));
            } else {
                System.out.println("_____________________________________________________________________________________________________________");
                System.out.println(getHeader());
                inj.display();
                System.out.println("_____________________________________________________________________________________________________________");
                int choice = MyToys.getInteger(0, 1, "Are you sure to remove this Injection?:\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");
                if (choice == 1) {
                    injections.remove(inj);
                    System.out.println(MyToys.getGreenColor("Removed successfully!!!~~~"));
                } else {
                    System.out.println(MyToys.getRedColor("Remove failed!!!~~~"));
                }
            }
        }
    }

    //Search
    public void searchInjectionByStudentId() {
        int choice = 0;
        do {
            if (injections.isEmpty()) {
                System.out.println(MyToys.getRedColor("This Injection List is empty!!"));
            } else {
                String id = MyToys.getString("+ Input Student's ID: ", "Student's ID cannot be empty!!\nPlease try again!~~~");
                Injection inj = getInjectionByStudentId(id);
                if (inj != null) {
                    System.out.println("_____________________________________________________________________________________________________________");
                    //header-----
                    System.out.println(getHeader());
                    inj.display();
                    System.out.println("_____________________________________________________________________________________________________________");
                } else {
                    System.out.println(MyToys.getRedColor("This Student didn't exist in Injection List!!~~"));
                }
                //CHOICE
                System.out.println("\n--------------------------------");
                choice = MyToys.getInteger(0, 1, "Do you want to continue search another Injection??\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");
            }
        } while (choice == 1);
    }
    
    public void searchInjectionByVaccineName() {
        int choice = 0;
        do {
            if (injections.isEmpty()) {
                System.out.println(MyToys.getRedColor("This Injection List is empty!!"));
            } else {
                String vaccine = MyToys.getString("+ Input Vaccine's Name: ", "Vaccine's Name cannot be empty!!\nPlease try again!~~~");
                ArrayList<Injection> inj = getInjectionByVaccineName(vaccine);
                if (inj.size() != 0) {
                    System.out.println("_____________________________________________________________________________________________________________");
                    //header-----
                    System.out.println(getHeader());
                    for (Injection injection : inj) {
                        injection.display();
                    }
                    System.out.println("_____________________________________________________________________________________________________________");
                } else {
                    System.out.println(MyToys.getRedColor("This Vaccine didn't exist in Injection List!!~~"));
                }
                //CHOICE
                System.out.println("\n--------------------------------");
                choice = MyToys.getInteger(0, 1, "Do you want to continue search another Injection??\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");
            }
        } while (choice == 1);
    }
    
    
    public ArrayList<Injection> getInjectionByVaccineName(String vaccine) {
        ArrayList<Injection> inj = new ArrayList();
        for (Injection injection : injections) {
            if (injection.getVaccineNameById(injection.getVacId()).toUpperCase().contains(vaccine.toUpperCase())) {
                inj.add(injection);
            }
        }
        return inj;
    }
    
    
    public Injection getInjectionById(String injId) {
        for (Injection injection : injections) {
            if (injection.getInjId().equalsIgnoreCase(injId)) {
                return injection;
            }
        }
        return null;
    }

    public Injection getInjectionByStudentId(String stuId) {
        for (Injection injection : injections) {
            if (injection.getStuId().equalsIgnoreCase(stuId)) {
                return injection;
            }
        }
        return null;
    }

    public Student getStudentById(String stuId) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(stuId)) {
                return student;
            }
        }
        return null;
    }

    public Vaccine getVaccineById(String vacId) {
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getId().equalsIgnoreCase(vacId)) {
                return vaccine;
            }
        }
        return null;
    }

    public boolean isFirstDateValid(String date) {
        return ((MyToys.compareDate(date, THE_FIRST_VACCINE) > 0) && (MyToys.compareDateToNow(date) <= 0));
    }

    public boolean isSecondDateValid(String fiDate, String seDate) {
        int days = MyToys.countDaysBetween(seDate, fiDate);
        return (days > 4 * 7 && days < 12 * 7 && (MyToys.compareDateToNow(seDate) <= 0));
    }

    public String getProvince(String str) {
        int count = 0;

        if (str.equalsIgnoreCase("HCM")) {
            return "HO CHI MINH";
        }
        if (str.length() > 1) {
            for (String province : provinces) {
                if (str.equalsIgnoreCase(province)) {
                    return province.toUpperCase();
                }
                count++;
            }
        }
        ArrayList<String> suggest = new ArrayList();
        if (count != 0) {
            boolean flag = true;
            for (String province : provinces) {
                if (province.contains(str.toUpperCase())) {
                    suggest.add(province);
                    flag = false;
                }
            }
            if (flag) {
                String[] tokens = str.split(" ");
                for (String token : tokens) {
                    if (token.length() > 1) {
                        for (String province : provinces) {
                            if (province.contains(token.toUpperCase())) {
                                for (String item : suggest) {
                                    if (province.equalsIgnoreCase(item)) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    suggest.add(province);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!suggest.isEmpty()) {

            int size = suggest.size();
            System.out.println("-----------------------------------------------");
            System.out.printf("| Did you mean (Choose 1~%-2d)                  |\n", size + 1);
            for (int i = 0; i < size; i++) {
                System.out.printf("| %2d. %-40s|\n", (i + 1), suggest.get(i));
            }
            System.out.printf("| %2d. %-40s|\n", (size + 1), "I didn't mean any of the above!");
            System.out.println("-----------------------------------------------");
            int choice = MyToys.getInteger(1, size + 1, "Enter your choice: ");
            if (choice == (size + 1)) {
                return null;
            }
            return suggest.get(choice - 1);
        }
        return null;
    }

    public String getHeader() {
        return ANSI_CYAN + "| INJ ID |STUDENT ID|    STUDENT NAME    | VACCINE NAME |  1ST PLACE  |  1ST DATE  |  2ND PLACE  |  2ND DATE  |" + ANSI_RESET;
    }


    public void readFile(String fileName) {
        FileReader fr = null;
        BufferedReader r = null;
        String line = "";
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                System.out.println(MyToys.getRedColor("File doesn't exist!!!"));
            }
            fr = new FileReader(f);
            r = new BufferedReader(fr);
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                String[] a = line.split(";");
                injections.add(new Injection(a[0], a[1], a[3], a[5], a[6], a[7], a[8]));
            }
            fr.close();
            r.close();
        } catch (Exception e) {
            System.out.println(MyToys.getRedColor(e.getMessage()));
        }
    }

    public void saveFile(String fileName) {

        PrintWriter w = null;
        try {
            w = new PrintWriter(fileName);
            for (Injection inj : injections) {
                w.println(inj);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {;
            }

        }
        System.out.println("Your file " + fileName + " was saved successfully");
    }
}
