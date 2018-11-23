package com.futmesa.client.base.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Evento customizado da interface gerenciado pelo EventBus. Todos os eventos customizados devem estender essa classe.
 */
public class CustomEvent extends GwtEvent< CustomEventHandler >
{
   public Type< CustomEventHandler > type = new Type< CustomEventHandler >();
   
   public GwtEvent.Type< CustomEventHandler > getAssociatedType()
   {
      return type;
   }

   @Override
   protected void dispatch( CustomEventHandler handler )
   {
      handler.onEvent( this );
   }
}
