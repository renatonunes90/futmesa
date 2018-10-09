package com.futmesa.client.windows.main;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface BaseViewportConsts
   extends Constants
{
   public static final BaseViewportConsts INSTANCE = GWT.create( BaseViewportConsts.class );

   @DefaultStringValue ( "Analysis Manager" )
   String analysisManager();

   @DefaultStringValue ( "Logout" )
   String logout();
   
   @DefaultStringValue ( "Message Log" )
   String messageLog();

   @DefaultStringValue ( "User" )
   String user();

   @DefaultStringValue ( "WebUser Manager" )
   String webUserManager();

   @DefaultStringValue ( "WebUser Group Manager" )
   String webUserGroupManager();
}
