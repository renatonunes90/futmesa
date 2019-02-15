package com.futmesa.client.base.event;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * Singleton que encapsula um gerenciador de eventos customizados da interface.
 */
public final class EventBus
{

   /**
    * Única instância da tela.
    */
   private static EventBus singleton;

   private final SimpleEventBus bus = new SimpleEventBus();

   /**
    * Construtor padrão.
    */
   private EventBus()
   {}

   /**
    * @return Retorna a única instância do singleton.
    */
   public static EventBus getInstance()
   {
      if ( singleton == null )
         singleton = new EventBus();
      return singleton;
   }

   /**
    * Linka um tipo de evento a um handler.
    * 
    * @param type
    * @param customHandler
    */
   public void addHandler( Type<CustomEventHandler> type, CustomEventHandler customHandler )
   {
      bus.addHandler( type, customHandler );
   }

   /**
    * Dispara um tipo de evento.
    * 
    * @param event
    */
   public void fireEvent( CustomEvent event )
   {
      bus.fireEvent( event );
   }
}
