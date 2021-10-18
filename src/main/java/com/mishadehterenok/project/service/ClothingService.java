package com.mishadehterenok.project.service;

import com.mishadehterenok.project.entity.Clothing;
import com.mishadehterenok.project.entity.Size;
import com.mishadehterenok.project.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClothingService{

    private final ClothingRepository clothingRepository;

    @Autowired
    public ClothingService(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    public int countAll(){
        return (int) clothingRepository.count();
    }

    public Clothing findById(Long id){
        return clothingRepository.findById(id).get();
    }

    //выборка по всей одежде
    public List<Clothing> findAllByOrderByPriceAsc(){
        return clothingRepository.findAllByOrderByPriceAsc();
    }
    public List<Clothing> findAllByOrderByPriceDesc(){
        return clothingRepository.findAllByOrderByPriceDesc();
    }


    //выборка по категории
    public List<Clothing> findAllByCategoryNameOrderByPriceAsc(String name){
        CategoryService categoryService = new CategoryService();
        return clothingRepository.findAllByCategoriesContainingOrderByPriceAsc(categoryService.findByName(name));
    }
    public List<Clothing> findAllByCategoryNameOrderByPriceDesc(String name){
        CategoryService categoryService = new CategoryService();
        return clothingRepository.findAllByCategoriesContainingOrderByPriceDesc(categoryService.findByName(name));
    }


    //выборка по бренду
    public List<Clothing> findAllByBrandNameOrderByPriceAsc(String name){
        return clothingRepository.findAllByBrand_NameOrderByPriceAsc(name);
    }
    public List<Clothing> findAllByBrandNameOrderByPriceDesc(String name){
        return clothingRepository.findAllByBrand_NameOrderByPriceDesc(name);
    }


    //выборка по размеру
    public List<Clothing> findAllBySizeNameOrderByPriceAsc(String sizeName){
        return clothingRepository.findAllBySizesContainingOrderByPriceAsc(Size.valueOf(sizeName));
    }
    public List<Clothing> findAllBySizeNameOrderByPriceDesc(String sizeName){
        return clothingRepository.findAllBySizesContainingOrderByPriceDesc(Size.valueOf(sizeName));
    }


    //методы для строки поиска по названию
    public List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceAsc(String st){
        return clothingRepository.findAllByNameIgnoreCaseContainingOrderByPriceAsc(st);
    }
    public List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceDesc(String st){
        return clothingRepository.findAllByNameIgnoreCaseContainingOrderByPriceDesc(st);
    }



}
