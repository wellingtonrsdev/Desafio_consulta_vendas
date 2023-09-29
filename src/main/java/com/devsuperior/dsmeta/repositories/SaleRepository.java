package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;


public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query( "SELECT obj FROM Sale obj " +
            "WHERE obj.date BETWEEN :startDate AND :endDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
            "ORDER BY obj.date desc")
    Page<Sale> searchSales(LocalDate startDate, LocalDate endDate, String name, Pageable pageable);
    
    
    
    @Query( "SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) " +
    		"FROM Sale obj " + 
    		"WHERE obj.date BETWEEN :startDate AND :endDate " + 
    		"GROUP BY obj.seller.name " + 
    		"ORDER BY obj.seller.name")
    List<SaleSummaryDTO> searchSummary(LocalDate startDate, LocalDate endDate);

    @Query("SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj in :sales")
    List<Sale> searchSalesWithSellers(List<Sale> sales);
}
