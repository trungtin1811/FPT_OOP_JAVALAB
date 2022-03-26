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
public class ProvinceList extends ArrayList<String> implements IFiles {

    public ProvinceList(String fileName) {
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

                this.add(line);
            }
            fr.close();
            r.close();
        } catch (Exception e) {
            System.out.println(MyToys.getRedColor(e.getMessage()));
        }
    }
}
