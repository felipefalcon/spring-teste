package com.example.api.web.rest;

import java.util.ArrayList;
import java.util.List;

import com.example.api.domain.Address;
import com.example.api.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private CustomerService customerService;
	private AddressService addressService;


	@Autowired
	public CustomerController(CustomerService customerService, AddressService addressService) {
		this.customerService = customerService;
		this.addressService = addressService;
	}

	@ApiOperation(value = "Lista os clientes e seus endereços de forma paginada")
	@GetMapping("/{page_num}/{page_size}")
	public List<Customer> findAll(@PathVariable Integer page_num, @PathVariable Integer page_size) {
		List<Customer> customers = customerService.findAll(page_num, page_size);
		for (Customer customer: customers) {
			List<Address> customerAddresses = this.addressService.listAddressByCustomerId(customer.getCustomer_id());
			customer.setAddresses(customerAddresses);
		}
		return customers;
	}

	@ApiOperation(value = "Pesquisa um cliente pelo customer_id")
	@GetMapping("/get/{customer_id}")
	public Customer findById(@PathVariable Long customer_id) {
		return customerService.findById(customer_id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@ApiOperation(value = "Cadastra um novo cliente")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Customer createCustomer(@RequestBody Customer customer) throws Exception {
		return customerService.createCustomer(customer);
	}

	@ApiOperation(value = "Atualiza dados de um cliente")
	@RequestMapping(value = "/update/{customer_id}", method = RequestMethod.PUT)
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customer_id) throws Exception {
		return customerService.updateCustomer(customer, customer_id);
	}

	@ApiOperation(value = "Exclui um cliente pelo customer_id")
	@RequestMapping(value = "/delete/{customer_id}", method = RequestMethod.DELETE)
	public Boolean deleteCustomer(@PathVariable Long customer_id) throws Exception {
		return customerService.deleteCustomer(customer_id);
	}

}
