package com.qart.stockmarket.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.exception.CompanyNotFoundException;
import com.qart.stockmarket.exception.DataExistingException;
import com.qart.stockmarket.model.CompanyDetails;
import com.qart.stockmarket.repository.CompanyDetailsRepository;


@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService{

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	@Override
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
		Optional<CompanyDetails> company =companyDetailsRepository.findById(companyDetailsDTO.getCompanyCode());
		if(company.isPresent()) {
			throw new DataExistingException("Company with given code is already existing");
		}else {
			CompanyDetails newCompany = new CompanyDetails();
			BeanUtils.copyProperties(companyDetailsDTO,newCompany);	
			CompanyDetails saved = companyDetailsRepository.save(newCompany);
			CompanyDetailsDTO companyDTO = new CompanyDetailsDTO();
			BeanUtils.copyProperties(saved,companyDTO);
			return companyDTO;
		}
	}

	public CompanyDetailsDTO deleteCompany(Long companyCode) {
		CompanyDetailsDTO company = getCompanyInfoById(companyCode);
		if (company!=null) {
			companyDetailsRepository.deleteByCompanyCode(companyCode);
		}
		return company;
	}

	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		Optional<CompanyDetails> company =companyDetailsRepository.findById(companyCode);
		
		if(company.isPresent()) {
			CompanyDetailsDTO companyDTO = new CompanyDetailsDTO();
			BeanUtils.copyProperties(company.get(),companyDTO);
			return companyDTO;
		}
		else
			throw new CompanyNotFoundException("Company not found with code " + companyCode);
	}

}
