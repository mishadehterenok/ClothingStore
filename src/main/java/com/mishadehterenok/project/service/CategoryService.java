package com.mishadehterenok.project.service;

import com.mishadehterenok.project.entity.Category;
import com.mishadehterenok.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements BaseService<Category>{

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll(){
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
}
