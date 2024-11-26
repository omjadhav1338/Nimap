package com.nimap.Nimap.controller;

import com.nimap.Nimap.entity.Category;
import com.nimap.Nimap.entity.Product;
import com.nimap.Nimap.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService service;

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category){
        return service.saveCategory(category);
    }

    @GetMapping("/categories")
    public Page<Category> getPaginatedCategories(@RequestParam int page, @RequestParam int size){
        return service.getPaginatedCategories(page, size);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return service.getCategoryById(id);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategoryById(@PathVariable Long id, @RequestBody Category category){
        return service.updateCategoryById(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategoryById(@PathVariable Long id){
        return service.deleteCategoryById(id);
    }

    @GetMapping("/products")
    public Page<Product> getPaginatedProducts(@RequestParam int page, @RequestParam int size){
        return service.getPaginatedProducts(page, size);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return service.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductById(@PathVariable Long id){
        return service.deleteProductById(id);
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product){
        return service.updateProductById(id, product);
    }

}
