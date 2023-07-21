package com.caito.products_service.repository;

import com.caito.products_service.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
    boolean existsByName(String name);
}
