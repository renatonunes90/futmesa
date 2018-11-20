package com.futmesa.client.module.main.dialogs.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface AboutDialogConsts
   extends Constants
{
   public static final AboutDialogConsts INSTANCE = GWT.create( AboutDialogConsts.class );

   @DefaultStringValue ( "About" )
   String aboutTitle();

   @DefaultStringValue ( "FM Manager" )
   String appName();
   
   @DefaultStringValue ( "System for management of table soccer championships." )
   String aboutLabel();

   @DefaultStringValue ( "Version:" )
   String versionLabel();
   
   @DefaultStringValue ( "1.0.0" )
   String versionNumber();
   
   @DefaultStringValue ( "Developed by Renato M. Barbieri Nunes." )
   String copyright();
   
   @DefaultStringValue ( "Close" )
   String closeBtn();
}
