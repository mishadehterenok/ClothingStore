package com.mishadehterenok.project.service;

import com.mishadehterenok.project.entity.Brand;
import com.mishadehterenok.project.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService implements BaseService<Brand>{

    private BrandRepository brandRepository;

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll(){
        return (List<Brand>) brandRepository.findAll();
    }

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public Brand findById(Long id){
        return brandRepository.findById(id).get();
    }
}
