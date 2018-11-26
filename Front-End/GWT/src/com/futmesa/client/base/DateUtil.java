package com.futmesa.client.base;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Funções para lidar com datas.
 */
public class DateUtil
{
   
   public static final String SIMPLE_DATE = "dd/MM/yyyy";
   public static final String SIMPLE_DATETIME = "dd/MM/yyyy HH:mm";
   public static final String DB_FORMAT = "yyyy-MM-dd HH:mm:ss";

   private DateUtil() 
   {}
   
   public static String convertPattern( String date, String patternIn, String patternOut )
   {
      String result = "";
      
      try
      {
         Date dateObj = DateUtil.stringToDate( date, patternIn );
         if ( dateObj != null )
         {
            result = DateUtil.dateToString( dateObj, patternOut );
         }
      }
      catch( Exception e)
      {}
      
      return result;
   }
   
   public static String dateToString( Date date, String pattern )
   {
      return DateTimeFormat.getFormat( pattern ).format( date );
   }
   
   public static Date stringToDate( String date, String pattern )
   {
      return DateTimeFormat.getFormat( pattern  ).parse( date );
   }
}
