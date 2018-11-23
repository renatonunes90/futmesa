package com.futmesa.client.base.event;

/**
 * Enum de propriedades de eventos.
 */
public enum EventProperty
{

   CHAMPIONSHIP( "CHAMPIONSHIP" );

   private final String key;

   private EventProperty( String key )
   {
      this.key = key;
   }

   public String getKey()
   {
      return key;
   }

}
