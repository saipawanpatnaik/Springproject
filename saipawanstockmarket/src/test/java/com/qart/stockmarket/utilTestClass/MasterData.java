package com.qart.stockmarket.utilTestClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.dto.CompanyStockDetailsDTO;
import com.qart.stockmarket.dto.StockPriceDetailsDTO;
import com.qart.stockmarket.dto.StockPriceIndexDTO;

public class MasterData {

	public static CompanyDetailsDTO getCompanyDetailsDTO() 
	{
		CompanyDetailsDTO companyDetails = new CompanyDetailsDTO();

		companyDetails.setCompanyCode((long)1001);
		companyDetails.setStockExchange("BSEINDIA");
		companyDetails.setCompanyName("Wells Fargo India, Hyderabad");
		companyDetails.setCompanyCEO("Sai Pawan Kumar");
		companyDetails.setTurnover(98765.71);
		companyDetails.setBoardOfDirectors("Wells, Fargo, Nick");
		companyDetails.setCompanyProfile("Headquaters Sanfransisco, USA");

		return companyDetails;
	}

	public static StockPriceDetailsDTO getStockPriceDetailsDTO() 
	{
		StockPriceDetailsDTO spDetails = new StockPriceDetailsDTO();

		spDetails.setId((long)1001);
		spDetails.setCompanyCode((long)2001);
		spDetails.setCurrentStockPrice(60.91);


		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		spDetails.setStockPriceDate(LocalDate.parse("03/01/2021", dateFormat));
		spDetails.setStockPriceTime(LocalTime.parse("09:57:12"));
		return spDetails;
	}
	
	public static CompanyStockDetailsDTO getCompanyStockPriceDetailsDTO() 
	{
		CompanyStockDetailsDTO spDetails = new CompanyStockDetailsDTO();
		spDetails.setCompanyDto(getCompanyDetailsDTO());
		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		stockList.add(getStockPriceDetailsDTO());
		spDetails.setStockPriceDTO(stockList);
		return spDetails;
	}

	public static StockPriceIndexDTO getStockPriceIndexDTO() 
	{
		StockPriceIndexDTO stockPriceIndexDTO = new StockPriceIndexDTO();

		CompanyDetailsDTO companyDetailsDTO = getCompanyDetailsDTO();

		List<StockPriceDetailsDTO> stockPriceDetailsList = new ArrayList<StockPriceDetailsDTO>();

		StockPriceDetailsDTO stockPriceDetailsDTO1 = new StockPriceDetailsDTO();
		stockPriceDetailsDTO1.setId((long)1001);
		stockPriceDetailsDTO1.setCompanyCode((long)2001);
		stockPriceDetailsDTO1.setCurrentStockPrice(55.76);
		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO1.setStockPriceDate(LocalDate.parse("01/01/2021", dateFormat1));
		stockPriceDetailsDTO1.setStockPriceTime(LocalTime.parse("10:30:00"));

		StockPriceDetailsDTO stockPriceDetailsDTO2 = new StockPriceDetailsDTO();
		stockPriceDetailsDTO2.setId((long)1002);
		stockPriceDetailsDTO2.setCompanyCode((long)2002);
		stockPriceDetailsDTO2.setCurrentStockPrice(75.76);
		DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO2.setStockPriceDate(LocalDate.parse("01/05/2021", dateFormat2));
		stockPriceDetailsDTO2.setStockPriceTime(LocalTime.parse("12:00:00"));		

		stockPriceDetailsList.add(stockPriceDetailsDTO1);
		stockPriceDetailsList.add(stockPriceDetailsDTO2);

		//stockPriceIndexDTO.setCompanyDto(companyDetailsDTO);
		stockPriceIndexDTO.setStockPriceList(stockPriceDetailsList);
		stockPriceIndexDTO.setMaxStockPrice(78.60);
		stockPriceIndexDTO.setAvgStockPrice(34.51);
		stockPriceIndexDTO.setMinStockPrice(20.25);

		return stockPriceIndexDTO;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
