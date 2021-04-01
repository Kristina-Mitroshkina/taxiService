package ua.kharkov.epam.mitroshkina.taxiService.db.bean;

public class ConfirmedOrderBean {
    private int confirmedOrder_id;
    private int clientOrder_id;
    private int user_id;
    private String departure_address;
    private String destination_address;
    private int passengers_number;
    private int car_id;
    private String category_name;
    private String brand;
    private String model;
    private String car_number;
    private String driver_name;
    private String driver_surname;
    private int cost;
    private int discount_cost;
    private String order_date;
    private String status;


    public int getConfirmedOrder_id() {
        return this.confirmedOrder_id;
    }

    public int getClientOrder_id() { return this.clientOrder_id; }

    public int getUser_id() { return this.user_id; }

    public String getDeparture_address() {
        return this.departure_address;
    }

    public String getDestination_address() {
        return this.destination_address;
    }

    public int getPassengers_number() {
        return this.passengers_number;
    }

    public int getCar_id() { return this.car_id; }

    public String getCategory_name() {
        return this.category_name;
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

    public String getDriver_name() {
        return this.driver_name;
    }

    public String getDriver_surname() {
        return this.driver_surname;
    }

    public int getCost() {
        return this.cost;
    }

    public int getDiscount_cost() {
        return this.discount_cost;
    }

    public String getOrder_date() {
        return this.order_date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setConfirmedOrder_id(int confirmedOrder_id) {
        this.confirmedOrder_id = confirmedOrder_id;
    }

    public void setClientOrder_id(int clientOrder_id) {
        this.clientOrder_id = clientOrder_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setDeparture_address(String departure_address) {
        this.departure_address = departure_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public void setPassengers_number(int passengers_number) {
        this.passengers_number = passengers_number;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public void setDriver_surname(String driver_surname) {
        this.driver_surname = driver_surname;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDiscount_cost(int discount_cost) {
        this.discount_cost = discount_cost;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + confirmedOrder_id + "," + clientOrder_id + "," + departure_address + "," + destination_address + "," + passengers_number + "," + category_name + "," + brand + "," + model + "," + car_number  + "," + driver_name + "," + driver_surname + "," + cost + "," + discount_cost + "]";
    }
}
