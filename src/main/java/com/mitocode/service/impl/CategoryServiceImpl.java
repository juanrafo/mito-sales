package com.mitocode.service.impl;

import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    //@Autowired
    private final ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByNameLikeIgnoreCase("%" + name + "%");
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled) {
        return repo.findByNameAndEnabled(name, enabled);
    }

    @Override
    public List<Category> getNameDescription1(String name, String desc) {
        return repo.getNameDescription1(name, desc);
    }

    @Override
    public List<Category> getNameDescription2(String name, String desc) {
        return repo.getNameDescription2(name, desc);
    }

    @Override
    public List<Category> getNameSQL(String name) {
        return repo.getNameSQL(name);
    }


    /*@Override
    public Category save(Category category) {
        return repo.save(category);
    }

    @Override
    public Category update(Integer id, Category category) {
        //VALIDAR POR ID
        category.setIdCategory(id);
        return repo.save(category);
    }

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return repo.findById(id).orElse(new Category());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/
    //private final double PI = 3.141516;

    /*public CategoryServiceImpl(ICategoryRepo repo) {
        this.repo = repo;
    }*/

    /*@Override
    public Category validAndGet(Integer id) {
        if (id != null && id > 0) {
            //repo = new CategoryRepo();
            return repo.getCategoryFromDB(id);
        } else {
            return new Category();
        }
    }*/
}
