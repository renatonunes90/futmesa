package com.futmesa.client.windows.main;

import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.main.dialogs.about.AboutDialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
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

   @UiField
   protected MenuBar configMenuBar;

   @UiField
   protected FocusPanel helpButton;

   // @UiField
   // protected Button btnUser;

   // @UiField
   // protected Button miLogout;

   @UiField
   protected DockLayoutPanel vLayout;

   private MenuBar playerMenu;

   private MenuBar championshipMenu;

   /**
    * Construtor padrão.
    */
   private BaseViewport()
   {
      uiBinder.createAndBindUi( this );

      createMainMenu();
      createConfigMenu();

      helpButton.setPixelSize( 24, 24 );
   }

   private void createMainMenu()
   {
      // Create a menu bar
      menuBar.setAutoOpen( true );
      menuBar.setAnimationEnabled( true );

      // Create a sub menu of recent documents
      championshipMenu = new MenuBar( true );
      playerMenu = new MenuBar( true );

      // Create the file menu
      MenuBar mainMenu = new MenuBar( true );
      mainMenu.setAnimationEnabled( true );

      MenuItem mainItem = new MenuItem( "   ", mainMenu );
      mainItem.setPixelSize( 24, 24 );
      menuBar.addItem( mainItem );

      mainMenu.addItem( new MenuItem( "Campeonatos", championshipMenu ) );
      mainMenu.addItem( new MenuItem( "Jogadores", playerMenu ) );
   }

   private void createConfigMenu()
   {
      configMenuBar.setAutoOpen( true );
      configMenuBar.setAnimationEnabled( true );

      MenuBar mainMenu = new MenuBar( true );
      mainMenu.setAnimationEnabled( true );

      MenuItem mainItem = new MenuItem( "   ", mainMenu );
      mainItem.setPixelSize( 24, 24 );
      configMenuBar.addItem( mainItem );

      Command menuCommand = new Command()
      {
         public void execute()
         {
            Window.Location.assign( "?view=championship&id=1"  );
         }
      };
      
      mainMenu.addItem( new MenuItem( "Editar Campeonatos", menuCommand ) );
      
      menuCommand = new Command()
      {
         public void execute()
         {
            Window.Location.assign( "?view=championship&id=1"  );
         }
      };
      
      mainMenu.addItem( new MenuItem( "Editar Jogadores", menuCommand ) );
   }

   @UiHandler ( "helpButton" )
   protected void helpButtonClicked( ClickEvent e )
   {
      AboutDialog about = new AboutDialog();
      about.show();
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
      // cpContent.forceLayout();
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

   public void setChampionships( JsArray< Championship > championships )
   {
      for ( int i = 0; i < championships.length(); i++ )
      {
         final int index = i;
         Command menuCommand = new Command()
         {
            public void execute()
            {
               Window.Location.assign( "?view=championship&id=" + String.valueOf( championships.get( index ).getId() ) );
            }
         };
         championshipMenu.addItem( new MenuItem( championships.get( i ).getName(), menuCommand ) );
      }
   }

   public void setPlayers( JsArray< Player > players )
   {
      for ( int i = 0; i < players.length(); i++ )
      {
         final int index = i;
         Command menuCommand = new Command()
         {
            public void execute()
            {
               Window.Location.assign( "?view=player&id=" + String.valueOf( players.get( index ).getId() ) );
            }
         };
         playerMenu.addItem( new MenuItem( players.get( i ).getName(), menuCommand ) );
      }
   }
}
