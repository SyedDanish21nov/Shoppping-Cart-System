package com.shopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopping.exception.ApiRequestException;
import com.shopping.model.AdminAuthResponse;
import com.shopping.model.AdminDetails;
import com.shopping.model.ProductCatelog;
import com.shopping.repository.AdminRepository;
import com.shopping.service.AdminService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AdminDetailsController {
	

	@RestController
	@RequestMapping("/AdminDetails")
	public class CustomerDetailsController {

		@Autowired
		private  AuthenticationManager  authenticationManager;

		@Autowired
		private RestTemplate restTemplate;

		@Autowired
		private AdminService AdminService;

		@Autowired
		private AdminRepository AdminRepository;

		// for creating/adding Admin
		@PostMapping("/addAdmin")
		public AdminDetails saveAdminDetails(@RequestBody AdminDetails AdminDetails) {
			return AdminService.addAdmin(AdminDetails);
		}

		// Reading all Admin
		@GetMapping("/allAdminDetails")
		public List<AdminDetails> findAllAdminDetails() {
			return AdminService.getAdminDetails();
		}

		// Reading Admin by Id
		@GetMapping("/allAdminDetails/{id}")
		public Optional<AdminDetails> getAdminById(@PathVariable int id) throws ApiRequestException {
			return Optional.of(AdminRepository.findById(id)
					.orElseThrow(() -> new ApiRequestException("Admin NOT FOUND WITH THIS ID ::")));
		}

		// Updating Admin Data by Id
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateAdminDetails(@PathVariable int id,
				@RequestBody AdminDetails AdminDetails) {
			boolean isAdminDetailsExist = AdminRepository.existsById(id);
			if (isAdminDetailsExist) {
				AdminRepository.save(AdminDetails);
				return new ResponseEntity<Object>("Admin updated successfully with id " + id, HttpStatus.OK);
			} else {
				throw new ApiRequestException("CAN NOT UPDATE AS Admin NOT FOUND WITH THIS ID ::");
			}

		}

		// Deleting Admin Data by Id
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteAdminDetails(@PathVariable int id) {
			boolean isAdminDetailsExist = AdminRepository.existsById(id);
			if (isAdminDetailsExist) {
				AdminService.deleteById(id);
				return new ResponseEntity<Object>("Admin deleted with id" + id, HttpStatus.OK);
			} else {
				throw new ApiRequestException("CAN NOT DELETE AS ADMIN NOT FOUND WITH THIS ID ::");
			}

		}

		// For Adding Product
		@PostMapping("/addproduct")

		public String addProduct(@RequestBody ProductCatelog Product) {
			return restTemplate.postForObject("http://Product-Microservice/product/addproduct", Product, String.class);
		}

		// for Deleting Order

		@DeleteMapping("/deleteproduct/{id}")
		public String deleteorder(@PathVariable("id") int id) {
			restTemplate.delete("http://Product-Microservice/product/delete/deleteproduct/{id}" + id, String.class);
			return "Product with id = "+ id + "is successfully Deleted " ;
		}

		@PostMapping("/auth")
		private ResponseEntity<?> authAdmin(@RequestBody AdminDetails Admin) {
			String email = Admin.getAdmEmail();
			String password = Admin.getAdmPassword();
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

			} catch (Exception e) {

				return ResponseEntity.ok(new AdminAuthResponse("Error during Admin Authentication" + email));
			}
			return ResponseEntity.ok(new AdminAuthResponse("Successfully Authenticated Admin " + email));

		}
	}


}
