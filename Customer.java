package com.journaldev.model;

public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String number;
    private Integer bill;

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
