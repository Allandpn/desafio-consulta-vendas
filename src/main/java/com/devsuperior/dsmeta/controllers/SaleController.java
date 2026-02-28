package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

    @Transactional
	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport(@RequestParam(required = false) String minDate,
                                                          @RequestParam(required = false) String maxDate,
                                                          @RequestParam(name = "name", defaultValue = "") String name,
                                                          Pageable pageable)
    {
        Page<SalesReportDTO> reports = service.searchReports(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(reports);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SellerSumaryDTO>> getSummary(@RequestParam(required = false) String minDate,
                                                            @RequestParam(required = false) String maxDate,
                                                            Pageable pageable) {
        Page<SellerSumaryDTO> sumary = service.searchSumary(minDate, maxDate, pageable);
        return ResponseEntity.ok(sumary);
	}
}
