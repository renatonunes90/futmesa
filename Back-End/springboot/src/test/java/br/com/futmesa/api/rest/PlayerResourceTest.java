package br.com.futmesa.api.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.futmesa.api.App;
import br.com.futmesa.api.buillder.PlayerBuilder;
import br.com.futmesa.api.domain.Player;
import br.com.futmesa.api.repository.PlayerRepository;
import br.com.futmesa.api.service.PlayerService;

@RunWith ( SpringRunner.class )
@SpringBootTest ( classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT )
public class PlayerResourceTest
{
   private final static int INVALID_ID = -1;

   private static final String PLAYER_A = "Player A";
   private static final String PLAYER_B = "Player B";
   private static final String PLAYERS_ENDPOINT = "/players/";

   @Autowired
   private PlayerRepository playerRepository;

   @Autowired
   private PlayerService playerService;

   @Autowired
   private TestRestTemplate testRestTemplate;

   private void assertObject( Player entityBody, Player entityResponse )
   {
      assertObjectId( entityBody, entityResponse );
      assertObjectWithoutId( entityBody, entityResponse );
   }

   private void assertObjectId( Player entityBody, Player entityResponse )
   {
      assertThat( entityBody.getId() ).isEqualTo( entityResponse.getId() );
   }

   private void assertObjectWithoutId( Player entityBody, Player entityResponse )
   {
      assertThat( entityBody.getName() ).isEqualTo( entityResponse.getName() );
   }

   private Player buildPlayer( final String name )
   {
      Player player = new PlayerBuilder().any().build();
      if ( name != null )
      {
         player.setName( name );
      }
      final Optional< Player > entity = playerService.create( player );
      return entity.get();
   }

   private HttpEntity< Player > getRequestEntity( final Player obj )
   {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType( MediaType.APPLICATION_JSON );
      return new HttpEntity< Player >( obj, headers );
   }


   private HttpEntity< Player > getRequestEntityOnlyHeader()
   {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType( MediaType.APPLICATION_JSON );
      return new HttpEntity< Player >( headers );
   }

   @Before
   public void before()
   {
      playerRepository.deleteAll();
   }

   @Test
   public void createBadRequestName()
   {
      final Player entity = new PlayerBuilder().any().build();
      entity.setName( "" );

      final ResponseEntity< Player > response = testRestTemplate.exchange( PLAYERS_ENDPOINT, HttpMethod.POST, getRequestEntity( entity ),
               Player.class );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.BAD_REQUEST );
   }

   @Test
   public void createBadRequestWithDuplicatedId()
   {
      final Player entity = buildPlayer( PLAYER_A );
      entity.setName( PLAYER_B );

      final ResponseEntity< Player > response = testRestTemplate.exchange( PLAYERS_ENDPOINT, HttpMethod.POST, getRequestEntity( entity ),
               Player.class );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.BAD_REQUEST );
   }

   @Test
   public void createErrorWithDuplicatedName()
   {
      buildPlayer( PLAYER_A );
      final Player entity2 = new PlayerBuilder().any().build();
      entity2.setName( PLAYER_A );

      final ResponseEntity< Player > response = testRestTemplate.exchange( PLAYERS_ENDPOINT, HttpMethod.POST, getRequestEntity( entity2 ),
               Player.class );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.INTERNAL_SERVER_ERROR );
   }

   @Test
   public void createOk()
   {
      final Player entity = new PlayerBuilder().any().build();
      final ResponseEntity< Player > response = testRestTemplate.exchange( PLAYERS_ENDPOINT, HttpMethod.POST, getRequestEntity( entity ),
               Player.class );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.CREATED );
      assertObject( response.getBody(), entity );
   }

   @Test
   public void getAll()
   {
      final Player entity = buildPlayer( PLAYER_A );
      final Player entity2 = buildPlayer( PLAYER_B );
      final ResponseEntity< Player[] > response = testRestTemplate.exchange( PLAYERS_ENDPOINT, HttpMethod.GET, getRequestEntity( entity ),
               Player[].class );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );
      assertThat( response.getBody().length ).isEqualTo( 2 );

      assertThat( response.getBody() ).contains( entity );
      assertThat( response.getBody() ).contains( entity2 );
   }

   @Test
   public void getAllFiltedByName()
   {
      final Player entity = buildPlayer( PLAYER_A );
      final Player entity2 = buildPlayer( PLAYER_B );

      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString( PLAYERS_ENDPOINT ).queryParam( "name", entity2.getName() );

      final ResponseEntity< Player[] > response = testRestTemplate.exchange( builder.build().encode().toUri(), HttpMethod.GET,
               getRequestEntity( entity ), Player[].class );

      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );
      assertThat( response.getBody().length ).isEqualTo( 1 );
      assertObject( response.getBody()[ 0 ], entity2 );

      builder = UriComponentsBuilder.fromUriString( PLAYERS_ENDPOINT ).queryParam( "name", "a" );

      final ResponseEntity< Player[] > response2 = testRestTemplate.exchange( builder.build().encode().toUri(), HttpMethod.GET,
               getRequestEntity( entity ), Player[].class );

      assertThat( response2.getStatusCode() ).isEqualTo( HttpStatus.OK );
      assertThat( response2.getBody().length ).isEqualTo( 0 );
   }

   @Test
   public void getOne()
   {
      final Player entity = buildPlayer( PLAYER_A );
      final ResponseEntity< Player > response = testRestTemplate.exchange( "/players/{id}", HttpMethod.GET, getRequestEntityOnlyHeader(),
               Player.class, entity.getId() );

      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );
      assertObject( entity, response.getBody() );
   }

   @Test
   public void getOneNotFound()
   {
      final ResponseEntity< Player > response = testRestTemplate.exchange( "/players/{id}", HttpMethod.GET,
               this.getRequestEntityOnlyHeader(), Player.class, INVALID_ID );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.NOT_FOUND );
   }

   @Test
   public void updateBadRequestWithInvalidId()
   {
      final Player entity = buildPlayer( PLAYER_A );

      final ResponseEntity< Player > response = testRestTemplate.exchange( "/players/{id}", HttpMethod.PUT, getRequestEntity( entity ),
               Player.class, INVALID_ID );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.BAD_REQUEST );
   }

   @Test
   public void updateBadRequestWithInvalidName()
   {
      final Player entity = buildPlayer( PLAYER_A );
      entity.setName( "" );

      final ResponseEntity< Player > response = testRestTemplate.exchange( "/players/{id}", HttpMethod.PUT, getRequestEntity( entity ),
               Player.class, entity.getId() );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.BAD_REQUEST );

   }

   @Test
   public void updateNotFound()
   {
      final Player entity = new PlayerBuilder().any().build();

      final ResponseEntity< Player > response = testRestTemplate.exchange( "/players/{id}", HttpMethod.PUT, getRequestEntity( entity ),
               Player.class, entity.getId() );
      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.NOT_FOUND );
   }

   @Test
   public void updateOK()
   {
      final Player entity = buildPlayer( PLAYER_A );
      entity.setName( PLAYER_B );

      final int databaseSizeBeforeCreate = playerService.findAll().size();

      final ResponseEntity< Player > response = testRestTemplate.exchange( "/players/{id}", HttpMethod.PUT, getRequestEntity( entity ),
               Player.class, entity.getId() );

      assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );
      assertObject( entity, response.getBody() );
      assertThat( playerService.findAll() ).hasSize( databaseSizeBeforeCreate );
   }
}
