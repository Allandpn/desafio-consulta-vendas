package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SellerSumaryProjection;
import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "SELECT s.name AS name, COALESCE(SUM(sa.amount), 0) AS totalAmount " +
            "FROM Seller s " +
            "JOIN s.sales sa " +
            "WHERE sa.date >= :minDate " +
            "AND sa.date <= :maxDate " +
            "GROUP BY s.name ",
    countQuery = "SELECT COUNT(DISTINCT s.id) " +
            "FROM Seller s " +
            "JOIN s.sales sa " +
            "WHERE sa.date >= :minDate " +
            "AND sa.date <= :maxDate "
    )
    Page<SellerSumaryProjection> searchSumary (@Param("minDate")LocalDate minDate,
                                               @Param("maxDate") LocalDate maxDate,
                                               Pageable pageable);
}
