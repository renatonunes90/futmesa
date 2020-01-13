package com.futmesa.client.module.main.viewport.championship;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ChampionshipViewportConsts
   extends Constants
{
   public static final ChampionshipViewportConsts INSTANCE = GWT.create( ChampionshipViewportConsts.class );

   @DefaultStringValue ( "Table" )
   String classificationTitle();

   @DefaultStringValue ( "Games" )
   String gamesTitle();
   
   @DefaultStringValue ( "Alter Results" )
   String alterResultsLabel();
   
   @DefaultStringValue ( "Qualify Phase" )
   String qualifyPhase();
   
   @DefaultStringValue ( "Death Match Phase" )
   String deathMatchPhase();
   
   @DefaultStringValue ( "Groups Phase" )
   String groupsPhase();
}
