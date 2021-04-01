package ua.kharkov.epam.mitroshkina.taxiService.db.bean;

public class CarBean {
    private long car_id;
    private String brand;
    private String model;
    private String car_number;
    private int max_capacity;
    private String category_name;
    private String driver_name;
    private String driver_surname;
    private String state_name;


    public long getCar_id() {
        return this.car_id;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public String getCar_number() {
        return this.car_number;
    }

    public int getMax_capacity() {
        return this.max_capacity;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public String getDriver_name() {
        return this.driver_name;
    }

    public String getDriver_surname() {
        return this.driver_surname;
    }

    public String getState_name() {
        return this.state_name;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public void setDriver_surname(String driver_surname) {
        this.driver_surname = driver_surname;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    @Override
    public String toString() {
        return "[" + car_id + "," + brand + "," + model + "," + car_number + "," + max_capacity + "," + category_name + "," + driver_name + "," + driver_surname + "," + state_name + "]";
    }

}
