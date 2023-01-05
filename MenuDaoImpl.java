package com.journaldev.spring.dao;

import com.journaldev.model.Menu;
import com.journaldev.model.MenuMapper;
import com.journaldev.model.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MenuDaoImpl implements MenuDao{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Menu getDishById(Integer id) {
        return jdbcTemplate.queryForObject("select * from menu where id = ?", new Object[]{id}, new MenuMapper());

    }

    @Override
    public List<Menu> getWholeMenu() {
        return jdbcTemplate.query("SELECT * FROM menu ORDER BY id", new MenuMapper());

    }

    @Override
    public int addDish(Menu menu) {
        return jdbcTemplate.update("INSERT INTO menu(id, dish, price) VALUES(?,?,?)", menu.getId(), menu.getDish(), menu.getPrice());

    }

    @Override
    public int deleteDishById(Integer id) {
        return jdbcTemplate.update("delete from menu where id=?",id);

    }

    @Override
    public int updateDish(Menu menu) {
        return jdbcTemplate.update("update menu set dish=?, price=? where id=?", new Object[] {menu.getDish(), menu.getPrice(), menu.getId()});

    }
}
