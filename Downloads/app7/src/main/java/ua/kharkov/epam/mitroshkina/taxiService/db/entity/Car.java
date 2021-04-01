package ua.kharkov.epam.mitroshkina.taxiService.db.entity;

public class Car extends Entity {

    private int car_id;
    private int car_description_id;
    private String car_number;
    private int max_capacity;
    private int category_id;
    private int driver_id;
    private int state_id;

    public Car() {
    }

    private Car(int car_description_id, String car_number, int max_capacity, int category_id, int driver_id, int state_id) {
        this.car_description_id = car_description_id;
        this.car_number = car_number;
        this.max_capacity = max_capacity;
        this.category_id = category_id;
        this.driver_id = driver_id;
        this.state_id = state_id;
    }

    public int getCar_id() {
        return this.car_id;
    }

    public int getCar_description_id() {
        return this.car_description_id;
    }

    public String getCar_number() {
        return this.car_number;
    }

    public int getMax_capacity() {
        return this.max_capacity;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public int getDriver_id() {
        return this.driver_id;
    }

    public int getState_id() {
        return this.state_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public void setCar_description_id(int car_description_id) {
        this.car_description_id = car_description_id;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    @Override
    public String toString() {
        return "[" + car_id + "," + car_description_id + "," + car_number + "," + max_capacity + "," + category_id + "," + driver_id + "," + state_id + "]";
    }

}
