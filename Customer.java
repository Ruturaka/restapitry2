package com.journaldev.model;

public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String number;
    private Integer bill;

    private Integer orderid;
    private String dish;
    private Integer qunt;
    private Integer price;




    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(String name, String email, String number) {
        this.name=name;
        this.email=email;
        this.number=number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
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

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", bill=" + bill +
                '}';
    }

}
