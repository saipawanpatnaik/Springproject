package com.qart.stockmarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qart.stockmarket.dto.CompanyStockDetailsDTO;
import com.qart.stockmarket.dto.StockPriceDetailsDTO;
import com.qart.stockmarket.dto.StockPriceIndexDTO;
import com.qart.stockmarket.exception.CompanyNotFoundException;
import com.qart.stockmarket.exception.InvalidDateException;
import com.qart.stockmarket.exception.StockNotFoundException;
import com.qart.stockmarket.model.CompanyDetails;
import com.qart.stockmarket.model.StockPriceDetails;
import com.qart.stockmarket.repository.CompanyDetailsRepository;
import com.qart.stockmarket.repository.StockPriceRepository;
import com.qart.stockmarket.utils.StockMarketUtility;

@Service
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
	private StockPriceRepository stockPriceRepository;

	@Autowired
	private CompanyDetailsRepository companyRepository;

	@Override
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		Optional<CompanyDetails> companyDetails = companyRepository.findById(stockPriceDetailsDTO.getCompanyCode());
		if (companyDetails.isPresent()) {
			if (stockPriceDetailsDTO.getStockPriceDate().compareTo(java.time.LocalDate.now()) < 0) {
				StockPriceDetails savedStock = stockPriceRepository
						.save(StockMarketUtility.convertToStockPriceDetails(stockPriceDetailsDTO));
				return StockMarketUtility.convertToStockPriceDetailsDTO(savedStock);
			} else {
				throw new InvalidDateException("Stock Price Date must be current or past date");
			}

		} else {
			throw new CompanyNotFoundException(
					"Company with Code " + stockPriceDetailsDTO.getCompanyCode() + " not found");
		}
	}

	@Override
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {
		List<StockPriceDetails> stockPriceDetailList = stockPriceRepository
				.findStockByCompanyCodeBetweendates(companyCode, startDate, endDate);
		if (stockPriceDetailList.isEmpty()) {
			StockPriceIndexDTO stockPriceIndexDto = new StockPriceIndexDTO();
			stockPriceIndexDto.setAvgStockPrice(getAvgStockPrice(companyCode, startDate, endDate));
			stockPriceIndexDto.setMaxStockPrice(getMaxStockPrice(companyCode, startDate, endDate));
			stockPriceIndexDto.setMinStockPrice(getMinStockPrice(companyCode, startDate, endDate));
			stockPriceIndexDto
			.setStockPriceList(StockMarketUtility.convertToStockPriceDetailsDtoList(stockPriceDetailList));
			return stockPriceIndexDto;
		} else {
			throw new StockNotFoundException("Stock with Company Code " + companyCode + " not found from " + startDate
					+ " to " + endDate + " time frame");
		}
	}

	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockPriceRepository.findMaxStockPrice(companyCode, startDate, endDate);
	}

	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockPriceRepository.findAvgStockPrice(companyCode, startDate, endDate);
	}

	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockPriceRepository.findMinStockPrice(companyCode, startDate, endDate);
	}

	@Override
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode) {

		List<StockPriceDetails> stockDetails = stockPriceRepository.findStockByCompanyCode(companyCode);

		if (CollectionUtils.isEmpty(stockDetails))
			throw new StockNotFoundException("Stock prices not found for Company Code!!- "+companyCode+" ");
		else
			return stockDetails.stream().map(StockMarketUtility::convertToStockPriceDetailsDTO)
					.collect(Collectors.toList());
	}
	@Override
	public CompanyStockDetailsDTO getAllStocksDetailsByCompanyCode(Long companyCode) {
	
		Optional<CompanyDetails> companyDetails = companyRepository.findById(companyCode);
		if(companyDetails.isPresent()) {
			CompanyStockDetailsDTO companyStockDto = new CompanyStockDetailsDTO();
			companyStockDto.setCompanyDto(StockMarketUtility.convertToCompanyDetailsDTO(companyDetails.get()));
			companyStockDto.setStockPriceDTO(getStockByCode(companyCode));
			return companyStockDto;
		}
		else
			throw new CompanyNotFoundException("Company not found with Code!!- "+companyCode+" Please enter valid companyCode...");   
	}

}
