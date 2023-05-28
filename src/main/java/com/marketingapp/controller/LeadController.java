package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadDto;
import com.marketingapp.entity.Lead;
import com.marketingapp.service.LeadService;
import com.marketingapp.utility.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private LeadService leadService;
	//http://localhost:8080/create
	@RequestMapping("/create")
	public String viewCreateLead(Lead lead) {
		
		return"create_lead";
	}
	@RequestMapping("/saveLead")
	public String saveLead(@ModelAttribute Lead lead,ModelMap model) {
		leadService.saveLead(lead);
		emailService.sendEmail(lead.getEmail(), "Test", "Welcome");
		model.addAttribute("msg", "Record Saved");
	
	return "create_lead";
		
	}
//	@RequestMapping("/saveLead")
//	public String saveLead(@RequestParam("firstName") String firstName,
//			@RequestParam("lastName") String lastName,@RequestParam("email") String email
//			,@RequestParam("mobile") long mobile) {
//		Lead l=new Lead();
//		l.setFirstName(firstName);
//		l.setLastName(lastName);
//		l.setEmail(email);
//		l.setMobile(mobile);
//		leadService.saveLead(l);
//	return "create_lead";
//		
//	}
//	@RequestMapping("/saveLead")
//	public String saveLead(LeadDto leadDto) {
//Lead l=new Lead();
//l.setFirstName(leadDto.getFirstName());
//l.setLastName(leadDto.getLastName());
//l.setEmail(leadDto.getEmail());
//l.setMobile(leadDto.getMobile());
//leadService.saveLead(l);
//	return "create_lead";
//		
//	}
	//http://localhost:8080/listAll
	@RequestMapping("/listAll")
	public String viewLeadAll(Model model) {
		List<Lead> leads = leadService.viewLeadAll();
		model.addAttribute("leads",leads);
	
		return "search_result";
		
	}
	@RequestMapping("/delete")
	public String deleteLeadById(@RequestParam("id") long id,Model model) {
		leadService.deleteLeadById(id);
		
		List<Lead> leads = leadService.viewLeadAll();
		model.addAttribute("leads",leads);
	
		return "search_result";
		
	}
	@RequestMapping("/update")
	public String updateLeadById(@RequestParam("id") long id,Model model) {
		Lead lead=leadService.findLeadById(id);
		model.addAttribute("lead",lead);
		return "update_lead";
	}
	@RequestMapping("/updateLead")
	public String updateLead(LeadDto leadDto,Model model) {
		Lead l=new Lead();
		l.setId(leadDto.getId());
		l.setFirstName(leadDto.getFirstName());
		l.setLastName(leadDto.getLastName());
		l.setEmail(leadDto.getEmail());
		l.setMobile(leadDto.getMobile());
		leadService.saveLead(l);
		List<Lead> leads = leadService.viewLeadAll();
		model.addAttribute("leads",leads);
	
		return "search_result";
		
	}

}
