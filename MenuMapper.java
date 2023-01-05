package com.journaldev.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
        Menu menu=new Menu();
        menu.setId(rs.getInt("id"));
        menu.setDish(rs.getString("dish"));
        menu.setPrice(rs.getInt("price"));
        return menu;
    }
}
