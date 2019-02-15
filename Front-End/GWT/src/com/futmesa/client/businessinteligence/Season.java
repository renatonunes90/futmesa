package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar uma temporada do banco.
 */
public final class Season extends JavaScriptObject
{
   public static final String ID   = "id";
   public static final String NAME = "name";
   
   /**
    * Construtor que não será chamado.
    */
   protected Season()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID of a contact.
    */
   public static final ProvidesKey<Season> KEY_PROVIDER = new ProvidesKey<Season>() {
     @Override
     public Object getKey(Season item) {
       return item == null ? null : item.getId();
     }
   };
   
   /**
    * @return Retorna o identificador da temporada.
    */
   public final native int getId() /*-{
      return this.id;
   }-*/;

   /**
    * @return Retorna o nome da temporada.
    */
   public final native String getName() /*-{
      return this.name;
   }-*/;

}

