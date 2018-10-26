package com.futmesa.client.module.main.viewport.player.maintab;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.futmesa.client.module.main.viewport.player.PlayerViewport;
import com.futmesa.client.module.main.widgets.simpletable.SimpleTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class PlayerMainTab implements ViewportInterface {

	interface Resources extends ClientBundle {

		@Source("playermaintab.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String resultGameTable();

	}
	
	/**
	 * Constantes da classe.
	 */
	private PlayerMainTabConsts constants;

	/**
	 * Estilos da tabela.
	 */
	private Resources resources;
	
	private VerticalPanel panel;
	
	private PlayerViewport parent;
	private Player player;
	
	private SimpleTable reviewTable;
	
	/**
	 * Construtor padrão.
	 */
	public PlayerMainTab( PlayerViewport parent ) {
		
		constants = GWT.create(PlayerMainTabConsts.class);

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		constants = GWT.create(PlayerMainTabConsts.class);
		
		this.parent = parent;
		player = null;
		
		panel = new VerticalPanel();
		
		reviewTable = new SimpleTable( 120, 120 );
		panel.add(reviewTable.asWidget());
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void updateReviewTable( JsArray<SimpleMapInfo> infos )
	{
		reviewTable.updateTableInfo( infos );
	}

}