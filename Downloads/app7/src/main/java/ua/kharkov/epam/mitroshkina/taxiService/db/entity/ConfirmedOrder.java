package ua.kharkov.epam.mitroshkina.taxiService.db.entity;

public class ConfirmedOrder extends Entity{
    private int id;
    private int clientOrder_id;
    private int cost;
    private float discount_cost;
    private int car_id;
    private String status;



    public ConfirmedOrder() {
    }
    public ConfirmedOrder(int clientOrder_id, int cost, int discount_cost, int car_id, String status) {
        this.clientOrder_id = clientOrder_id;
        this.cost = cost;
        this.discount_cost = discount_cost;
        this.car_id=car_id;
        this.status=status;
    }
    public int getConfirmedOrder_id() {
        return this.id;
    }
    public int getClientOrder_id() {
        return this.clientOrder_id;
    }
    public int getCost() {
        return this.cost;
    }
    public float getDiscount_cost() {
        return this.discount_cost;
    }
    public int getCar_id() {
        return this.car_id;
    }
    public String getStatus() {
        return this.status;
    }
    public void setConfirmedOrder_id(int id) {
        this.id = id;
    }
    public void setClientOrder_id(int clientOrder_id) {
        this.clientOrder_id = clientOrder_id;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setDiscount_cost(float discount_cost) {
        this.discount_cost = discount_cost;
    }
    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "[" + id + "," + clientOrder_id + "," + cost + "," + discount_cost+ "," + car_id + "," + status + "]";
    }
}
