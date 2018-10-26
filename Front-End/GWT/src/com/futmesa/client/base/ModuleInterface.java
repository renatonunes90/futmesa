package com.futmesa.client.base;

/**
 * Classe base para um Módulo do TriGeA. Implementa toda a lógica de criação do Módulo e a interface para as funções de redirecionamento e
 * navegação no Módulo.
 */
public abstract class ModuleInterface
{
   
   /**
    * Botão que leva ao menu do Módulo.
    */
   // private ModuleMenuButton menuBtn = null;
   
   /**
    * Ctor padrão.
    */
   public ModuleInterface()
   {}

   /**
    * Função chamada quando a URL é modificada e um novo painel deve ser exibido na interface do Módulo.
    * 
    * @param filter
    *           Objeto contendo os parâmetros do novo painel.
    */
   public abstract void updatePanel( URLFilter filter );

   /**
    * Função utilizada na criação do Módulo para retornar o nome do Módulo.
    * 
    * @return Nome do Módulo como deve aparecer no menu da interface.
    */
   public abstract String getModuleName();

   /**
    * 
    * @return Botão que abre o menu do Módulo.
    */
   // public ModuleMenuButton getMenuBtn()
   // {
   // if ( menuBtn == null )
   // {
   // menuBtn = new ModuleMenuButton( getModuleName() );
   // }
   // return menuBtn;
   // }

   
   /**
    * Adiciona um novo link no menu do Módulo.
    * 
    * @param name
    *           Label do link.
    * @param target
    *           Parâmetros da URL que o link deve setar.
    */
   public void addMenu( String name, String target )
   {
      // getMenuBtn().addMenuItem( name, target );
   }
}
