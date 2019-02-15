package com.futmesa.client.module.config;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ConfigModuleConsts
   extends Messages 
{
   public static final ConfigModuleConsts INSTANCE = GWT.create( ConfigModuleConsts.class );

   @DefaultMessage ( "Add" )
   String addBtn();
   
   @DefaultMessage ( "Error adding {0}, try again later." )
   String addErrorMsg( String value );
   
   @DefaultMessage ( "{0} added successfuly." )
   String addSuccessMsg( String value );
   
   @DefaultMessage ( "Cancel" )
   String cancelBtn();
   
   @DefaultMessage ( "Championship" )
   String championshipLabel();
   
   @DefaultMessage ( "Are you sure you want to remove the {0}? This operation cannot be undone." )
   String confirmRemoveMsg( String value );
   
   @DefaultMessage ( "Error removing {0}, try again later." )
   String removeErrorMsg( String value );
   
   @DefaultMessage ( "{0} removed successfuly." )
   String removeSuccessMsg( String value );
   
   @DefaultMessage ( "Save" )
   String saveBtn();
   
   @DefaultMessage ( "Error updating {0}, try again later." )
   String updateErrorMsg( String value );
   
   @DefaultMessage ( "{0} updated successfuly." )
   String updateSuccessMsg( String value );
   
}
