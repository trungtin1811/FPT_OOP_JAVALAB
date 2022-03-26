/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author User
 */
public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id.toUpperCase();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vaccine{" + "id=" + id + ", name=" + name + '}';
    }
    
    public void display() {
        System.out.printf("|%-10s|%-20s|\n", id, name);
    }
}
