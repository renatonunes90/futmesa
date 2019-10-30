package com.futmesa.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface FutMesaConsts
   extends Constants
{
   public static final FutMesaConsts INSTANCE = GWT.create( FutMesaConsts.class );

   @DefaultStringValue ( "FM Manager" )
   String pageTitle();

   @DefaultStringValue ( "Championships" )
   String championshipMenu();

   @DefaultStringValue ( "Players" )
   String playerMenu();
   
   @DefaultStringValue ( "Manage Championships" )
   String manageChampionshipMenu();

   @DefaultStringValue ( "Manage Players" )
   String managePlayersMenu();

}
