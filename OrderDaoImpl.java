package com.journaldev.spring.dao;

import com.journaldev.model.Customer;
import com.journaldev.model.CustomerMapper;
import com.journaldev.model.Order;
import com.journaldev.model.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Order getOrderById(Integer orderid) {
        return jdbcTemplate.queryForObject("select * from orders where orderid = ?", new Object[]{orderid}, new OrderMapper());

    }

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query("SELECT * FROM orders ORDER BY orderid", new OrderMapper());

    }

    @Override
    public List<Order> findByDishContaining(String dish) {
        String q = "SELECT * from orders WHERE dish ILIKE '%" + dish + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public int addOrder(Order order) {
        return jdbcTemplate.update("INSERT INTO orders(id, dish, qunt) VALUES(?,?,?)", order.getId(), order.getDish(), order.getQunt());

    }

    @Override
    public int deleteOrderById(Integer id) {
        return jdbcTemplate.update("delete from orders where id=?",id);
    }

    @Override
    public int updateOrder(Order order) {
        return jdbcTemplate.update("update orders set dish=?, qunt=? where orderid=?", new Object[] {order.getDish(), order.getQunt(), order.getOrderid()});

    }

}
