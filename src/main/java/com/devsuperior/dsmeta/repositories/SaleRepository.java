package com.devsuperior.dsmeta.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT s FROM Sale s " +
            "JOIN FETCH s.seller " +
            "WHERE s.date >= :minDate " +
            "AND s.date <= :maxDate " +
            "AND UPPER(s.seller.name) " +
            "LIKE UPPER(CONCAT('%', :name, '%'))",
           countQuery = "SELECT COUNT(s) FROM Sale s " +
                   "JOIN s.seller " +
                   "WHERE s.date >= :minDate " +
                   "AND s.date <= :maxDate " +
                   "AND UPPER(s.seller.name) " +
                   "LIKE UPPER(CONCAT('%', :name, '%'))"
    )
    Page<Sale> searchAllWithSeller(@Param("minDate") LocalDate minDate,
                                   @Param("maxDate") LocalDate maxDate,
                                   @Param("name") String name,
                                   Pageable pageable);


}
