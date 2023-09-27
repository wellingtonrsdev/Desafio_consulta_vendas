package com.devsuperior.dsmeta.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {

	private Long id;
	private String name;
	private String email;
	private String phone;

	private List<Sale> sales = new ArrayList<>();

	public SellerMinDTO(Long id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public SellerMinDTO(Seller entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		phone = entity.getPhone();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public List<Sale> getSales() {
		return sales;
	}
}
