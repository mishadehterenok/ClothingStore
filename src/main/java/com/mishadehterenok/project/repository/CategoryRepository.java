package com.mishadehterenok.project.repository;

import com.mishadehterenok.project.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAllByOrderById();

    Category findByName(String name);
}
