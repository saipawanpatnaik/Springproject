package com.qart.stockmarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.exception.InvalidCompanyException;
import com.qart.stockmarket.services.CompanyDetailsService;

@RestController
@RequestMapping (value = "/company")
public class CompanyDetailsController {

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@PostMapping(value="/add-company")
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails( @Valid @RequestBody  CompanyDetailsDTO companyDetailsDTO,
			BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new InvalidCompanyException("Company Details not valid");		
		}
		
		CompanyDetailsDTO saveCompanyDetails = companyDetailsService.saveCompanyDetails(companyDetailsDTO);
		return ResponseEntity.ok(saveCompanyDetails);
	}

	@DeleteMapping(value = "/deleteCompany/{companyCode}")
	public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@PathVariable("companyCode")  Long companyCode){
		CompanyDetailsDTO companyDetailsDTO = companyDetailsService.deleteCompany(companyCode);
		return ResponseEntity.ok(companyDetailsDTO);
	}
}
