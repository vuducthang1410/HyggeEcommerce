package org.vdt.productmanagementservice.services.impl;

import org.springframework.stereotype.Service;
import org.vdt.productmanagementservice.entities.Products;

@Service
public class ProductServiceImpl {
    public String saveProduct(Products product) {
        Products newProduct = new Products();
        newProduct.setName(product.getName());
        return "hehe";
    }
}
