package com.futmesa.client.windows.main;

import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.main.MainModulePanel;
import com.futmesa.client.windows.main.about.AboutDialog;
import com.futmesa.client.windows.main.landing.LandingViewport;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
   protected FocusPanel homeShortcut;
   
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

      homeShortcut.addClickHandler( handler -> {
         Window.Location.assign( Window.Location.getPath() );
      });
      
      helpButton.setPixelSize( 24, 24 );
      helpButton.addClickHandler( handler -> {
         AboutDialog about = new AboutDialog();
         about.show();
      });
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
            URLFilter filter = new URLFilter( Modules.CONFIG_MODULE, MainModulePanel.CHAMPIONSHIP_PANEL );
            filter.addFilter( "id", String.valueOf( "1" ) );
            Window.Location.assign( filter.toURLString() );
         }
      };
      
      mainMenu.addItem( new MenuItem( "Editar Campeonatos", menuCommand ) );
      
      menuCommand = new Command()
      {
         public void execute()
         {
            URLFilter filter = new URLFilter( Modules.CONFIG_MODULE, MainModulePanel.PLAYER_PANEL );
            filter.addFilter( "id", String.valueOf( "1" ) );
            Window.Location.assign( filter.toURLString() );
         }
      };
      
      mainMenu.addItem( new MenuItem( "Editar Jogadores", menuCommand ) );
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
               URLFilter filter = new URLFilter( Modules.MAIN_MODULE, MainModulePanel.CHAMPIONSHIP_PANEL );
               filter.addFilter( "id", String.valueOf( championships.get( index ).getId() ) );
               Window.Location.assign( filter.toURLString() );
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
               URLFilter filter = new URLFilter( Modules.MAIN_MODULE, MainModulePanel.PLAYER_PANEL );
               filter.addFilter( "id", String.valueOf( players.get( index ).getId() ) );
               Window.Location.assign( filter.toURLString() );
            }
         };
         playerMenu.addItem( new MenuItem( players.get( i ).getName(), menuCommand ) );
      }
   }
   
   public void showLandingPage()
   {
      LandingViewport landing = new LandingViewport();
      setViewportContent( landing );
   }
}
