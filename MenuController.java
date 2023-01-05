package com.journaldev.controller;

import com.journaldev.model.Menu;
import com.journaldev.model.Order;
import com.journaldev.spring.dao.MenuDao;
import com.journaldev.spring.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MenuController {
    @Autowired
    MenuDao menuDao;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getWholeMenu(@RequestParam(required = false) String name){
        try {
            List<Menu> menu = new ArrayList<Menu>();

            if (name == null)
                menuDao.getWholeMenu().forEach(menu::add);
            if (menu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> getCustomerById(@PathVariable("id") Integer id) {
        Menu menu = menuDao.getDishById(id);

        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/menu")
    public ResponseEntity<String> createCustomer(@RequestBody Menu menu) {
        try {
            menuDao.addDish(new Menu(menu.getId(), menu.getDish(), menu.getPrice()));
            return new ResponseEntity<>("Dish created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer id) {
        try {
            int result = menuDao.deleteDishById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Order with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Dish deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/menu/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Integer id, @RequestBody Menu menu) {
        Menu _menu = menuDao.getDishById(id);

        if (_menu != null) {
            _menu.setDish(menu.getDish());
            _menu.setPrice(menu.getPrice());

            menuDao.updateDish(_menu);
            return new ResponseEntity<>("Dish updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Order with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
}
