package com.journaldev.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order=new Order();
        Customer customer=new Customer(order.getId());
        order.setOrderid(rs.getInt("orderid"));
        order.setId(rs.getInt("id"));
        order.setDish(rs.getString("dish"));
        order.setQunt(rs.getInt("qunt"));
        order.setPrice(rs.getInt("price"));
        order.setAmnt(rs.getInt("amnt"));
        return order;
    }
}
