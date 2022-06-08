package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Customer createCustomer(Customer customer) throws Exception {
		if (customer.getName().equals("")) {
			throw new Exception("O nome não pode ser vazio e deve possuir ao menos dois caracteres");
		}
		if (customer.getEmail().equals("") || !customer.getEmail().contains("@")) {
			throw new Exception("O email não pode ser vazio e deve conter o caractere @ para ser válido");
		}
		this.repository.save(customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer) throws Exception {
		if (customer.getName().equals("")) {
			throw new Exception("O nome não pode ser vazio e deve possuir ao menos dois caracteres");
		}
		if (customer.getEmail().equals("") || !customer.getEmail().contains("@")) {
			throw new Exception("O email não pode ser vazio e deve conter o caractere @ para ser válido");
		}
		this.repository.save(customer);
		return customer;
	}

	public Boolean deleteCustomer(Long customer_id) throws Exception {
		this.repository.deleteById(customer_id);
		return true;
	}

}
