package com.journaldev.model;

public class Order {
    private Integer orderid;
    private Integer id;
    private String dish;
    private Integer qunt;
    private Integer price;
    private Integer amnt;

    public Order() {
    }

    public Order(Integer id, String dish, Integer qunt) {
        this.id = id;
        this.dish = dish;
        this.qunt=qunt;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public Integer getQunt() {
        return qunt;
    }

    public void setQunt(Integer qunt) {
        this.qunt = qunt;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmnt() {
        return amnt;
    }

    public void setAmnt(Integer amnt) {
        this.amnt = amnt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", id=" + id +
                ", dish='" + dish + '\'' +
                ", qunt=" + qunt +
                '}';
    }
}
