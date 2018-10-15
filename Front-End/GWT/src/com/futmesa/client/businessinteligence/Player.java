package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar um jogador do banco.
 */
public final class Player extends JavaScriptObject
{
   public static final String ID   = "id";
   public static final String NAME = "name";
   
   /**
    * Construtor que não será chamado.
    */
   protected Player()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID of a contact.
    */
   public static final ProvidesKey<Player> KEY_PROVIDER = new ProvidesKey<Player>() {
     @Override
     public Object getKey(Player item) {
       return item == null ? null : item.getId();
     }
   };
   
   /**
    * @return Retorna o identificadordo tipo.
    */
   public final native int getId() /*-{
      return this.id;
   }-*/;

   /**
    * @return Retorna o nome.
    */
   public final native String getName() /*-{
      return this.name;
   }-*/;

}

