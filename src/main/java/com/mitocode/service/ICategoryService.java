package com.mitocode.service;

import com.mitocode.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer> {

    /*Category save(Category category);
    Category update(Integer id, Category category);
    List<Category> findAll();
    Category findById(Integer id);
    void delete(Integer id);*/
    //public Category validAndGet(Integer id);

    List<Category> findByName(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> getNameDescription1(String name, String desc);
    List<Category> getNameDescription2(String name, String desc);
    List<Category> getNameSQL(String name);
}
