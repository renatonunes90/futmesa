package com.futmesa.client.module.main.viewport.championship;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Round;
import com.google.gwt.core.client.JsArray;

public abstract class ChampionshipViewport implements ViewportInterface {

	protected Championship championship;
	
	public Championship getChampionship() {
	   return championship;
	}
	
	public void setChampionship( Championship championship ) {
		this.championship = championship;
	}
	
	public abstract void updateClassification(JsArray<Classification> classification);	
	
	public abstract void updateRounds( JsArray<Round> rounds );
}