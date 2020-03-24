package com.vir.microservices.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vir.microservices.boot.dao.TeamDao;
import com.vir.microservices.boot.domain.Team;

@RestController
public class WhateverIWant {
	
	@Autowired
	TeamDao teamDao;
	
	@RequestMapping("/hi/{name}")
	public Team hiThere(@PathVariable String name){
		
		return teamDao.findByName(name);
	}
}
