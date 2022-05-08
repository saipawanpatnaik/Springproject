package com.qart.stockmarket.functionalTestCases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qart.stockmarket.controller.CompanyDetailsController;
import com.qart.stockmarket.controller.StockPriceController;
import com.qart.stockmarket.dto.CompanyDetailsDTO;
import com.qart.stockmarket.dto.CompanyStockDetailsDTO;
import com.qart.stockmarket.dto.StockPriceDetailsDTO;
import com.qart.stockmarket.dto.StockPriceIndexDTO;
import com.qart.stockmarket.services.CompanyDetailsService;
import com.qart.stockmarket.services.StockMarketService;
import com.qart.stockmarket.utilTestClass.MasterData;

import static com.qart.stockmarket.utilTestClass.TestUtils.businessTestFile;
import static com.qart.stockmarket.utilTestClass.TestUtils.currentTest;
import static com.qart.stockmarket.utilTestClass.TestUtils.testAssert;

@WebMvcTest({CompanyDetailsController.class, StockPriceController.class})
@AutoConfigureMockMvc
public class ControllerTests {
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompanyDetailsService companyService;

	@MockBean
	private StockMarketService stockMarketService;

	//===========================================================================================================================
		//				I - Testing CompanyDetailsController Rest End Points
	//=======================================================================================================================
	
	@Test 
	public void testAddCompany() throws Exception 
	{ 
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
	
        Mockito.when(companyService.saveCompanyDetails(companyDto)).thenReturn(companyDto);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/add-company")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		testAssert(currentTest(),result.getResponse().getStatus() == 200? true : false, businessTestFile);
	}
	//-- BDD Test : addCompany --------------------------------------------------------------------------------------------------
//	@SuppressWarnings("unused")
//	@Test
//	public void testAddCompanyBDD() throws Exception 
//	{
//		final int count[] = new int[1];
//		
//        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
//		
//		Mockito.when(companyService.saveCompanyDetails(companyDto)).then(new Answer<CompanyDetailsDTO>() {
//			@Override
//			public CompanyDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
//				
//				count[0]++;
//				return companyDto;
//			}
//		});
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/add-company")
//				.content(MasterData.asJsonString(companyDto))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);	
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		testAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
//	}

	//---------------------------------------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /company/deleteCompany/{id}
	//-- Test 1 : deleteCompany -------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteCompany() throws Exception
	{
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();
		
		Mockito.when(companyService.deleteCompany(companyCode)).thenReturn(companyDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
		testAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(companyDto))? true : false, businessTestFile);
	}
	//-- BDD Test : deleteCompany -----------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testDeleteCompanyBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();
		
		Mockito.when(companyService.deleteCompany(companyCode)).then(new Answer<CompanyDetailsDTO>() {
			@Override
			public CompanyDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return MasterData.getCompanyDetailsDTO();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		testAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}
	
	//===========================================================================================================================
			//				I - Testing StockPriceController Rest End Points
	//=======================================================================================================================
	
	@Test 
	public void testAddStockPrice() throws Exception 
	{ 
        StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
	
        Mockito.when(stockMarketService.saveStockPriceDetails(stockDto)).thenReturn(stockDto);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/add-stock")
				.content(MasterData.asJsonString(stockDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();  
        
             
        testAssert(currentTest(),result.getResponse().getStatus() == 200? true : false, businessTestFile);
	}
	//-- BDD Test : addCompany --------------------------------------------------------------------------------------------------
//	@SuppressWarnings("unused")
//	@Test
//	public void testAddStockPriceBDD() throws Exception 
//	{
//		final int count[] = new int[1];
//		
//		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
//		
//		Mockito.when(stockMarketService.saveStockPriceDetails(stockDto)).then(new Answer<StockPriceDetailsDTO>() {
//			@Override
//			public StockPriceDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
//				
//				count[0]++;
//				return stockDto;
//			}
//		});
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/add-stock")
//				.content(MasterData.asJsonString(stockDto))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);	
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		testAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
//	}
	
	@Test
	public void testFindStockByCompanyCode() throws Exception
	{
		CompanyStockDetailsDTO stockDto = MasterData.getCompanyStockPriceDetailsDTO();
        Long companyCode = stockDto.getCompanyDto().getCompanyCode();
        
		Mockito.when(stockMarketService.getAllStocksDetailsByCompanyCode(companyCode)).thenReturn(stockDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(),	result.getResponse().getContentAsString().contains("\"currentStockPrice\":60.91")? true : false, businessTestFile);		
	}
	//-- BDD Test : getStockByCompanyCode ---------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testFindStockByCompanyCodeBDD() throws Exception 
	{
		final int count[] = new int[1];
		
		CompanyStockDetailsDTO stockDto = MasterData.getCompanyStockPriceDetailsDTO();
        Long companyCode = stockDto.getCompanyDto().getCompanyCode();

		Mockito.when(stockMarketService.getAllStocksDetailsByCompanyCode(companyCode)).then(new Answer<CompanyStockDetailsDTO>() {
			@Override
			public CompanyStockDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return stockDto;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		testAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}
	
	
	@Test 
	public void testStockPriceIndex() throws Exception 
	{ 
        StockPriceIndexDTO stockPriceIndexDto = MasterData.getStockPriceIndexDTO();
        CompanyDetailsDTO companyDetailsDTO = MasterData.getCompanyDetailsDTO();
        Long companyCode = companyDetailsDTO.getCompanyCode();
        
        List<StockPriceDetailsDTO> stockPDDTOList = stockPriceIndexDto.getStockPriceList();
        StockPriceDetailsDTO spDetails1 = stockPDDTOList.get(0);
        StockPriceDetailsDTO spDetails2 = stockPDDTOList.get(1);
        
        LocalDate startDate = spDetails1.getStockPriceDate();
        LocalDate endDate   = spDetails2.getStockPriceDate();
        
		Mockito.when(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate)).thenReturn(stockPriceIndexDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockPriceIndex/"+companyCode+"/"+startDate+"/"+endDate)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		testAssert(currentTest(),result.getResponse().getContentAsString().contains("\"companyCode\":2001")? true : false, businessTestFile);	
	}
	//-- BDD Test : getStockPriceIndex ------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testStockPriceIndexBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        StockPriceIndexDTO stockPriceIndexDTO = new StockPriceIndexDTO();
        
        StockPriceIndexDTO stockPriceDto = MasterData.getStockPriceIndexDTO();
        
        CompanyDetailsDTO companyDetailsDTO = MasterData.getCompanyDetailsDTO();
        Long companyCode = companyDetailsDTO.getCompanyCode();
        
        List<StockPriceDetailsDTO> stockPDDTOList = stockPriceDto.getStockPriceList();
        
        StockPriceDetailsDTO spDetails1 = stockPDDTOList.get(0);
        StockPriceDetailsDTO spDetails2 = stockPDDTOList.get(1);
        
        LocalDate startDate = spDetails1.getStockPriceDate();
        LocalDate endDate   = spDetails2.getStockPriceDate();

		Mockito.when(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate)).then(new Answer<StockPriceIndexDTO>() {
			@Override
			public StockPriceIndexDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return stockPriceIndexDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockPriceIndex/"+companyCode+"/"+startDate+"/"+endDate)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	
		testAssert(currentTest(), count[0] >0 ? true : false, businessTestFile);
	}
		
}
