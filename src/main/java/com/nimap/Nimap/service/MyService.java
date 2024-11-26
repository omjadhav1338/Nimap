package com.nimap.Nimap.service;

import com.nimap.Nimap.entity.Category;
import com.nimap.Nimap.entity.Product;
import com.nimap.Nimap.repository.CategoryRepository;
import com.nimap.Nimap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Category saveCategory(Category category) {
        List<Product> products = category.getProductList();
        for(Product product:products){
            product.setCategory(category);
        }
        return categoryRepository.save(category);
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Page<Category> getPaginatedCategories(int page , int size) {
        return categoryRepository.findAll(PageRequest.of(page , size));
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateCategoryById(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
        if(category.getCategoryName()!=null) {
            assert existingCategory != null;
            existingCategory.setCategoryName(category.getCategoryName());
        }
        else if(category.getProductList()!=null){
            List<Product> productList = category.getProductList();
            for(Product product : productList){
                Product existingProduct = productRepository.findById(product.getPid()).orElse(null);
                if(product.getProductName()!=null) {
                    assert existingProduct != null;
                    existingProduct.setProductName(product.getProductName());
                }
            }
        }
        assert existingCategory != null;
        return categoryRepository.save(existingCategory);
    }

    public String deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return "Record deleted successfully";
    }

    public Page<Product> getPaginatedProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page,size));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String deleteProductById(Long id) {
        productRepository.deleteById(id);
        return "Record deleted successfully";
    }

    public Product saveProduct(Product product) {
        if (product.getCategory() != null) {
            Category existingCategory = categoryRepository.findById(product.getCategory().getCid()).orElseThrow(()->new RuntimeException("Category not found"));
            product.setCategory(existingCategory);
        } else {
            System.out.println("Please provide category Id");
        }
        return productRepository.save(product);
    }

    public Product updateProductById(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found"));
        if(product.getProductName()!=null)
            existingProduct.setProductName(product.getProductName());
        return productRepository.save(existingProduct);
    }
}
