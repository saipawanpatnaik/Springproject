package com.qart.stockmarket.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qart.stockmarket.model.CompanyDetails;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Long>{
	
	@Query("select cd FROM CompanyDetails cd WHERE cd.companyCode=?1")
	public CompanyDetails findCompanyDetailsById(Long companyCode);

	@Transactional
	public Integer deleteByCompanyCode(Long companyCode);

}
