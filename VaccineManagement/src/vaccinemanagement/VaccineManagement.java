/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import data.InjectionList;
import util.MyToys;

/**
 *
 * @author User
 */
public class VaccineManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "injection.txt";
        int choice;
        InjectionList list = new InjectionList();
        list.readFiles("student.txt", "vaccine.txt", "province.txt", fileName);
        do {
            list.printMenu();
            choice = MyToys.getInteger(1, 6, "Enter your choice: ");
            switch (choice) {
                case 1: {
                    System.out.println("\n--------------------------------");
                    list.printAllInjectionList();
                    System.out.println("--------------------------------\n");
                    break;
                }
                case 2: {
                    System.out.println("\n--------------------------------");
                    list.addNewInjection();
                    System.out.println("--------------------------------\n");
                    break;
                }
                case 3: {
                    System.out.println("\n--------------------------------");
                    list.updateInjection();
                    System.out.println("--------------------------------\n");
                    break;
                }
                case 4: {
                    System.out.println("\n--------------------------------");
                    list.deleteInjection();
                    System.out.println("--------------------------------\n");
                    break;
                }
                case 5: {
                    System.out.println("\n--------------------------------");
                    list.searchInjectionByVaccineName();
                    System.out.println("--------------------------------\n");
                    break;
                }
                case 6: {
                    list.saveFile(fileName);
                    System.out.println(MyToys.getGreenColor("Your datas have been saved in *" + fileName + "*"));
                    System.out.println(MyToys.getGreenColor("Bye bye!!!~~\nSee you again!!~~~"));
                    break;
                }
            }
        } while (choice != 6);

    }

}
