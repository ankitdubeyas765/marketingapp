package com.marketingapp.service;

import java.util.List;

import com.marketingapp.entity.Lead;



public interface LeadService {

	public void saveLead(Lead lead);
	public List<Lead> viewLeadAll();
	public void deleteLeadById(long id);
	public Lead findLeadById(long id);
}
