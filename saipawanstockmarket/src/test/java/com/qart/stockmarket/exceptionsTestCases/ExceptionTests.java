package com.qart.stockmarket.exceptionsTestCases;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qart.stockmarket.controller.CompanyDetailsController;
import com.qart.stockmarket.controller.StockPriceController;
import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.dto.StockPriceDetailsDTO;
import com.qart.stockmarket.dto.StockPriceIndexDTO;
import com.qart.stockmarket.exception.CompanyNotFoundException;
import com.qart.stockmarket.exception.DataExistingException;
import com.qart.stockmarket.exception.ExceptionResponse;
import com.qart.stockmarket.exception.InvalidDateException;
import com.qart.stockmarket.exception.InvalidStockException;
import com.qart.stockmarket.exception.StockNotFoundException;
import com.qart.stockmarket.services.CompanyDetailsService;
import com.qart.stockmarket.services.StockMarketService;
import com.qart.stockmarket.utilTestClass.MasterData;

import static com.qart.stockmarket.utilTestClass.TestUtils.exceptionTestFile;
import static com.qart.stockmarket.utilTestClass.TestUtils.currentTest;
import static com.qart.stockmarket.utilTestClass.TestUtils.testAssert;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.time.LocalDate;

@WebMvcTest({CompanyDetailsController.class, StockPriceController.class})
@AutoConfigureMockMvc
public class ExceptionTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyDetailsService companyInfoService;

	@MockBean
	private StockMarketService stockMarketService;

	//====================================================================================================================
	//			1. Exceptions tests regarding Company Operations - Add and Delete
	//====================================================================================================================	
	@Test
	public void testCompanyForExceptionUponAddingCompanyWithNullValue() throws Exception
	{
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setStockExchange(null);

		Mockito.when(companyInfoService.saveCompanyDetails(companyDto)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/add-company")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false, exceptionTestFile);		
	}

	@Test
	public void testCompanyForExceptionUponAddingNewCompany() throws Exception
	{
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setCompanyName("SE");

		Mockito.when(companyInfoService.saveCompanyDetails(companyDto)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/add-company")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false), exceptionTestFile);
	}
	
	@Test
	public void testCompanyDataExistingException() throws Exception
	{
		ExceptionResponse exResponse = new ExceptionResponse("Company already exists!",
				HttpStatus.NOT_FOUND.value());

		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		
		Mockito.when(companyInfoService.saveCompanyDetails(companyDto)).thenThrow(new DataExistingException("Company already exists!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/add-company")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		testAssert(currentTest(),result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false, exceptionTestFile);		
	}
	
	
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	@Test
	public void testCompanyForExceptionUponDeletingCompanyByNullValue() throws Exception
	{
		Mockito.when(companyInfoService.deleteCompany(2L)).thenThrow(new CompanyNotFoundException("Company with Id - 2 not Found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		testAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false, exceptionTestFile);		
	}
	
	@Test
	public void testCompanyNotFoundExceptionsUponDeleting() throws Exception
	{
		ExceptionResponse exResponse = new ExceptionResponse("Company with Id - 2 not Found!",
				HttpStatus.NOT_FOUND.value());

		Mockito.when(this.companyInfoService.deleteCompany(2L))
		.thenThrow(new CompanyNotFoundException("Company with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		testAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}	

	//====================================================================================================================
	//			2. Exceptions tests regarding Stock Operations
	//====================================================================================================================	

	//--------------------------------------------------------------------------------------------
	@Test
	public void testStockForExceptionUponAddingStockWithNullValue() throws Exception
	{
		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
		//stockDto.setCurrentStockPrice(null);
		stockDto.setStockPriceDate(null);

		Mockito.when(stockMarketService.saveStockPriceDetails(stockDto)).thenReturn(stockDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/add-stock")
				.content(MasterData.asJsonString(stockDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false, exceptionTestFile);		
	}

	@Test
	public void testStockForExceptionUponFetchingStockDetailsByNullValue() throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	
	@Test
	public void testInvalidDateExceptionException() throws Exception
	{
		ExceptionResponse exResponse = new ExceptionResponse("Invalid data..! Please provide current or past date ",
				HttpStatus.NOT_FOUND.value());
		LocalDate date = LocalDate.parse("2050-05-06");
		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
		stockDto.setStockPriceDate(date);
		
		Mockito.when(stockMarketService.saveStockPriceDetails(stockDto)).thenThrow(new InvalidDateException("Invalid data..! Please provide current or past date"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/add-stock")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		testAssert(currentTest(),result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false, exceptionTestFile);		
	}

	@Test
	public void testStockNotFoundExceptionsUponFetchingStockPriceIndex() throws Exception
	{
		ExceptionResponse exResponse = new ExceptionResponse("Stock not found for provided dates",
				HttpStatus.NOT_FOUND.value());

		LocalDate start = LocalDate.parse("2021-05-06");
		LocalDate end = LocalDate.parse("2021-06-09");

		Mockito.when(this.stockMarketService.getStockPriceIndex(2L,start,end))
		.thenThrow(new StockNotFoundException("Stock not found for provided dates"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockPriceIndex/2/"+start+"/"+end)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
	
}