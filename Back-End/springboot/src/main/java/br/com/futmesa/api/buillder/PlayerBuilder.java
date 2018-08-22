package br.com.futmesa.api.buillder;

import java.util.UUID;

import br.com.futmesa.api.domain.Player;

public class PlayerBuilder
{

   private Player entity = new Player();

   public PlayerBuilder()
   {}

   public PlayerBuilder( Player entity )
   {
      this.entity = entity;
   }

   private int generateUUID()
   {
      return UUID.randomUUID().hashCode();
   }

   public PlayerBuilder any()
   {
      int id = generateUUID();
      this.withId( id );
      this.withName( "Player " + id );
      return this;
   }

   public Player build()
   {
      return this.entity;
   }

   public PlayerBuilder withId( int id )
   {
      this.entity.setId( id );
      return this;
   }

   public PlayerBuilder withName( String name )
   {
      this.entity.setName( name );
      return this;
   }

}
