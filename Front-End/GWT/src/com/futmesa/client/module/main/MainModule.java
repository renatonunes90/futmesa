package com.futmesa.client.module.main;

import com.futmesa.client.FutMesaConsts;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.businessinteligence.Championship;
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

	private Championship currentChampionship;
	
	private int nextChampionship;
	
	/**
	 * Construtor padrão.
	 */
	public MainModule() {
		classificationViewport = new ClassificationViewport();
		serviceChampionship = new ServiceChampionship(this);
		
		currentChampionship = null;
		nextChampionship = -1;
//		super.addMenu(consts.examplePage(), "module=main");
//		super.addMenu(consts.exampleTables(), "module=main&panel=table");
	}

	@Override
	public void updatePanel(FilterConfig filter) {
		
		String championshipId = filter.getFilter( "championship" );
		nextChampionship = parseIntSafe( championshipId );
		currentChampionship = null;
		
		serviceChampionship.requestChampionships();
	}

	@Override
	public String getModuleName() {
		return "main"; //consts.moduleName();
	}

	@Override
	public void onServiceResult(JavaScriptObject records, String requestId) {
		
		if ( ServiceChampionship.GET_ALL_CHAMPIONSHIPS.equals( requestId ) ) {
			JsArray<Championship> championships = records.cast();
			if ( nextChampionship > 0 ) {
				for( int i =0; i <championships.length(); i++  ) {
					if ( championships.get(i).getId() == nextChampionship ) {
						currentChampionship = championships.get( i );
						break;
					}
				}
			}
			
			if ( currentChampionship == null ) {
				currentChampionship = championships.get( 0 );
			}
			
			classificationViewport.setChampionship( currentChampionship );
			serviceChampionship.requestClassification( currentChampionship.getId() );
			
		} else if (ServiceChampionship.GET_LAST_CLASSIFICATIONS.equals(requestId)) {
			JsArray<Classification> classification = records.cast();
			classificationViewport.updateClassification(classification);

			serviceChampionship.requestAllRounds( currentChampionship.getId() );
			
		} else if (ServiceChampionship.GET_ALL_ROUNDS.equals(requestId)) {
			JsArray<Round> rounds = records.cast();
			classificationViewport.updateRounds(rounds);

			BaseViewport.getInstance().setChampionshipLabel( currentChampionship.getName() );
			BaseViewport.getInstance().setViewportContent(classificationViewport);
		}
	}
	
	private int parseIntSafe( String value )
	{
		int parsedValue = -1;
		try {
			parsedValue = Integer.parseInt(value);
		}catch (NumberFormatException e) {
		}
		return parsedValue;
	}
}
