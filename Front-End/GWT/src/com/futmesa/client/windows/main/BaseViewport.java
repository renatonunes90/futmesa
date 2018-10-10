package com.futmesa.client.windows.main;

import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.ViewportInterface;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport principal do app. Possui o menu e o conteúdo da tela.
 */
public final class BaseViewport
   implements IsWidget
{
   private static final ModulesViewportUiBinder uiBinder = GWT.create( ModulesViewportUiBinder.class );

   interface ModulesViewportUiBinder
      extends UiBinder< Widget, BaseViewport >
   {}

   /**
    * Única instância da tela.
    */
   private static BaseViewport singleton;

   @UiField
   protected HorizontalPanel cpContent;

   // @UiField
   // protected Button btnHelp;

   // @UiField
   // protected Button btnOptions;
   //
   // @UiField
   // protected Button btnUser;
   //
   // @UiField
   // protected Hyperlink linkMessageLog;
   //
   // @UiField
   // protected Hyperlink linkUserManager;
   //
   // @UiField
   // protected Hyperlink linkUserGroupManager;
   //
   // @UiField
   // protected Button miLogout;

   @UiField
   protected HTMLPanel vLayout;

   /**
    * Construtor padrão.
    */
   private BaseViewport()
   {
      uiBinder.createAndBindUi( this );

      // adiciona o listener para o logout
      // miLogout.addSelectHandler( listener -> AuthWindow.getInstance().logout() );
   }

   /**
    * Muda o conteúdo da viewport principal do site.
    * 
    * @param panel
    *           Nova interface que deve ser exibida.
    */
   public void setViewportContent( ViewportInterface panel )
   {
      // Deleta o conteúdo atual.
      cpContent.clear();

      // Atualiza o help do módulo.
      // SafeHtmlBuilder sb = new SafeHtmlBuilder();
      // String help = panel.getHelp();
      // sb.appendHtmlConstant( "<div style='width: 400px;'>" + help + "</div>" );
      // btnHelp.setToolTip( sb.toSafeHtml() );
      // btnHelp.setVisible( help != null && !help.isEmpty() );

      // Coloca a tela na viewport e atualiza ela com o filtro.
      cpContent.add( panel );
      //cpContent.forceLayout();
   }

   /**
    * Habilita o Módulo de configuração avançada da interface.
    */
   public void ableAdvancedConfigModule()
   {
      // btnOptions.setVisible( true );
   }

   /**
    * Habilita o Módulo Base de configurações de usuário da interface.
    */
   public void ableUserConfig()
   {
      // btnUser.setVisible( true );
   }

   /**
    * Adiciona os menus para um Módulo na interface.
    * 
    * @param module
    *           Módulo a ser adicionado.
    */
   public void addModule( ModuleInterface module )
   {
      // modulesMenu.add( module.getMenuBtn(), ( BoxLayoutData ) btnUser.getLayoutData() );
   }

   /**
    * Seta o link que será utilizado no botão de "Log de Mensagens".
    * 
    * @param target
    */
   public void setMessageLogLinkTarget( String target )
   {
      // linkMessageLog.setTargetHistoryToken( target );
   }

   /**
    * Altera o texto do botão do usuário.
    * 
    * @param username
    *           Nome do usuário logado.
    */
   public void setUserLogged( String username )
   {
      // btnUser.setText( username );
   }

   /**
    * Seta o link que será utilizado no botão de "Gerenciador de Usuários".
    * 
    * @param target
    */
   public void setUserManagerLinkTarget( String target )
   {
      // linkUserManager.setTargetHistoryToken( target );
   }

   /**
    * Seta o link que será utilizado no botão de "Gerenciador de Grupos de Usuários".
    * 
    * @param target
    */
   public void setUserGroupManagerLinkTarget( String target )
   {
      // linkUserGroupManager.setTargetHistoryToken( target );
   }

   /**
    * @return Retorna a única instância da janela.
    */
   public static BaseViewport getInstance()
   {
      if ( singleton == null )
         singleton = new BaseViewport();
      return singleton;
   }

   @Override
   public Widget asWidget()
   {
      return vLayout;
   }
}
