package com.example.demo.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import com.example.demo.model.*;
import com.example.demo.repo.*;
import com.example.demo.exception.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployController {
	@Autowired
	private EmployRepository employRepository;

	@GetMapping("/employs")
	public List<Employ> getAllEmploys() {
		return employRepository.findAll();
	}

	@PostMapping("/employs")
	public Employ createEmployee(@RequestBody Employ employ) {
		return employRepository.save(employ);
	}

	@GetMapping("/employs/{id}")
	public ResponseEntity<Employ> getEmployById(@PathVariable Long id) {
		Employ employ = employRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		return ResponseEntity.ok(employ);
	}

	@PutMapping("/employs/{id}")
	public ResponseEntity<Employ> updateEmploy(@PathVariable Long id, @RequestBody Employ employDetails) {
		Employ employ = employRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		employ.setFirstName(employDetails.getFirstName());
		employ.setLastName(employDetails.getLastName());
		employ.setEmailId(employDetails.getEmailId());
		Employ updatedEmploy = employRepository.save(employ);
		return ResponseEntity.ok(updatedEmploy);
	}

	@DeleteMapping("/employs/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmploy(@PathVariable Long id) {
		Employ employ = employRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		employRepository.delete(employ);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}