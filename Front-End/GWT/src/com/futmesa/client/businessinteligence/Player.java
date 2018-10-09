package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;

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
