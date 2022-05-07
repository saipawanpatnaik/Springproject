package com.qart.stockmarket.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.dto.StockPriceDetailsDTO;
import com.qart.stockmarket.model.CompanyDetails;
import com.qart.stockmarket.model.StockPriceDetails;

public class StockMarketUtility {

	public static StockPriceDetails convertToStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO)	{

		StockPriceDetails newStock = new StockPriceDetails();
		BeanUtils.copyProperties(stockPriceDetailsDTO,newStock);
		return newStock;	
	}

	public static StockPriceDetailsDTO convertToStockPriceDetailsDTO(StockPriceDetails stockPriceDetails)	{

		StockPriceDetailsDTO newStock = new StockPriceDetailsDTO();
		BeanUtils.copyProperties(stockPriceDetails,newStock);
		return newStock;		
	}

	public static CompanyDetailsDTO convertToCompanyDetailsDTO(CompanyDetails companyDto)	{

		CompanyDetailsDTO newCompanyDTO = new CompanyDetailsDTO();
		BeanUtils.copyProperties(companyDto,newCompanyDTO);			
		return newCompanyDTO;
	}

	public static CompanyDetails convertToCompanyDetails(CompanyDetailsDTO companyDetailsDTO)	{

		CompanyDetails newCompany = new CompanyDetails();
		BeanUtils.copyProperties(companyDetailsDTO,newCompany);			
		return newCompany;
	}

	public static List<CompanyDetailsDTO> convertToCompanyDetailsDtoList(List<CompanyDetails> companyDetailsList) {

		List<CompanyDetailsDTO> list = new ArrayList<>();

		for(CompanyDetails companyDto: companyDetailsList) {
			list.add(convertToCompanyDetailsDTO(companyDto));
		}       
		return list;
	}

	public static List<StockPriceDetailsDTO> convertToStockPriceDetailsDtoList(List<StockPriceDetails> stockPriceDetailsList) {

		List<StockPriceDetailsDTO> list = new ArrayList<>();

		for(StockPriceDetails stockDto : stockPriceDetailsList) {
			list.add(convertToStockPriceDetailsDTO(stockDto));
		}
		return list;
	}

}
