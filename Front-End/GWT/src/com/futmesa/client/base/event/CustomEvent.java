package com.futmesa.client.base.event;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evento customizado da interface gerenciado pelo EventBus. Todos os eventos customizados devem estender essa classe.
 */
public class CustomEvent extends GwtEvent< CustomEventHandler >
{
   private Type< CustomEventHandler > type = new Type< CustomEventHandler >();
   
   private Map<String,Object> properties = new HashMap<>();   
   
   @Override
   public GwtEvent.Type< CustomEventHandler > getAssociatedType()
   {
      return type;
   }

   @Override
   protected void dispatch( CustomEventHandler handler )
   {
      handler.onEvent( this );
   }
   
   /**
    * Busca o valor de uma propriedade do evento.
    * 
    * @param key
    * @return
    */
   public Object getProperty( EventProperty key  )
   {
      return properties.get( key.getKey() );
   }
   
   /**
    * Adiciona uma propriedade ao evento.
    * 
    * @param key
    * @param value
    */
   public void setProperty( EventProperty key, Object value )
   {
      properties.put( key.getKey(), value );
   }
}
