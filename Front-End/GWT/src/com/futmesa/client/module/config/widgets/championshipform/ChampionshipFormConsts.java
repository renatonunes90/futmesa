package com.futmesa.client.module.config.widgets.championshipform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ChampionshipFormConsts
   extends Constants
{
   public static final ChampionshipFormConsts INSTANCE = GWT.create( ChampionshipFormConsts.class );

   @DefaultStringValue ( "Configurations" )
   String configTitle();

   @DefaultStringValue ( "Participants" )
   String participantsTitle();
   
}
