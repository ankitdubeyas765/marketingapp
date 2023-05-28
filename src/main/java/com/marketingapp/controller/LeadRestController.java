package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp.dto.LeadDto;
import com.marketingapp.entity.Lead;
import com.marketingapp.repository.LeadRepository;

@RestController
@RequestMapping("/api/leads")
public class LeadRestController {

	@Autowired
	private LeadRepository leadRepo;
	
	//http://localhost:8080/api/leads
	
	@GetMapping
	public List<Lead> getAllLeads(){
		List<Lead> leads = leadRepo.findAll();
		return leads;
	}
	@PostMapping
	public void createLead(@RequestBody Lead lead) {
	leadRepo.save(lead);
	}
	//http://localhost:8080/api/leads/1
	@DeleteMapping("/{id}")
public void deleteLead(@PathVariable("id") long id) {
		leadRepo.deleteById(id);
	
}
	@PutMapping("/{id}")
	public void updateLead(@RequestBody LeadDto leadDto,@PathVariable("id") long id) {
		Lead l=new Lead();
		l.setId(id);
		l.setFirstName(leadDto.getFirstName());
		l.setLastName(leadDto.getLastName());
		l.setEmail(leadDto.getEmail());
		l.setMobile(leadDto.getMobile());
		leadRepo.save(l);
		
		
	}
}
