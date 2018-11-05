package com.futmesa.client.windows.main;

import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.ViewportInterface;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
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
      extends UiBinder< DockLayoutPanel, BaseViewport >
   {}

   /**
    * Única instância da tela.
    */
   private static BaseViewport singleton;

   @UiField
   protected HorizontalPanel mainPanel;

   @UiField
   protected MenuBar menuBar;
   
   @UiField
   protected Label championshipLabel;

   // @UiField
   // protected Button btnHelp;

   // @UiField
   // protected Button btnOptions;
   //
   // @UiField
   // protected Button btnUser;

   // @UiField
   // protected Button miLogout;

   @UiField
   protected DockLayoutPanel vLayout;

   /**
    * Construtor padrão.
    */
   private BaseViewport()
   {
      uiBinder.createAndBindUi( this );

      // Create a command that will execute on menu item selection
      Command menuCommand = new Command() {
        public void execute() {
          Window.alert( "clicou");
        }
      };

      // Create a menu bar
      menuBar.setAutoOpen(true);
      menuBar.setAnimationEnabled(true);

      // Create a sub menu of recent documents
      MenuBar championshipMenu = new MenuBar(true);
      MenuBar playerMenu = new MenuBar(true);
      
      championshipMenu.addItem( new MenuItem( "Campeonato A", menuCommand ) );

      // Create the file menu
      MenuBar mainMenu = new MenuBar(true);
      mainMenu.setAnimationEnabled(true);
      
      MenuItem mainItem = new MenuItem("   ", mainMenu);
      mainItem.setPixelSize( 24, 24 );
      menuBar.addItem( mainItem);
      
      mainMenu.addItem( new MenuItem( "Campeonatos",  championshipMenu ) );
      mainMenu.addItem( new MenuItem( "Jogadores",  playerMenu ) );
      
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
      mainPanel.clear();

      // Atualiza o help do módulo.
      // SafeHtmlBuilder sb = new SafeHtmlBuilder();
      // String help = panel.getHelp();
      // sb.appendHtmlConstant( "<div style='width: 400px;'>" + help + "</div>" );
      // btnHelp.setToolTip( sb.toSafeHtml() );
      // btnHelp.setVisible( help != null && !help.isEmpty() );

      // Coloca a tela na viewport e atualiza ela com o filtro.
      mainPanel.add( panel );
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
   
   public void setChampionshipLabel( String text )
   {
	   championshipLabel.setText( text );
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
