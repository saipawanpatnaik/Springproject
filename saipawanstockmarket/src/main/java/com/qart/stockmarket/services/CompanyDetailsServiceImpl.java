package com.qart.stockmarket.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.exception.CompanyNotFoundException;
import com.qart.stockmarket.exception.DataBaseException;
import com.qart.stockmarket.exception.DataExistingException;
import com.qart.stockmarket.model.CompanyDetails;
import com.qart.stockmarket.repository.CompanyDetailsRepository;
import com.qart.stockmarket.utils.StockMarketUtility;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService{

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	@Override
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
		Optional<CompanyDetails> company =companyDetailsRepository.findById(companyDetailsDTO.getCompanyCode());
		if(!company.isPresent()) {
			CompanyDetails saved = companyDetailsRepository.save(StockMarketUtility.convertToCompanyDetails(companyDetailsDTO));
			return StockMarketUtility.convertToCompanyDetailsDTO(saved);
		}
		throw new DataExistingException("Company with given code is already existing");
	}

	public CompanyDetailsDTO deleteCompany(Long companyCode) {
		CompanyDetailsDTO company = getCompanyInfoById(companyCode);
		Integer value=null;
		if (company!=null) {
			value = companyDetailsRepository.deleteByCompanyCode(companyCode);
		}
		if(value==null) {
			throw new DataBaseException("Database write failed");
		}
		else
			return company;
	}

	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		Optional<CompanyDetails> company =companyDetailsRepository.findById(companyCode);
		if(company.isPresent())
			return StockMarketUtility.convertToCompanyDetailsDTO(company.get());
		else
			throw new CompanyNotFoundException("Company not found with code " + companyCode);
	}

}
