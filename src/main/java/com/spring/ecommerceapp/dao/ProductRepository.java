package com.spring.ecommerceapp.dao;

import com.spring.ecommerceapp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface ProductRepository extends JpaRepository<Product, Long> {
    // search Product by Category
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    // search Product by name
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
