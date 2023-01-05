package com.journaldev.spring.dao;

import com.journaldev.model.Customer;
import com.journaldev.model.CustomerMapper;
import com.journaldev.spring.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Customer getCustomerById(Integer id) {
        return jdbcTemplate.queryForObject("select * from customers where id = ?", new Object[]{id}, new CustomerMapper());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query("SELECT * FROM customers ORDER BY id", new CustomerMapper());
    }

    @Override
    public List<Customer> CustOrderInfo() {
        return jdbcTemplate.query("SELECT customers.id, orderid, name, number, dish, qunt, amnt, bill FROM customers INNER JOIN orders ON customers.id=orders.id ORDER BY orderid", new CustomerMapper());
    }

    @Override
    public int createCustomer(Customer customer) {
        return jdbcTemplate.update("INSERT INTO customers(name, email,number) VALUES(?,?,?)", customer.getName(), customer.getEmail(), customer.getNumber());
    }

    @Override
    public  int updateCustomer(Customer customer) {
        return jdbcTemplate.update("update customers set name=?, email=?, number=? where id=?", new Object[] {customer.getName(), customer.getEmail(), customer.getNumber(), customer.getId()});
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete from customers where id=?",id);
    }


}
