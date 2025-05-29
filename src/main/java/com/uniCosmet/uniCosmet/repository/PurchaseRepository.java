package com.uniCosmet.uniCosmet.repository;

import com.uniCosmet.uniCosmet.model.Purchase;
import com.uniCosmet.uniCosmet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser(User user);

    @Query("SELECT p FROM Purchase p WHERE p.user.id = :userId")
    List<Purchase> findByUserId(@Param("id") Long userId);
}
