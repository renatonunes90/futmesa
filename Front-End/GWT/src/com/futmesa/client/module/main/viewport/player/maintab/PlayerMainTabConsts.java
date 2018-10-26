package com.futmesa.client.module.main.viewport.player.maintab;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface PlayerMainTabConsts
   extends Constants
{
   public static final PlayerMainTabConsts INSTANCE = GWT.create( PlayerMainTabConsts.class );

   @DefaultStringValue ( "Review" )
   String reviewLabel();

   @DefaultStringValue ( "Statistics" )
   String  statisticsLabel();   
}
