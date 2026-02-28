package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SalesReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SalesReportDTO() {
    }

    public SalesReportDTO(Long id, String sellerName, LocalDate date, Double amount) {
        this.id = id;
        this.sellerName = sellerName;
        this.date = date;
        this.amount = amount;
    }

    public SalesReportDTO(Sale sale) {
        this.id = sale.getId();
        this.sellerName = sale.getSeller().getName();
        this.date = sale.getDate();
        this.amount = sale.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
