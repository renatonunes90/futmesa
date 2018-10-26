package com.futmesa.client.module.main.viewport.player;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.futmesa.client.module.main.viewport.player.maintab.PlayerMainTab;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class PlayerViewport implements ViewportInterface {

	private static final PlayerViewportUiBinder uiBinder = GWT.create(PlayerViewportUiBinder.class);

	interface PlayerViewportUiBinder extends UiBinder<VerticalPanel, PlayerViewport> {
	}
	
	/**
	 * Constantes da classe.
	 */
	private PlayerViewportConsts constants;

	@UiField(provided = false)
	protected VerticalPanel panel;

	@UiField(provided = false)
	protected TabLayoutPanel tabPanel;

	@UiField(provided = false)
	protected Label playerLabel;
	
	@UiField(provided = false)
	protected HorizontalPanel mainTabPanel;
	
	@UiField(provided = false)
	protected HorizontalPanel seasonsTabPanel;
	
	@UiField(provided = false)
	protected HorizontalPanel historicTabPanel;
	
	private PlayerMainTab mainTab;
	
	private Player player;
	
	/**
	 * Construtor padrão.
	 */
	public PlayerViewport() {

		constants = GWT.create(PlayerViewportConsts.class);
		
		player = null;
		
		mainTab = new PlayerMainTab( this );

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		mainTabPanel.add( mainTab.asWidget() );
	}

	@Override
	public Widget asWidget() {
		return panel;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer( Player player )
	{
		this.player = player;
		playerLabel.setText( player.getName().isEmpty() ? constants.playerTitleLabel() : player.getName() );
	}
	
	public void updateReviewInfo( JsArray<SimpleMapInfo> infos )
	{
		mainTab.updateReviewTable( infos );
	}

}