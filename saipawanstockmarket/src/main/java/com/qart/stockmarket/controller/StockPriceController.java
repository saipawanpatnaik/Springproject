package com.qart.stockmarket.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qart.stockmarket.dto.CompanyStockDetailsDTO;
import com.qart.stockmarket.dto.StockPriceDetailsDTO;
import com.qart.stockmarket.dto.StockPriceIndexDTO;
import com.qart.stockmarket.exception.InvalidStockException;
import com.qart.stockmarket.services.StockMarketService;

@RestController
@RequestMapping (value = "/stock")
public class StockPriceController {

	@Autowired
	private StockMarketService stockMarketService;

	@PostMapping(value="/add-stock")
	public ResponseEntity<StockPriceDetailsDTO> addStockDetails(@Valid @RequestBody  StockPriceDetailsDTO stockPriceDetailsDTO,
			BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new InvalidStockException("Stock Details not valid");
		}
		return ResponseEntity.ok(stockMarketService.saveStockPriceDetails(stockPriceDetailsDTO));

	}

	@GetMapping(value = "/getStockByCompanyCode/{companyCode}")
	public ResponseEntity<CompanyStockDetailsDTO> getStockByCompanyCode(@PathVariable("companyCode") final Long companyCode){
		return ResponseEntity.ok(stockMarketService.getAllStocksDetailsByCompanyCode(companyCode)); 
	}

	@GetMapping(value = "/getStockPriceIndex/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<StockPriceIndexDTO> displayStockPriceIndex(@PathVariable final Long companyCode,
			@PathVariable final Date startDate, @PathVariable final Date endDate){
		LocalDate start= Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end= Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		return ResponseEntity.ok(stockMarketService.getStockPriceIndex(companyCode, start, end));
	}
}
