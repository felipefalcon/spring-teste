package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.service.AddressService;
import com.example.api.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
	private CustomerService customerService;
	private AddressService addressService;


	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@ApiOperation(value = "Cadastra um endereço para um cliente")
	@RequestMapping(value = "/save-address/{customer_id}", method = RequestMethod.POST)
	public Address saveAddress(@RequestBody Address address, @PathVariable Long customer_id) throws Exception {
		return addressService.saveAddress(address, customer_id);
	}

	@ApiOperation(value = "Exclui um endereço para um cliente")
	@RequestMapping(value = "/{customer_id}", method = RequestMethod.DELETE)
	public Boolean deleteAddress(@PathVariable Long address_id) throws Exception {
		return addressService.deleteAddress(address_id);
	}

}
