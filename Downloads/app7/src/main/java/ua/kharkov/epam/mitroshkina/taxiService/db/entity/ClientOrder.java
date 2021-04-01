package ua.kharkov.epam.mitroshkina.taxiService.db.entity;

public class ClientOrder extends Entity {

    private int id;
    private int user_id;
    private int departure_address;
    private int destination_address;
    private int passengers_number;
    private int car_category;
    private String date;


    public ClientOrder() {
    }
    public ClientOrder(int user_id, int departure_address, int destination_address, int passengers_number, int car_category) {
        this.user_id = user_id;
        this.departure_address = departure_address;
        this.destination_address = destination_address;
        this.passengers_number = passengers_number;
        this.car_category = car_category;
        this.date=date;
    }
    public int getClientOrder_id() {
        return this.id;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public int getDeparture_address() {
        return this.departure_address;
    }
    public int getDestination_address() {
        return this.destination_address;
    }
    public int getPassengers_number() { return this.passengers_number; }
    public int getCar_category() {
        return this.car_category;
    }
    public String getDate() {
        return this.date;
    }
    public void setClientOrder_id(int clientOrder_id) {
        this.id = clientOrder_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setDeparture_address(int departure_address) {
        this.departure_address = departure_address;
    }
    public void setDestination_address(int destination_address) {
        this.destination_address = destination_address;
    }
    public void setPassengers_number(int passengers_number) { this.passengers_number = passengers_number; }
    public void setCar_category(int car_category) {
        this.car_category = car_category;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "[" + id + "," + user_id + "," + departure_address + "," + destination_address + "," + passengers_number + "," + car_category + "]";
    }
}
