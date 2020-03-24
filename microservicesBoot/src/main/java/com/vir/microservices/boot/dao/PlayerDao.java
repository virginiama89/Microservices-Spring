package com.vir.microservices.boot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.vir.microservices.boot.domain.Player;

@RestResource(path="players", rel="players")
public interface PlayerDao extends CrudRepository<Player, Long>{

}
