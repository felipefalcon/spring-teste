package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

	private AddressRepository addressRepository;

	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public Address saveAddress(Address address, Long customer_id) throws Exception {
		if (address.getStreet().equals("") || address.getStreet() == null) {
			throw new Exception("A rua não pode ser vazia");
		}
		if (address.getDistrict().equals("") || address.getDistrict() == null) {
			throw new Exception("O bairro não pode ser vazio");
		}
		if (address.getAddress_number() == null) {
			throw new Exception("O numero do endereço não pode ser vazia");
		}
		if (customer_id == null) {
			throw new Exception("O customer_id do cliente deve ser informado");
		}
		address.setCustomer_id(customer_id);
		this.addressRepository.save(address);
		return address;
	}

	public Boolean deleteAddress(Long address_id) throws Exception {
		this.addressRepository.deleteById(address_id);
		return true;
	}

	public List<Address> listAddressByCustomerId(Long customer_id) {
		Iterable<Address> addresses = this.addressRepository.findAll();
		List<Address> result = new ArrayList<Address>();
		for (Address address: addresses ) {
			if (address.getCustomer_id().equals(customer_id)) {
				result.add(address);
			}
		}
		return result;
	}

}
