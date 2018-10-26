package com.futmesa.client.module.main.viewport.player;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.main.widgets.classification.ClassificationTableConsts;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import cern.jet.math.Constants;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class PlayerViewport implements ViewportInterface {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<VerticalPanel, PlayerViewport> {
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
	
	private Player player;
	
	/**
	 * Construtor padrão.
	 */
	public PlayerViewport() {

		constants = GWT.create(PlayerViewportConsts.class);
		
		player = null;
		
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

	}

	@Override
	public Widget asWidget() {
		return panel;
	}
	
	public void setPlayer( Player player )
	{
		this.player = player;
		playerLabel.setText( player.getName().isEmpty() ? constants.playerTitleLabel() : player.getName() );
	}

}