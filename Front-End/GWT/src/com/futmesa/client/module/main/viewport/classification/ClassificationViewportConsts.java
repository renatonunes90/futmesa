package com.futmesa.client.module.main.viewport.classification;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ClassificationViewportConsts
   extends Constants
{
   public static final ClassificationViewportConsts INSTANCE = GWT.create( ClassificationViewportConsts.class );

   @DefaultStringValue ( "Table" )
   String classificationTitle();

   @DefaultStringValue ( "Games" )
   String gamesTitle();
   
   @DefaultStringValue ( "Alter Results" )
   String alterResultsLabel();
}
