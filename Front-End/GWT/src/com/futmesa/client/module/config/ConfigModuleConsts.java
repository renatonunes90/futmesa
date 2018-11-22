package com.futmesa.client.module.config;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ConfigModuleConsts
   extends Constants
{
   public static final ConfigModuleConsts INSTANCE = GWT.create( ConfigModuleConsts.class );

   @DefaultStringValue ( "Add" )
   String addBtn();
}
