package com.example.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll(Integer page_num, Integer page_size) {
		Pageable paging = PageRequest.of(page_num, page_size);
		Page<Customer> pagedResult = repository.findAll(paging);
		return pagedResult.stream().collect(Collectors.toList());
	}

	public Optional<Customer> findById(Long customer_id) {
		return repository.findById(customer_id);
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

	public Customer updateCustomer(Customer customer, Long customer_id) throws Exception {
		if (customer.getName().equals("")) {
			throw new Exception("O nome não pode ser vazio e deve possuir ao menos dois caracteres");
		}
		if (customer.getEmail().equals("") || !customer.getEmail().contains("@")) {
			throw new Exception("O email não pode ser vazio e deve conter o caractere @ para ser válido");
		}
		if (!this.repository.existsById(customer_id)) {
			throw new Exception("Não existe um cliente com o customer_id informado");
		}
		customer.setCustomer_id(customer_id);
		this.repository.save(customer);
		return customer;
	}

	public Boolean deleteCustomer(Long customer_id) throws Exception {
		this.repository.deleteById(customer_id);
		return true;
	}

}
