package com.journaldev.spring.dao;

import com.journaldev.model.Menu;
import com.journaldev.model.Order;

import java.util.List;

public interface MenuDao {
    Menu getDishById(Integer id);

    List<Menu> getWholeMenu();

    int addDish(Menu menu);

    int deleteDishById(Integer id);

    int updateDish(Menu menu);
}
