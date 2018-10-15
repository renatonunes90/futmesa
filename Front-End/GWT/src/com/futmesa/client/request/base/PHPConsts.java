package com.futmesa.client.request.base;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface PHPConsts
   extends Constants
{
   public static final PHPConsts INSTANCE = GWT.create( PHPConsts.class );

   @DefaultStringValue ( "Error" )
   String error();

   @DefaultStringValue ( "Error parsing request data." )
   String parseError();

   @DefaultStringValue ( "HTTP request error:" )
   String requestError();

   @DefaultStringValue ( "Server apears to be busy, request timed out." )
   String timeoutError();
}
