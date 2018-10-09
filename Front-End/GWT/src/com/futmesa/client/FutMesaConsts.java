package com.futmesa.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface FutMesaConsts
   extends Constants
{
   public static final FutMesaConsts INSTANCE = GWT.create( FutMesaConsts.class );

   @DefaultStringValue ( "Home Page Example" )
   String examplePage();

   @DefaultStringValue ( "Table Example" )
   String exampleTables();

   @DefaultStringValue ( "A pretty example page." )
   String helpPage();

   @DefaultStringValue ( "A pretty example viewport." )
   String helpViewport();

   @DefaultStringValue ( "Logged User" )
   String loggedUser();

   @DefaultStringValue ( "Module Name" )
   String moduleName();

   @DefaultStringValue ( "Welcome to the example module" )
   String welcome();
}
