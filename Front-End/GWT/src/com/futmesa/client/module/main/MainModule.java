package com.futmesa.client.module.main;

import com.futmesa.client.FutMesaConsts;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.viewport.classification.ClassificationViewport;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.shared.GWT;

public class MainModule extends ModuleInterface implements ServiceInterface {
	/**
	 * Constantes de mensagens.
	 */
	private FutMesaConsts consts = GWT.create(FutMesaConsts.class);

	private ServiceChampionship serviceChampionship;

	private ClassificationViewport classificationViewport;

	/**
	 * Construtor padrão.
	 */
	public MainModule() {
		classificationViewport = new ClassificationViewport();
		serviceChampionship = new ServiceChampionship(this);
//		super.addMenu(consts.examplePage(), "module=main");
//		super.addMenu(consts.exampleTables(), "module=main&panel=table");
	}

	@Override
	public void updatePanel(FilterConfig filter) {
		serviceChampionship.requestClassification(1);
	}

	@Override
	public String getModuleName() {
		return "main"; //consts.moduleName();
	}

	@Override
	public void onServiceResult(JavaScriptObject records, String requestId) {
		if (ServiceChampionship.GET_LAST_CLASSIFICATIONS.equals(requestId)) {
			JsArray<Classification> classification = records.cast();
			classificationViewport.updateClassification(classification);

			serviceChampionship.requestAllRounds(1);
			
		} else if (ServiceChampionship.GET_ALL_ROUNDS.equals(requestId)) {
			JsArray<Round> rounds = records.cast();
			classificationViewport.updateRounds(rounds);

			BaseViewport.getInstance().setViewportContent(classificationViewport);
		}
	}
}
