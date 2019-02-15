package com.futmesa.client.windows.main;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface BaseViewportConsts
   extends Constants
{
   public static final BaseViewportConsts INSTANCE = GWT.create( BaseViewportConsts.class );

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
