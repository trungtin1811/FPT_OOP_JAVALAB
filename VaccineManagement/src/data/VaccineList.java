/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import util.MyToys;

/**
 *
 * @author User
 */
public class VaccineList extends ArrayList<Vaccine> implements IFiles {

    public VaccineList() {

    }

    public VaccineList(String fileName) {
//        this.add(new Vaccine("2", "Sinopharm"));
//        this.add(new Vaccine("4", "AstraZeneca"));
//        this.add(new Vaccine("6", "Gam-COVID-Vac"));
//        this.add(new Vaccine("7", "Janssen"));
//        this.add(new Vaccine("9", "Morderna"));
//        this.add(new Vaccine("10", "Pfizer"));
        this.readFile(fileName);
    }
    @Override
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
                this.add(new Vaccine(a[0], a[1]));
            }
            fr.close();
            r.close();
        } catch (Exception e) {
            System.out.println(MyToys.getRedColor(e.getMessage()));
        }
    }
}
