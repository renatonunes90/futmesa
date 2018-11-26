package com.futmesa.client.module.config.widgets.championshipform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.futmesa.client.base.ChampionshipType;
import com.futmesa.client.base.DateUtil;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

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
   protected ListBox seasonInput;
   
   @UiField ( provided = false )
   protected ListBox typeInput;

   @UiField ( provided = false )
   protected DateBox baseDateInput;
   
   @UiField ( provided = false )
   protected ListBox baseTimeInput;
   
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
      
      DateTimeFormat dateFormat = DateTimeFormat.getFormat( DateUtil.SIMPLE_DATE );
      baseDateInput.setFormat( new DateBox.DefaultFormat( dateFormat ) );
      baseDateInput.getDatePicker().setYearArrowsVisible( true );
      
      for ( int i = 0; i < 24; i++ ) 
      {
         String hour = i < 10 ? "0" : "";
         hour += String.valueOf( i ) + ":00";
         baseTimeInput.addItem( hour );
      }
      
      typeInput.addItem( ChampionshipType.FREE_FOR_ALL.getLabel() );
      typeInput.addItem( ChampionshipType.CLASSIFICATORY_GROUPS.getLabel() );
      typeInput.addItem( ChampionshipType.CLASSIFICATORY_DEATHMATCH.getLabel() );
      
      seasonInput.addItem( "2017" );
      seasonInput.addItem( "2018" );
   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }

   @SuppressWarnings ( "unchecked" )
   public Championship getChampionship()
   {
      championship.setName( nameInput.getValue() );

      String dateString = DateUtil.dateToString( baseDateInput.getValue(), DateUtil.DB_FORMAT );
      dateString = dateString.substring( 0, dateString.indexOf( " " ) );
      dateString += " " + baseTimeInput.getSelectedItemText();
      championship.setBaseDate( dateString );

      championship.setIdSeason( seasonInput.getSelectedIndex() + 1 );

      championship.setType( typeInput.getSelectedIndex() );

      int value = !gamesByRoundInput.getValue().isEmpty() ? Integer.parseInt( gamesByRoundInput.getValue() ) : 0;
      championship.setGamesByRound( value );

      value = !roundsByDayInput.getValue().isEmpty() ? Integer.parseInt( roundsByDayInput.getValue() ) : 0;
      championship.setRoundsByDay( value );

      value = !dateIncrInput.getValue().isEmpty() ? Integer.parseInt( dateIncrInput.getValue() ) : 0;
      championship.setDateIncr( value );

      JsArray<Player> players = ( JsArray< Player > ) JavaScriptObject.createArray();
      for ( Entry< Player, CheckBox > check : participants.entrySet() )
      {
         if ( check.getValue().getValue() )
         {
            players.push( check.getKey() );
         }
      }
      championship.setPlayers( players );
      
      return championship;
   }

   public void setChampionship( Championship championship )
   {
      this.championship = championship;
      
      clearForm();

      nameInput.setValue( championship.getName() );
      seasonInput.setSelectedIndex( championship.getIdSeason() - 1 );
      typeInput.setSelectedIndex( championship.getType() );
      
      if ( championship.getBaseDate() == null )
      {
         baseDateInput.setValue( new Date() );
      }
      else
      {
         String baseDate = championship.getBaseDate();
         Date date = DateUtil.stringToDate( baseDate, DateUtil.DB_FORMAT );
         baseDateInput.setValue( date );
         baseTimeInput.setSelectedIndex( Integer.parseInt( baseDate.substring( baseDate.indexOf( " " ), baseDate.indexOf( ":" ) ) ) );
      }
      
      gamesByRoundInput.setValue( String.valueOf( championship.getGamesByRound() ) );
      roundsByDayInput.setValue( String.valueOf( championship.getRoundsByDay() ) );
      dateIncrInput.setValue( String.valueOf( championship.getDateIncr() ) );
      
      for ( int i = 0; i < championship.getPlayers().length(); i++ )
      {
         Player p = championship.getPlayers().get( i );
         for ( Player checkP : participants.keySet() )
         {
            if ( checkP.getId() == p.getId() )
            {
               participants.get( checkP ).setValue( true );
               
               // não é possível remover um participante do campeonato
               participants.get( checkP ).setEnabled( false );
               break;
            }
         }
      }
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

   private void clearForm()
   {
      nameInput.setValue( "" );
      seasonInput.setSelectedIndex( 1 );
      typeInput.setSelectedIndex( 0 );
      baseDateInput.setValue( new Date() );
      baseTimeInput.setSelectedIndex( 0 );
      gamesByRoundInput.setValue( "0" );
      roundsByDayInput.setValue( "0" );
      dateIncrInput.setValue( "0" );
      
      for ( Player checkP : participants.keySet() )
      {
         participants.get( checkP ).setValue( false );
         participants.get( checkP ).setEnabled( true );
      }
   }
}
