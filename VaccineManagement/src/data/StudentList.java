/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.util.ArrayList;
import util.MyToys;

/**
 *
 * @author User
 */
public class StudentList extends ArrayList<Student> implements IFiles{

    public StudentList() {
    }

    
    public StudentList(String fileName) {
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
                this.add(new Student(a[0], a[1]));
            }
            fr.close();
            r.close();
        } catch (Exception e) {
            System.out.println(MyToys.getRedColor(e.getMessage()));
        }
    }
}
