package com.example.productsnew.rest;

import com.example.productsnew.entity.Product;

import java.util.List;

public interface ProductsImpl {
    List<Product> findAll();

    Product findById(int id);

    Product save (Product theProduct);

    void deletebyId(int id);
}
