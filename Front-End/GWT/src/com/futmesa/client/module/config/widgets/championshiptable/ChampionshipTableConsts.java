package com.futmesa.client.module.config.widgets.championshiptable;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ChampionshipTableConsts
   extends Constants
{
   public static final ChampionshipTableConsts INSTANCE = GWT.create( ChampionshipTableConsts.class );

   @DefaultStringValue ( "Name" )
   String nameLabel();
   
   @DefaultStringValue ( "Season" )
   String seasonLabel();
   
   @DefaultStringValue ( "Type" )
   String typeLabel();
   
   @DefaultStringValue ( "Base Date" )
   String baseDateLabel();
   
   @DefaultStringValue ( "Games By Round" )
   String gamesbByRoundLabel();
   
   @DefaultStringValue ( "Rounds By Day" )
   String roundsByDayLabel();
   
   @DefaultStringValue ( "Date Increment" )
   String dateIncrLabel();
   
   @DefaultStringValue ( "Finished" )
   String finishedLabel();
}
