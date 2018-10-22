package com.futmesa.client.module.main.dialogs.results;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ResultsDialogConsts
   extends Constants
{
   public static final ResultsDialogConsts INSTANCE = GWT.create( ResultsDialogConsts.class );

   @DefaultStringValue ( "Games" )
   String gamesTitle();
}
