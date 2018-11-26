package com.futmesa.client.module.config.widgets.common;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ChampionshipCommonConsts
   extends Constants
{
   public static final ChampionshipCommonConsts INSTANCE = GWT.create( ChampionshipCommonConsts.class );

   @DefaultStringValue ( "Name" )
   String nameLabel();
   
   @DefaultStringValue ( "Season" )
   String seasonLabel();
   
   @DefaultStringValue ( "Type" )
   String typeLabel();
   
   @DefaultStringValue ( "Initial Date" )
   String baseDateLabel();

   @DefaultStringValue ( "Initial Hour" )
   String baseTimeLabel();
   
   @DefaultStringValue ( "Games By Round" )
   String gamesbByRoundLabel();
   
   @DefaultStringValue ( "Rounds By Day" )
   String roundsByDayLabel();
   
   @DefaultStringValue ( "Date Increment" )
   String dateIncrLabel();
   
   @DefaultStringValue ( "Finished" )
   String finishedLabel();
}
