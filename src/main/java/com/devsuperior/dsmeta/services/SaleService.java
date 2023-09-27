package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<ReportMinDTO> report(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate endDate = "".equals(maxDate) ? today : LocalDate.parse(maxDate, fmt1);
		LocalDate startDate = "".equals(minDate) ? endDate.minusYears(1L) : LocalDate.parse(minDate, fmt1);

		return repository.searchByReport(startDate, endDate, name, pageable);
	}

	@Transactional(readOnly = true)
	public List<SummaryMinDTO> summary(String minDate, String maxDate) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate endDate = "".equals(maxDate) ? today : LocalDate.parse(maxDate, fmt1);
		LocalDate startDate = "".equals(minDate) ? endDate.minusYears(1L) : LocalDate.parse(minDate, fmt1);

		return repository.searchBySummary(startDate, endDate);

	}
}
