package com.futmesa.client.base.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler para eventos customizados.
 */
public interface CustomEventHandler extends EventHandler
{
   void onEvent( CustomEvent event );
}
