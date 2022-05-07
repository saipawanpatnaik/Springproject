package com.qart.stockmarket.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="StockPriceDetails")
public class StockPriceDetails implements Serializable{


	private static final long serialVersionUID = -7695345309639827086L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column
	private Long companyCode;

	@Column(precision=10, scale=2)
	private Double currentStockPrice;

	@Column(columnDefinition = "DATE")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate stockPriceDate;

	@Column(columnDefinition = "TIME")
	private LocalTime stockPriceTime;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="companyCode", insertable=false, updatable=false)
	private CompanyDetails company;

	public CompanyDetails getCompany() {
		return company;
	}
	public void setCompany(CompanyDetails company) {
		this.company = company;
	}
	public StockPriceDetails() {
		super();
	}	
	public StockPriceDetails(Long id, Long companyCode, Double currentStockPrice, LocalDate stockPriceDate,	LocalTime stockPriceTime) {
		super();
		this.Id = id;
		this.companyCode = companyCode;
		this.currentStockPrice = currentStockPrice;
		this.stockPriceDate = stockPriceDate;
		this.stockPriceTime = stockPriceTime;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}

	public Double getCurrentStockPrice() {
		return currentStockPrice;
	}

	public void setCurrentStockPrice(Double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}

	public LocalDate getStockPriceDate() {
		return stockPriceDate;
	}

	public void setStockPriceDate(LocalDate stockPriceDate) {
		this.stockPriceDate = stockPriceDate;
	}

	public LocalTime getStockPriceTime() {
		return stockPriceTime;
	}

	public void setStockPriceTime(LocalTime stockPriceTime) {
		this.stockPriceTime = stockPriceTime;
	}
}
