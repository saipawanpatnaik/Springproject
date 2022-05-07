package com.qart.stockmarket.dto;

import java.util.List;

public class CompanyStockDetailsDTO {

	private CompanyDetailsDTO companyDetails;
	private List<StockPriceDetailsDTO> stockPriceDetails;

	public List<StockPriceDetailsDTO> getStockPriceDTO() {
		return stockPriceDetails;
	}
	public void setStockPriceDTO(List<StockPriceDetailsDTO> stockDtos) {
		this.stockPriceDetails = stockDtos;
	}
	public CompanyDetailsDTO getCompanyDto() {
		return companyDetails;
	}
	public void setCompanyDto(CompanyDetailsDTO companyDto) {
		this.companyDetails = companyDto;
	}
}
