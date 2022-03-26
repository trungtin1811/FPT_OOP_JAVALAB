/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.text.*;
import java.util.*;
import util.MyToys;

/**
 *
 * @author User
 */
public class Refrigerator {

    private List<Food> foods = new ArrayList<Food>();

    public Refrigerator() {
    }

    public void printMenu() {
        System.out.println("*********************************************************************************");
        System.out.println("*   Welcome to Food Management - © 2021 by < SE150116 - Nguyen Trung Tin >      *");
        System.out.println("*   Select the option bellow (1 ~ 5):                                           *");
        System.out.println("*   1. Add a new food.                                                          *");
        System.out.println("*   2. Search a food by weight.                                                 *");
        System.out.println("*   3. Remove the food by ID.                                                   *");
        System.out.println("*   4. Print the food list in the descending order of expired date.             *");
        System.out.println("*   5. Quit.                                                                    *");
        System.out.println("*********************************************************************************");
    }

    public void addFood() {
        int choice;
        do {
            System.out.println("\n--------------------------------");
            String id;
            do {
                id = MyToys.getString("+ Input Food's ID: ", "Your Food's ID cannot be empty!!\nPlease try again!~~~");
                if (getFoodIndexById(id) != -1) {
                    System.out.println("This Food's ID has existed!!\nPlease try again!~~~");
                }
            } while (getFoodIndexById(id) != -1);
            String name = MyToys.getString("+ Input Food's Name: ", "Food's Name cannot be empty!!!");
            double weight = MyToys.getDouble(0.0001, 30.0, "+ Input Food's Weight (kg): ", "Food's Weight must be > 0 and <= 30 (kg)!!");
            String type = MyToys.getString("+ Input Food's Type: ", "Food's Type cannot be empty!!!");
            //String place = MyToys.getString("+ Input Food's Place (where you put this food in refrigerator): ", "Food's Place cannot be empty!!!");
            String place = choosePlace();
            String date;
            do {
                date = MyToys.getString("+ Input Food's Expired Date (DD/MM/YYYY): ", "Food's Expired Date cannot be empty!!!");
                if (!MyToys.isValidDate(date)) {
                    System.out.println("Your Food's Expired Date is invalid!!\nPlease try again!~~~");
                }
            } while (!MyToys.isValidDate(date));
            if (MyToys.compareDate(date) >= 0) {
                if (MyToys.compareDate(date, 50) < 0) {
                    foods.add(new Food(id, name, weight, type, place, date));
                    System.out.println("Added successfully!!!~~~");
                    System.out.println("--------------------------------\n");
                } else {
                    System.out.println("Added fail!!!~~~\nYour Food over 50 years!!");
                    System.out.println("--------------------------------\n");
                }
            } else {
                System.out.println("Added fail!!!~~~\nYour Food was out-of-date");
                System.out.println("--------------------------------\n");
            }

            choice = MyToys.getInteger(0, 1, "Do you want to continue adding another food??\n"
                    + "0: NO    1: YES\n"
                    + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");
            System.out.println("--------------------------------\n");

        } while (choice == 1);

    }

    public void searchFoodByName() {

        if (foods.isEmpty()) {
            System.out.println("There is no food in your refrigerator!!");
        } else {
            int choice;
            do {
                System.out.println("\n--------------------------------");
                String searchTerm = MyToys.getString("+ Input Search Term: ", "Search Term cannot be empty!!!");
                int searchCount = 0;
                for (Food food : foods) {
                    if (food.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                        searchCount++;
                    }
                }
                if (searchCount > 0) {
                    System.out.printf("There is/are %d result(s) related with search tearm {%s}!!!\n", searchCount, searchTerm);
                    System.out.printf("|%-10s|%-30s|%6s|%-12s|%-20s|%-10s|\n", "ID", "NAME", "WEIGHT", "TYPE", "PLACE", "DATE");

                    for (Food food : foods) {
                        if (food.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                            food.display();
                        }
                    }
                } else {
                    System.out.println("There is no Food related with {" + searchTerm + "} in your refrigerator!!");
                }
                System.out.println("--------------------------------\n");
                choice = MyToys.getInteger(0, 1, "Do you want to continue searching for another food??:\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");

            } while (choice == 1);

        }
        System.out.println("--------------------------------\n");
    }

    public void searchFoodByWeight() {
        if (foods.isEmpty()) {
            System.out.println("There is no food in your refrigerator!!");
        } else {
            int choice;
            do {
                System.out.println("\n--------------------------------");
                double weight = MyToys.getDouble(0.0001, 30.0, "+ Input Food's Weight (kg): ", "Food's Weight must be > 0 and <= 30 (kg)!!");
                int searchCount = 0;
                for (Food food : foods) {
                    if (food.getWeight() <= weight) {
                        searchCount++;
                    }
                }
                if (searchCount > 0) {
                    System.out.printf("There is/are %d result(s) <= %.2f (kg)}!!!\n",searchCount, weight);
                    System.out.printf("|%-10s|%-30s|%6s|%-12s|%-20s|%-10s|\n", "ID", "NAME", "WEIGHT", "TYPE", "PLACE", "DATE");

                    for (Food food : foods) {
                        if (food.getWeight() <= weight) {
                            food.display();
                        }
                    }
                } else {
                    System.out.printf("There is no Food <= %.2f (kg)}!!!\n", weight);
                }
                System.out.println("--------------------------------\n");
                choice = MyToys.getInteger(0, 1, "Do you want to continue searching for another food??:\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");

            } while (choice == 1);

        }
        System.out.println("--------------------------------\n");
    }

    public void removeFoodById() {
        if (foods.isEmpty()) {
            System.out.println("There is no food in your refrigerator!!");
        } else {
            System.out.printf("|%-10s|%-30s|%6s|%-12s|%-20s|%-10s|\n", "ID", "NAME", "WEIGHT", "TYPE", "PLACE", "DATE");

            for (Food food : foods) {
                food.display();
            }
            System.out.println("\n--------------------------------");
            String id = MyToys.getString("+ Input Food's ID: ", "Food's ID cannot be empty!!!");
            int index = getFoodIndexById(id);
            if (index == -1) {
                System.out.println("There is no food matching with this ID!!");
            } else {
                System.out.printf("|%-10s|%-30s|%6s|%-12s|%-20s|%-10s|\n", "ID", "NAME", "WEIGHT", "TYPE", "PLACE", "DATE");
                foods.get(index).display();
                System.out.println("--------------------------------\n");
                int choice = MyToys.getInteger(0, 1, "Are you sure to remove this Food from your Refrigerator??:\n"
                        + "0: NO    1: YES\n"
                        + "Your choice: ", "You must input exaclly 0 or 1!!\nPlease try again!~~~\n");
                if (choice == 1) {
                    foods.remove(index);
                    System.out.println("Removed successfully!!!~~~");
                } else {
                    System.out.println("Remove failed!!!~~~");
                }

            }

        }
        System.out.println("--------------------------------\n");
    }

    public void printDescendingOfDate() {
        if (foods.isEmpty()) {
            System.out.println("There is no food in your refrigerator!!");
        } else {
            Collections.sort(foods);
            System.out.printf("|%-10s|%-30s|%6s|%-12s|%-20s|%-10s|\n", "ID", "NAME", "WEIGHT", "TYPE", "PLACE", "DATE");
            for (Food food : foods) {
                food.display();
            }
        }
        System.out.println("--------------------------------\n");
    }

    public int getFoodIndexById(String id) {
        if (foods.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void writeFoodsListToBinaryFile(String fileName) {
        FileOutputStream f = null;
        ObjectOutputStream of = null;
        try {
            f = new FileOutputStream(fileName);
            of = new ObjectOutputStream(f);
            of.writeObject(foods);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (f != null) {
                    f.close();
                }

                if (of != null) {
                    of.close();
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    public void writeFoodsListToTextFile(String fileName) {
        PrintWriter w = null;
        try {
            w = new PrintWriter(fileName);
            for (Food food : foods) {
                w.println(food);
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

    public String choosePlace() {
        int choice;
        System.out.println("+ Input Food's Place (where you put this food in refrigerator): ");
        System.out.println("    *********************************************");
        System.out.println("    *   Select the option bellow (1 ~ 5):       *");
        System.out.println("    *   1. Freezer.                             *");
        System.out.println("    *   2. Upper shelf.                         *");
        System.out.println("    *   3. Lower shelf.                         *");
        System.out.println("    *   4. Refrigerator door.                   *");
        System.out.println("    *********************************************");
        choice = MyToys.getInteger(1, 4, "Enter your choice: ");
        switch (choice) {
            case 1:
                return "Freezer";
            case 2:
                return "Upper shelf";
            case 3:
                return "Lower shelf";
            case 4:
                return "Refrigerator door";
            default:
                return "Undefined";
        }
    }

    public void importData() {
        foods.add(new Food("1111", "Rau Muong", 0.4, "Rau", "Upper Shelf", "18/11/2021"));
        foods.add(new Food("2222", "Thit Bo", 2, "Thit", "Freezer", "20/11/2021"));
        foods.add(new Food("3333", "Thit Heo", 1.6, "Thit", "Freezer", "12/09/2021"));

    }

}
