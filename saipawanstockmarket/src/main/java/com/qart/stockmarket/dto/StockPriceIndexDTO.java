package com.qart.stockmarket.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StockPriceIndexDTO {

	@NotEmpty
	private List<StockPriceDetailsDTO> stockPriceList;

	@NotNull
	@Digits(integer = 10, fraction = 2)
	private Double maxStockPrice;

	@NotNull
	@Digits(integer = 10, fraction = 2)
	private Double minStockPrice;

	@NotNull
	@Digits(integer = 10, fraction = 2)
	private Double avgStockPrice;

	public List<StockPriceDetailsDTO> getStockPriceList() {
		return stockPriceList;
	}

	public void setStockPriceList(List<StockPriceDetailsDTO> stockPriceList) {
		this.stockPriceList = stockPriceList;
	}

	public Double getMaxStockPrice() {
		return maxStockPrice;
	}

	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}

	public Double getMinStockPrice() {
		return minStockPrice;
	}

	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}

	public Double getAvgStockPrice() {
		return avgStockPrice;
	}

	public void setAvgStockPrice(Double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
}
