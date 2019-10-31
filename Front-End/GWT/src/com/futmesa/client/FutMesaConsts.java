package com.futmesa.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface FutMesaConsts extends Constants {
	public static final FutMesaConsts INSTANCE = GWT.create(FutMesaConsts.class);

	@DefaultStringValue("FM Manager")
	String appName();

	@DefaultStringValue("Championships")
	String championshipMenu();

	@DefaultStringValue("Players")
	String playerMenu();

	@DefaultStringValue("Manage Championships")
	String manageChampionshipMenu();

	@DefaultStringValue("Manage Players")
	String managePlayersMenu();

	@DefaultStringValue("Welcome to FM Manager!")
	String welcomeTitle();

	@DefaultStringValue("Click on the image to view the last championship table or select a page in the upper menu.")
	String helpText();
}
