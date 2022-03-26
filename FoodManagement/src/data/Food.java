package data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author User
 */
public class Food implements Comparable<Food> {

    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String date;

    public Food() {
    }

    public Food(String id, String name, double weight, String type, String place, String date) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.date = date;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Food{" + "id=" + id + ", name=" + name + ", weight=" + weight + ", type=" + type + ", place=" + place + ", date=" + date + '}';
    }

    public void display() {
        System.out.printf("|%-10s|%-30s|%6.2f|%-12s|%-20s|%-10s|\n", id, name, weight, type, place, date);

    }

    @Override
    public int compareTo(Food o) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return -dateFormat.parse(date).compareTo(dateFormat.parse(o.getDate()));
        } catch (Exception e) {
            return 9999;
        }

    }

}
