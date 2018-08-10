package br.com.futmesa.api.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.futmesa.api.domain.Player;
import br.com.futmesa.api.exception.ErrorReturn;
import br.com.futmesa.api.service.PlayerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping ( "/players" )
public class PlayerResource
{

   private final PlayerService playerService;

   public PlayerResource( PlayerService playerService )
   {
      this.playerService = playerService;
    }

   @PostMapping
   @ApiOperation ( value = "Gera um novo jogador. Retorna objeto do jogador.", notes = "Cria uma nova instância de jogador. Jogador é uma pessoa cadastrada no sistema.", response = Player.class )
   @ApiResponses ( value = {
            @ApiResponse ( code = 200, message = "Jogador criado com sucesso.", responseContainer = "List", response = Player.class ),
            @ApiResponse ( code = 400, message = "Requisição inválida.", response = ErrorReturn.class ),
            @ApiResponse ( code = 500, message = "Erro interno do servidor ou serviço.", response = ErrorReturn.class ) } )
   public ResponseEntity< Player > create( @RequestBody @Valid @NotNull Player player )
   {
      if ( StringUtils.isEmpty( player.getName() ) )
      {
         return new ResponseEntity< Player >( HttpStatus.BAD_REQUEST );
      }

      final Optional< Player > newPlayer = playerService.create( player );
      if ( !newPlayer.isPresent() )
      {
         return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
      }

      return new ResponseEntity< Player >( newPlayer.get(), HttpStatus.CREATED );
   }

   @DeleteMapping ( value = "/{id_player}" )
   @ApiOperation ( value = "remove um jogador.", notes = "Remove os dados de um jogador.", response = Player.class )
   @ApiResponses ( value = {
            @ApiResponse ( code = 200, message = "Jogador removido com sucesso.", responseContainer = "List", response = Player.class ),
            @ApiResponse ( code = 404, message = "Não encontrado.", response = ErrorReturn.class ),
            @ApiResponse ( code = 500, message = "Erro interno do servidor ou serviço.", response = ErrorReturn.class ) } )
   public ResponseEntity< Void > delete( @NotNull @PathVariable ( "id_player" ) int id )
   {
      final int result = playerService.delete( id );
      if ( result == 0 )
      {
         return new ResponseEntity< Void >( HttpStatus.NOT_FOUND );
      }
      return new ResponseEntity< Void >( HttpStatus.OK );
   }

   @GetMapping ( "/" )
   @ApiOperation ( value = "Lista de jogadores cadastrados.", notes = "Retorna a lista de jogadores.", response = Player.class, responseContainer = "List" )
   @ApiResponses ( value = {
            @ApiResponse ( code = 200, message = "Lista de jogadores retornada com sucesso.", responseContainer = "List", response = Player.class ),
            @ApiResponse ( code = 500, message = "Erro interno do servidor ou serviço.", response = ErrorReturn.class ) } )
   public ResponseEntity< List< Player > > getAll( @RequestParam ( value = "name", required = false ) final String name,
                                                                    @RequestParam ( value = "page", required = false ) Integer page,
                                                                    @RequestParam ( value = "size", required = false ) Integer size )
   {
      final List< Player > result = playerService.findAll( name, page, size );
      return new ResponseEntity<>( result, HttpStatus.OK );
   }

   @GetMapping ( value = "/{player_id}" )
   @ApiOperation ( value = "Merchant por ID", notes = "Retorna um cliente por identificador.", response = Player.class )
   @ApiResponses ( value = {
            @ApiResponse ( code = 200, message = "Um jogador retornado com sucesso.", responseContainer = "List", response = Player.class ),
            @ApiResponse ( code = 400, message = "Requisição inválida.", response = ErrorReturn.class ),
            @ApiResponse ( code = 404, message = "Não encontrado.", response = ErrorReturn.class ),
            @ApiResponse ( code = 500, message = "Erro interno do servidor ou serviço.", response = ErrorReturn.class ) } )
   @ApiImplicitParams ( {
            @ApiImplicitParam ( name = "player_id", value = "Identificador do jogador para buscar.", required = true, dataType = "Integer", paramType = "query" ) } )
   public ResponseEntity< Player > getOne( @Valid @NotNull @PathVariable ( "player_id" ) int id )
   {
      final Optional< Player > entity = playerService.findOne( id );
      if ( !entity.isPresent() )
      {
         return new ResponseEntity<>( HttpStatus.NOT_FOUND );
      }

      return new ResponseEntity< Player >( entity.get(), HttpStatus.OK );
   }

   @PutMapping ( value = "/{player_id}" )
   @ApiOperation ( value = "Atualizar dados do jogador.", notes = "Atualiza ou alterar dados de um jogador.", response = Player.class )
   @ApiResponses ( value = {
            @ApiResponse ( code = 200, message = "Jogador atualizado com sucesso.", responseContainer = "List", response = Player.class ),
            @ApiResponse ( code = 400, message = "Requisição inválida.", response = ErrorReturn.class ),
            @ApiResponse ( code = 404, message = "Não encontrado.", response = ErrorReturn.class ),
            @ApiResponse ( code = 500, message = "Erro interno do servidor ou serviço.", response = ErrorReturn.class ) } )
   public ResponseEntity< Player > update( @Valid @RequestBody Player player, @Valid @NotNull @PathVariable ( "player_id" ) int id )
   {
      if ( StringUtils.isEmpty( player.getName() ) || id != player.getId() )
      {
         return new ResponseEntity< Player >( HttpStatus.BAD_REQUEST );
      }

      final Optional< Player > currentPlayer = playerService.findOne( player.getId() );
      if ( !currentPlayer.isPresent() )
      {
         return new ResponseEntity< Player >( HttpStatus.NOT_FOUND );
      }

      final Optional< Player > updatedPlayer = playerService.update( player );
      if ( !updatedPlayer.isPresent() )
      {
         return new ResponseEntity< Player >( HttpStatus.BAD_REQUEST );
      }

      return new ResponseEntity< Player >( updatedPlayer.get(), HttpStatus.OK );
   }
}
