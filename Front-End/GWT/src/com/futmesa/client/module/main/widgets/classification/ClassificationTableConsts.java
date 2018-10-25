package com.futmesa.client.module.main.widgets.classification;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ClassificationTableConsts
   extends Constants
{
   public static final ClassificationTableConsts INSTANCE = GWT.create( ClassificationTableConsts.class );
   
   @DefaultStringValue ( "Classification" )
   String classificationColumn();
   
   @DefaultStringValue ( "P" )
   String pointsColumn();
   
   @DefaultStringValue ( "G" )
   String gamesColumn();
   
   @DefaultStringValue ( "W" )
   String winsColumn();
   
   @DefaultStringValue ( "T" )
   String tiesColumn();
   
   @DefaultStringValue ( "L" )
   String lossesColumn();
   
   @DefaultStringValue ( "GP" )
   String goalsProColumn();
   
   @DefaultStringValue ( "GC" )
   String goalsConColumn();
   
   @DefaultStringValue ( "GD" )
   String goalsDifferenceColumn();
   
   @DefaultStringValue ( "%" )
   String winRatioColumn();
   
   @DefaultStringValue ( "Last Games" )
   String lastGamesColumn();
}
