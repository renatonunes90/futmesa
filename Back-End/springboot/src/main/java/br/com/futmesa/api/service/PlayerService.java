package br.com.futmesa.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.futmesa.api.domain.Player;
import br.com.futmesa.api.repository.PlayerRepository;

@Service
@Transactional
public class PlayerService
{

   private static final Logger log = LoggerFactory.getLogger( PlayerService.class );

   private final PlayerRepository playerRepository;

   private MongoTemplate mongoTemplate;

   public PlayerService( PlayerRepository playerRepository, MongoTemplate mongoTemplate )
   {
      this.playerRepository = playerRepository;
      this.mongoTemplate = mongoTemplate;
    }

   public Optional< Player > create( final Player playerToCreate )
   {
      log.debug( "Iniciando criação de player" );

      final Optional< Player > alreadyExists = findOne( playerToCreate.getId() );
      if ( alreadyExists.isPresent() )
      {
         return Optional.empty();
      }
      
      final Optional< Player > createdPlayer = Optional.ofNullable( playerRepository.save( playerToCreate ) );
      if ( createdPlayer.isPresent() )
      {
         log.debug( createdPlayer.get().toString() + " Salvo com sucesso. Id: " + createdPlayer.get().getId() );
      }

      return createdPlayer;
   }

   public int delete( final int id )
   {
      log.debug( "Iniciando remoção de player" );

      final Optional< Player > currentPlayer = findOne( id );
      if ( currentPlayer.isPresent() )
      {
         playerRepository.delete( id );

         log.debug( "Player removido com sucesso. " + currentPlayer.toString() );

         return 1;
      }

      return 0;
   }

   public List< Player > findAll()
   {
      return playerRepository.findAll();
   }

   public List< Player > findAll( String name, Integer page, Integer size )
   {
      if ( page == null )
      {
         page = 0;
      }

      if ( size == null )
      {
         size = 30;
      }

      Pageable pageable = new PageRequest( page, size );
      final Query query = new Query().with( pageable );

      // Criterios da busca
      if ( !StringUtils.isEmpty( name ) )
      {
         query.addCriteria( Criteria.where( "name" ).is( name ) );
      }

      final List< Player > players = mongoTemplate.find( query, Player.class );
      final long count = mongoTemplate.count( query, Player.class );
      Page< Player > resultPage = new PageImpl< Player >( players, pageable, count );

      return resultPage.getContent();
   }

   public Optional< Player > findOne( final int id )
   {
      return Optional.ofNullable( playerRepository.findOne( id ) );
   }

   public Optional< Player > update( Player newPlayer )
   {
      if ( newPlayer == null )
      {
         return Optional.empty();
      }

      final Optional< Player > response = Optional.ofNullable( playerRepository.save( newPlayer ) );
      if ( response.isPresent() )
      {
         log.debug( response.toString() + "Alterado com sucesso" );
      }
      return response;
   }

}
