package com.futmesa.client.module.main.widgets.games;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface GamesTableConsts
   extends Constants
{
   public static final GamesTableConsts INSTANCE = GWT.create( GamesTableConsts.class );

   @DefaultStringValue ( "Round" )
   String roundLabel();
   
   @DefaultStringValue ( "Table" )
   String tableLabel();
   
   @DefaultStringValue ( "X" )
   String versusSymbol();
}
