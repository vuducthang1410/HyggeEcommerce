package org.vdt.productmanagementservice.services.impl;

import org.springframework.stereotype.Service;
import org.vdt.productmanagementservice.entities.Product;

@Service
public class ProductServiceImpl {
    public String saveProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        return "hehe";
    }
}
