/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author User
 */
public class Injection {

    private String injId;
    private String stuId;
    private String vacId;
    private String fiPlace;
    private String fiDate;
    private String sePlace;
    private String seDate;

    public Injection(String injId, String stuId, String vacId, String fiPlace, String fiDate) {
        this.injId = injId.toUpperCase();
        this.stuId = stuId.toUpperCase();
        this.vacId = vacId.toUpperCase();
        this.fiPlace = fiPlace;
        this.fiDate = fiDate;
    }

    public Injection(String injId, String stuId, String vacId, String fiPlace, String fiDate, String sePlace, String seDate) {
        this.injId = injId.toUpperCase();
        this.stuId = stuId.toUpperCase();
        this.vacId = vacId.toUpperCase();
        this.fiPlace = fiPlace;
        this.fiDate = fiDate;
        this.sePlace = sePlace;
        this.seDate = seDate;
    }

    public String getInjId() {
        return injId;
    }

    public void setInjId(String injId) {
        this.injId = injId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getVacId() {
        return vacId;
    }

    public void setVacId(String vacId) {
        this.vacId = vacId;
    }

    public String getFiPlace() {
        return fiPlace;
    }

    public void setFiPlace(String fiPlace) {
        this.fiPlace = fiPlace;
    }

    public String getFiDate() {
        return fiDate;
    }

    public void setFiDate(String fiDate) {
        this.fiDate = fiDate;
    }

    public String getSePlace() {
        return sePlace;
    }

    public void setSePlace(String sePlace) {
        this.sePlace = sePlace;
    }

    public String getSeDate() {
        return seDate;
    }

    public void setSeDate(String seDate) {
        this.seDate = seDate;
    }

    @Override
    public String toString() {
        return injId + ";" + stuId + ";" + getStudentNameById(stuId) + ";" + vacId + ";" + getVaccineNameById(vacId) + ";" + fiPlace + ";" + fiDate + ";" + sePlace + ";" + seDate;
    }

    public void display() {
        System.out.printf("|%-8s|%-10s|%-20s|%-14s|%-13s|%-12s|%-13s|%-12s|\n",
                injId, stuId, getStudentNameById(stuId), getVaccineNameById(vacId), fiPlace, fiDate, sePlace, seDate);
    }

    public String getVaccineNameById(String id) {
        for (Vaccine vaccine : InjectionList.vaccines) {
            if (vaccine.getId().equalsIgnoreCase(id)) {
                return vaccine.getName();
            }
        }
        return null;
    }

    public String getStudentNameById(String id) {
        for (Student student : InjectionList.students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student.getName();
            }
        }
        return null;
    }
}
