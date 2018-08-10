package br.com.futmesa.api.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document ( collection = "Player" )
public class Player
   implements Serializable
{

   private static final long serialVersionUID = 1L;

   @Id
   private Integer id;

   @NotNull
   @Indexed ( unique = true )
   private String name;

   public Integer getId()
   {
      return id;
   }

   public void setId( Integer id )
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName( String name )
   {
      this.name = name;
   }

   @Override
   public boolean equals( Object o )
   {
      if ( this == o )
      {
         return true;
      }
      if ( o == null || getClass() != o.getClass() )
      {
         return false;
      }
      Player player = ( Player ) o;
      return Objects.equals( id, player.id ) && Objects.equals( name, player.name );
   }

   @Override
   public int hashCode()
   {
      return Objects.hash( id, name );
   }

   @Override
   public String toString()
   {
      return "Pessoa{ id=" + id + ", name='" + name + "\''}";
   }
}
