package com.example.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.api.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll(Integer page_num, Integer page_size) {
		Pageable paging = PageRequest.of(page_num, page_size);
		Page<Customer> pagedResult = customerRepository.findAll(paging);
		return pagedResult.stream().collect(Collectors.toList());
	}

	public Optional<Customer> findById(Long customer_id) {
		return customerRepository.findById(customer_id);
	}

	public Customer createCustomer(Customer customer) throws Exception {
		if (customer.getName().equals("") || customer.getName() == null) {
			throw new Exception("O nome não pode ser vazio");
		}
		if (customer.getEmail().equals("") || !customer.getEmail().contains("@") || customer.getEmail() == null) {
			throw new Exception("O email não pode ser vazio e deve conter o caractere @ para ser válido");
		}
		this.customerRepository.save(customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer, Long customer_id) throws Exception {
		if (customer.getName().equals("") || customer.getName() == null) {
			throw new Exception("O nome não pode ser vazio");
		}
		if (customer.getEmail().equals("") || !customer.getEmail().contains("@") || customer.getEmail() == null) {
			throw new Exception("O email não pode ser vazio e deve conter o caractere @ para ser válido");
		}
		if (!this.customerRepository.existsById(customer_id)) {
			throw new Exception("Não existe um cliente com o customer_id informado");
		}
		customer.setCustomer_id(customer_id);
		this.customerRepository.save(customer);
		return customer;
	}

	public Boolean deleteCustomer(Long customer_id) throws Exception {
		this.customerRepository.deleteById(customer_id);
		return true;
	}

}
