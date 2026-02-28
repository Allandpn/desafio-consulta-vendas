package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.*;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    @Transactional(readOnly = true)
    public Page<SalesReportDTO> searchReports (String min, String max, String name, Pageable pageable) {
        DateRange range = validateDates(min, max);
        Page<Sale> result = repository.searchAllWithSeller(range.minDate(), range.maxDate(), name, pageable);
        return  result.map(SalesReportDTO::new);
    }


    @Transactional(readOnly = true)
    public Page<SellerSumaryDTO> searchSumary (String min, String max, Pageable pageable) {
        DateRange range = validateDates(min, max);
        Page<SellerSumaryProjection> projection = sellerRepository.searchSumary(range.minDate(), range.maxDate(), pageable);
        Page<SellerSumaryDTO> dto = projection.map(SellerSumaryDTO::new);
        return dto;
    }

    // UTILS
    private DateRange validateDates (String min, String max){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate maxDate;
        if(max == null || max.isBlank()){
            maxDate =  LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        }
        else {
            maxDate = LocalDate.parse(max, formatter);
        }
        LocalDate minDate;
        if (min == null || min.isBlank()){
            minDate = maxDate.minusYears(1);
        }
        else {
            minDate = LocalDate.parse(min, formatter);
        }
        if (minDate.isAfter(maxDate)){
            throw new IllegalArgumentException("Min date cannot be after max date");
        }
        return new DateRange(minDate, maxDate);
    }

}
