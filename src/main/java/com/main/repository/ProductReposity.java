package com.main.repository;

import com.main.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductReposity extends ListCrudRepository<Product, Long> {
}
