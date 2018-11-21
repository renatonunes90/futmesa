package com.futmesa.client.windows.main.landing;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface LandingViewportConsts
   extends Constants
{
   public static final LandingViewportConsts INSTANCE = GWT.create( LandingViewportConsts.class );

   @DefaultStringValue ( "Welcome to FM Manager!" )
   String welcomeTitle();

   @DefaultStringValue ( "Click on the image to view the last championship table or select a page in the upper menu." )
   String helpText();
}
