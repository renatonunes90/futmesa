package com.futmesa.client.windows.main.landing;

import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.module.main.MainModulePanel;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Landing page.
 */
public class LandingViewport implements ViewportInterface {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<FluidRow, LandingViewport> {
	}

	@UiField(provided = false)
	protected FluidRow panel;

	@UiField(provided = false)
	protected FocusPanel shortcutBtn;

	@UiField(provided = false)
	protected Label helpText;

	public LandingViewport() {
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void setLastChampionshipLink(int idChampionship) {
		shortcutBtn.addClickHandler(handler -> {
			URLFilter filter = new URLFilter(Modules.MAIN_MODULE, MainModulePanel.CHAMPIONSHIP_PANEL);
			filter.addFilter("id", String.valueOf(idChampionship));
			Window.Location.assign(filter.toURLString());
		});
	}

}
