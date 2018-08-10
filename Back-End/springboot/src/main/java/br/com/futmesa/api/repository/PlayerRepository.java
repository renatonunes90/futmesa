package br.com.futmesa.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.futmesa.api.domain.Player;

@Repository
public interface PlayerRepository
   extends MongoRepository< Player, Integer >
{}
