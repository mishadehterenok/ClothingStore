package com.mishadehterenok.project.controller;

import com.mishadehterenok.project.dto.BrandDto;
import com.mishadehterenok.project.dto.CategoryDto;
import com.mishadehterenok.project.entity.Brand;
import com.mishadehterenok.project.entity.Category;
import com.mishadehterenok.project.entity.Clothing;
import com.mishadehterenok.project.entity.Size;
import com.mishadehterenok.project.service.BrandService;
import com.mishadehterenok.project.service.CategoryService;
import com.mishadehterenok.project.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("sortType")
public class CatalogController {

    private final ClothingService clothingService;

    @Autowired
    public CatalogController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @ModelAttribute
    public void addCategoriesBrandsSizes(Model model) {
        int countOfAllClothing = clothingService.countAll();
        List<BrandDto> allBrandsDto = new BrandService().findAll().stream()
                .map(Brand::createDto).collect(Collectors.toList());
        List<CategoryDto> allCategoriesDto = new CategoryService().findAll().stream()
                .map(Category::createDto).collect(Collectors.toList());
        Size[] allSizes = Size.values();

        model.addAttribute("countOfAllClothing", countOfAllClothing);
        model.addAttribute("allBrands", allBrandsDto);
        model.addAttribute("allCategories", allCategoriesDto);
        model.addAttribute("allSizes", allSizes);
    }

    @GetMapping("/catalog?category=All&page=2")
    public ModelAndView showCatalogPageByCategories(@RequestParam() Map<String, String> allParams,
                                                    @SessionAttribute("sortType") String currentSortingType,
                                                    ModelAndView model) {
        if (allParams.containsKey("sortType")) {
            model.addObject("sortType", allParams.get("sortType"));
        }
        List<Clothing> clothingList = null;
        if (allParams.containsKey("category")) {
            String categoryName = allParams.get("category");
            if (categoryName.equals("All")) {
                clothingList = currentSortingType.equals("desc") ?
                        clothingService.findAllByOrderByPriceDesc() :
                        clothingService.findAllByOrderByPriceAsc();
            } else {
                clothingList = currentSortingType.equals("desc") ?
                        clothingService.findAllByCategoryNameOrderByPriceDesc(categoryName) :
                        clothingService.findAllByCategoryNameOrderByPriceAsc(categoryName);
            }
        } else if (allParams.containsKey("brand")) {
            String brandName = allParams.get("brand");
            clothingList = currentSortingType.equals("desc") ?
                    clothingService.findAllByBrandNameOrderByPriceDesc(brandName) :
                    clothingService.findAllByBrandNameOrderByPriceAsc(brandName);
        } else if (allParams.containsKey("size")) {
            String sizeName = allParams.get("size");
            clothingList = currentSortingType.equals("desc") ?
                    clothingService.findAllBySizeNameOrderByPriceDesc(sizeName) :
                    clothingService.findAllBySizeNameOrderByPriceAsc(sizeName);
        } else if (allParams.containsKey("search")) {
            String st = allParams.get("search");
            clothingList = currentSortingType.equals("desc") ?
                    clothingService.findAllByNameIgnoreCaseContainingOrderByPriceDesc(st) :
                    clothingService.findAllByNameIgnoreCaseContainingOrderByPriceAsc(st);
        }
        return clothingList != null ? getModelAndViewForCatalogPage(
                Integer.parseInt(allParams.get("page")), model, clothingList) :
                new ModelAndView("/error404");
    }

    private ModelAndView getModelAndViewForCatalogPage(@RequestParam("page") int pageNumber,
                                                       ModelAndView model,
                                                       List<Clothing> clothingList) {
        int listSize = clothingList.size();
        int numberOfPages = listSize % 6 == 0 ? listSize / 6 : listSize / 6 + 1;
        if (pageNumber > numberOfPages) {
            return new ModelAndView("/error404");
        }
        List<Clothing> resultList = getClothingListAccordingToPageNumber(pageNumber, clothingList);

        model.addObject("numberOfPages", numberOfPages);
        model.addObject("resultList", resultList);
        model.setViewName("catalog");
        return model;
    }

    private List<Clothing> getClothingListAccordingToPageNumber(int pageNumber, List<Clothing> clothingList) {
        int listSize = clothingList.size();
        int numberOfPages = listSize % 6 == 0 ?
                listSize / 6 : (listSize / 6) + 1;
        int indexOfFirstElement = (pageNumber - 1) * 6;
        List<Clothing> resultList = new ArrayList<>();
        if (pageNumber == numberOfPages) {
            for (int i = indexOfFirstElement; i < listSize; i++) {
                resultList.add(clothingList.get(i));
            }
        } else {
            for (int i = indexOfFirstElement; i < indexOfFirstElement + 6; i++) {
                resultList.add(clothingList.get(i));
            }
        }
        return resultList;
    }


}
