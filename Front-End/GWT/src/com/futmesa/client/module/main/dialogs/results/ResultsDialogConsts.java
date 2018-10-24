package com.futmesa.client.module.main.dialogs.results;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

public interface ResultsDialogConsts
   extends Constants
{
   public static final ResultsDialogConsts INSTANCE = GWT.create( ResultsDialogConsts.class );

   @DefaultStringValue ( "Insert Results" )
   String insertResultsLabel();

   @DefaultStringValue ( "X" )
   String versusSymbol();
   
   @DefaultStringValue ( "Save" )
   String saveBtn();
   
   @DefaultStringValue ( "Cancel" )
   String cancelBtn();
   
   @DefaultStringValue ( "Results saved successfully." )
   String successMsg();
}
