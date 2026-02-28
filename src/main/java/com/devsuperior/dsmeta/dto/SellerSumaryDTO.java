package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerSumaryDTO {
    private String name;
    private Double salesAmount;

    public SellerSumaryDTO() {
    }

    public SellerSumaryDTO(String name, Double salesAmount) {
        this.name = name;
        this.salesAmount = salesAmount;
    }

    public SellerSumaryDTO(SellerSumaryProjection seller) {
        this.name = seller.getName();
        this.salesAmount = seller.getTotalAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }
}
