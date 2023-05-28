package com.marketingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketingapp.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead,Long> {

}
