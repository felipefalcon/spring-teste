package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@RequestMapping(value = "/create-customer", method = RequestMethod.POST)
	public Customer createCustomer(@RequestBody Customer customer) throws Exception {
		return service.createCustomer(customer);
	}

	@RequestMapping(value = "/update-customer", method = RequestMethod.PUT)
	public Customer updateCustomer(@RequestBody Customer customer) throws Exception {
		return service.updateCustomer(customer);
	}

	@RequestMapping(value = "/delete-customer/{customer_id}", method = RequestMethod.DELETE)
	public Boolean deleteCustomer(@PathVariable Long customer_id) throws Exception {
		return service.deleteCustomer(customer_id);
	}

}
