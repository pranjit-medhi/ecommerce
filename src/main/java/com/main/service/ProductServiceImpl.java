package com.main.service;

import com.main.model.Product;
import com.main.repository.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductReposity productReposity;
    @Autowired
    public ProductServiceImpl(ProductReposity productReposity) {
        this.productReposity = productReposity;
    }

    @Override
    public List<Product> getAllProducts() {
        return productReposity.findAll();
    }
}
