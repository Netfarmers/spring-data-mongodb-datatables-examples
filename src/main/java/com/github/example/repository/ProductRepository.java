package com.github.example.repository;

import com.github.example.model.Product;
import org.springframework.data.mongodb.datatables.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends DataTablesRepository<Product, Long> {
}