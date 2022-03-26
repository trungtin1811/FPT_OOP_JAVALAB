/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import data.Refrigerator;
import util.MyToys;

/**
 *
 * @author User
 */
public class FoodManagement {
    public static void main(String[] args) {
        int choice;
        Refrigerator refrige = new Refrigerator();
        refrige.importData();
        do {
            refrige.printMenu();
            choice = MyToys.getInteger(1, 5, "Enter your choice: ");
            switch (choice) {
                case 1: {
                    refrige.addFood();
                    break;
                }
                case 2: {
                    refrige.searchFoodByWeight();
                    break;
                }
                case 3: {
                    refrige.removeFoodById();
                    break;
                }
                case 4: {
                    refrige.printDescendingOfDate();
                    break;
                }
                case 5: {
                    refrige.writeFoodsListToTextFile("Food.txt");
                    System.out.println("Bye bye!!!~~\nSee you again!!~~~");
                    break;
                }
            }
        } while (choice != 5);
    }
    
}
