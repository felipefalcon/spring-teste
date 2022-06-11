package com.example.api.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long address_id;

	@Column(nullable = false)
	private Long customer_id;

	@Column(nullable = false)
	@NotEmpty
	private String street;

	@Column(nullable = false)
	private Long address_number;

	@Column(nullable = false)
	@NotEmpty
	private String district;

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getAddress_number() {
		return address_number;
	}

	public void setAddress_number(Long address_number) {
		this.address_number = address_number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
