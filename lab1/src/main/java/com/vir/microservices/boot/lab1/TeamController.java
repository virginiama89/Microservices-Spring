package com.vir.microservices.boot.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vir.microservices.boot.lab1.dao.TeamRepository;
import com.vir.microservices.boot.lab1.domain.Team;


public class TeamController {
	
	@Autowired TeamRepository teamRepository;
	
	@GetMapping("/teams")
	public Iterable<Team> getTeams(){
	  	return teamRepository.findAll();
	}
	
	@GetMapping("/teams/{teamId}")
	Team getTeam(@PathVariable long teamId) {
		return teamRepository.findById(teamId).get();
	}
	
	
}
