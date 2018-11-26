package com.futmesa.client.module.config.widgets.championshipform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget com o formulário de configuração de um campeonato.
 */
public class ChampionshipForm
   implements ViewportInterface
{

   private static final ChampionshipFormUiBinder uiBinder = GWT.create( ChampionshipFormUiBinder.class );

   interface ChampionshipFormUiBinder
      extends UiBinder< HorizontalPanel, ChampionshipForm >
   {}
   
   interface Resources
      extends ClientBundle
   {
      @Source ( "championshipform.css" )
      Styles styles();
   }

   interface Styles
      extends CssResource
   {
      String championshipEditTitle();
   
      String customLeftPanel();
   
      String customRightPanel();
   
      String customRow();
   
      String customLabel();
   
      String customCheck();
   }
   
   /**
    * Estilos do formulário.
    */
   private Resources resources;

   @UiField ( provided = false )
   protected HorizontalPanel panel;

   @UiField ( provided = false )
   protected TextBox nameInput;

   @UiField ( provided = false )
   protected TextBox seasonInput;

   @UiField ( provided = false )
   protected TextBox typeInput;

   @UiField ( provided = false )
   protected TextBox baseDateInput;

   @UiField ( provided = false )
   protected TextBox gamesByRoundInput;

   @UiField ( provided = false )
   protected TextBox roundsByDayInput;

   @UiField ( provided = false )
   protected TextBox dateIncrInput;

   @UiField ( provided = false )
   protected VerticalPanel participantsPanelColumn1;
   
   @UiField ( provided = false )
   protected VerticalPanel participantsPanelColumn2;
   
   @UiField ( provided = false )
   protected VerticalPanel participantsPanelColumn3;

   /**
    * Campeonato corrente que está sendo editado.
    */
   private Championship championship;

   /**
    * Lista de participantes do campeonato.
    */
   private Map< Player, CheckBox > participants;
   
   /**
    * Lista de jogadores cadastrados no sistema para serem selecionados como participantes.
    */
   private List< Player > players;

   /**
    * Construtor padrão.
    */
   public ChampionshipForm()
   {
      resources = GWT.create( Resources.class );
      resources.styles().ensureInjected();

      championship = null;
      players = new ArrayList<>();
      participants = new HashMap<>();
      // Create the UiBinder.
      uiBinder.createAndBindUi( this );
   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }

   public Championship getChampionship()
   {
      championship.setName( nameInput.getValue() );
      championship.setBaseDate( baseDateInput.getValue() );

      int value = !seasonInput.getValue().isEmpty() ? Integer.parseInt( seasonInput.getValue() ) : 0;
      championship.setIdSeason( value );

      value = !typeInput.getValue().isEmpty() ? Integer.parseInt( typeInput.getValue() ) : 0;
      championship.setType( value );

      value = !gamesByRoundInput.getValue().isEmpty() ? Integer.parseInt( gamesByRoundInput.getValue() ) : 0;
      championship.setGamesByRound( value );

      value = !roundsByDayInput.getValue().isEmpty() ? Integer.parseInt( roundsByDayInput.getValue() ) : 0;
      championship.setRoundsByDay( value );

      value = !dateIncrInput.getValue().isEmpty() ? Integer.parseInt( dateIncrInput.getValue() ) : 0;
      championship.setDateIncr( value );

      return championship;
   }
   
   public List< Player > getParticipants()
   {
      List<Player> selected = new ArrayList<>();
      for ( Entry< Player, CheckBox > check : participants.entrySet() )
      {
         if ( check.getValue().getValue() )
         {
            selected.add( check.getKey() );
         }
      }
      return selected;
   }

   public void setChampionship( Championship championship )
   {
      this.championship = championship;

      nameInput.setValue( championship.getName() );
      seasonInput.setValue( String.valueOf( championship.getIdSeason() ) );
      typeInput.setValue( String.valueOf( championship.getIdSeason() ) );
      baseDateInput.setValue( championship.getBaseDate() );
      gamesByRoundInput.setValue( String.valueOf( championship.getGamesByRound() ) );
      roundsByDayInput.setValue( String.valueOf( championship.getRoundsByDay() ) );
      dateIncrInput.setValue( String.valueOf( championship.getDateIncr() ) );
   }

   public void setPlayers( JsArray< Player > players )
   {
      this.players.clear();

      for ( int i = 0; i < players.length(); i++ )
      {
         this.players.add( players.get( i ) );
      }
      
      updateParticipantsOptions();
   }

   private void updateParticipantsOptions()
   {
      participantsPanelColumn1.clear();
      participantsPanelColumn2.clear();
      participantsPanelColumn3.clear();
      participants.clear();

      for ( int i = 0; i < players.size(); i++ )
      {
         Player player = players.get( i );
         CheckBox checkBox = new CheckBox( player.getName() );
         checkBox.setStyleName( resources.styles().customCheck() );
         participants.put( player, checkBox );
         
         if ( i < 8 )
         {
            participantsPanelColumn1.add( checkBox );
            participantsPanelColumn1.setCellHeight( checkBox, "31px" );
            participantsPanelColumn1.setCellWidth( checkBox, "150px" );
         }
         else if ( i >= 8 && i < 16 )
         {
            participantsPanelColumn2.add( checkBox );
            participantsPanelColumn2.setCellHeight( checkBox, "31px" );
            participantsPanelColumn2.setCellWidth( checkBox, "150px" );
         }
         else
         {
            participantsPanelColumn3.add( checkBox );
            participantsPanelColumn3.setCellHeight( checkBox, "31px" );
            participantsPanelColumn3.setCellWidth( checkBox, "150px" );
         }
      }
   }

}
