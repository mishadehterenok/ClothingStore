package com.mishadehterenok.project.repository;

import com.mishadehterenok.project.entity.Category;
import com.mishadehterenok.project.entity.Clothing;
import com.mishadehterenok.project.entity.Size;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClothingRepository extends CrudRepository<Clothing, Long> {

    List<Clothing> findAllByOrderByPriceAsc();
    List<Clothing> findAllByOrderByPriceDesc();

    List<Clothing> findAllByCategoriesContainingOrderByPriceAsc(Category category);
    List<Clothing> findAllByCategoriesContainingOrderByPriceDesc(Category category);

    List<Clothing> findAllByBrand_NameOrderByPriceAsc(String name);
    List<Clothing> findAllByBrand_NameOrderByPriceDesc(String name);

    List<Clothing> findAllBySizesContainingOrderByPriceAsc(Size size);
    List<Clothing> findAllBySizesContainingOrderByPriceDesc(Size size);

    List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceAsc(String st);
    List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceDesc(String st);

}
