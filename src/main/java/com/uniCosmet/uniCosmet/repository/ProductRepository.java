package com.uniCosmet.uniCosmet.repository;

import com.uniCosmet.uniCosmet.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
