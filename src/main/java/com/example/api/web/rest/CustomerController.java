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

	@GetMapping("/{page_num}/{page_size}")
	public List<Customer> findAll(@PathVariable Integer page_num, @PathVariable Integer page_size) {
		return service.findAll(page_num, page_size);
	}

	@GetMapping("/get-customer/{customer_id}")
	public Customer findById(@PathVariable Long customer_id) {
		return service.findById(customer_id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@RequestMapping(value = "/create-customer", method = RequestMethod.POST)
	public Customer createCustomer(@RequestBody Customer customer) throws Exception {
		return service.createCustomer(customer);
	}

	@RequestMapping(value = "/update-customer/{customer_id}", method = RequestMethod.PUT)
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customer_id) throws Exception {
		return service.updateCustomer(customer, customer_id);
	}

	@RequestMapping(value = "/delete-customer/{customer_id}", method = RequestMethod.DELETE)
	public Boolean deleteCustomer(@PathVariable Long customer_id) throws Exception {
		return service.deleteCustomer(customer_id);
	}

}
