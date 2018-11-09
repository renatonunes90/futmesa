package com.futmesa.client.businessinteligence.tablestructures;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar um objeto simples com chave e valor retornado pelo back-end.
 */
public final class SimpleMapInfo extends JavaScriptObject
{
   
   /**
    * Construtor que não será chamado.
    */
   protected SimpleMapInfo()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID.
    */
   public static final ProvidesKey<SimpleMapInfo> KEY_PROVIDER = new ProvidesKey<SimpleMapInfo>() {
     @Override
     public Object getKey(SimpleMapInfo item) {
       return item == null ? null : item.getKey();
     }
   };

   /**
    * @return Nome da propriedade.
    */
   public final native String getKey() /*-{
      return this.key;
   }-*/;
   
   /**
    * @return Valor da propriedade.
    */
   public final native String getValue() /*-{
      return this.value;
   }-*/;
}

