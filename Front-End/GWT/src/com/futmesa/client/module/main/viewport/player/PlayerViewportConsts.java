package com.futmesa.client.module.main.viewport.player;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface PlayerViewportConsts
   extends Constants
{
   public static final PlayerViewportConsts INSTANCE = GWT.create( PlayerViewportConsts.class );

   @DefaultStringValue ( "Player" )
   String playerTitleLabel();
   
   @DefaultStringValue ( "Main" )
   String mainTabLabel();

   @DefaultStringValue ( "Seasons" )
   String seasonTabLabel();
   
   @DefaultStringValue ( "Historic" )
   String historicTabLabel();
}
