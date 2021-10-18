package com.mishadehterenok.project.repository;

import com.mishadehterenok.project.entity.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {

    List<Brand> findAllByOrderById();
}
