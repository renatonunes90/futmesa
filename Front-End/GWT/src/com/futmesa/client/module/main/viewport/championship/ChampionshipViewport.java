package com.futmesa.client.module.main.viewport.championship;

import java.util.HashMap;

import com.futmesa.client.base.PhaseType;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Group;
import com.futmesa.client.businessinteligence.Phase;
import com.futmesa.client.module.main.viewport.championship.phase.deathMatch.DeathMatchPhase;
import com.futmesa.client.module.main.viewport.championship.phase.qualify.QualifyPhase;
import com.futmesa.client.request.service.ServiceGroup;
import com.futmesa.client.request.service.ServicePhase;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChampionshipViewport implements ViewportInterface, ServiceInterface  {

	private static final ChampionshipViewportUiBinder uiBinder = GWT.create(ChampionshipViewportUiBinder.class);

	interface ChampionshipViewportUiBinder extends UiBinder<VerticalPanel, ChampionshipViewport> {
	}

	private ChampionshipViewportConsts constants;
	
	@UiField(provided = false)
	protected VerticalPanel panel;
	
	@UiField(provided = false)
	protected Button prevPhaseBtn;

	@UiField(provided = false)
	protected Button nextPhaseBtn;
	
	@UiField(provided = false)
	protected Label phaseLabel;
	
	@UiField(provided = false)
	protected VerticalPanel phasePanel;
	
	private DeathMatchPhase deathMatchPhase;
	
	private HashMap<String, QualifyPhase> groups;
	
	private Championship championship;
	
	private Phase currentPhase;

	private ServiceGroup serviceGroup;
	private ServicePhase servicePhase;
	
	public ChampionshipViewport() {
		serviceGroup = new ServiceGroup(this);
		servicePhase = new ServicePhase(this);
		
		constants = GWT.create(ChampionshipViewportConsts.class);
		 
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
		
		prevPhaseBtn.setEnabled(false);
		nextPhaseBtn.setEnabled(true);
		
		prevPhaseBtn.setText("<<");
		prevPhaseBtn.addClickHandler(handler -> {
			phasePanel.clear();
			servicePhase.requestPhaseCompleteInfo(championship.getId(), currentPhase.getNumber()-1);
		});
		
		nextPhaseBtn.setText(">>");
		nextPhaseBtn.addClickHandler(handler -> {		
			phasePanel.clear();
			this.servicePhase.requestPhaseCompleteInfo(championship.getId(), currentPhase.getNumber()+1);
		});
	}
	
	public Championship getChampionship() {
	   return championship;
	}
	
	public void setChampionship( Championship championship ) {
		this.championship = championship;
		
		phasePanel.clear();
		servicePhase.requestPhaseCompleteInfo(this.championship.getId(), 1);
	}
	
	
	private void buildPhase() {
		String currentPhaseType = PhaseType.getLabelIndex(currentPhase.getType());
		if ( currentPhaseType.equals(PhaseType.DEATHMATCH.getLabel()) ) {
			deathMatchPhase = new DeathMatchPhase();
			phaseLabel.setText(constants.deathMatchPhase());	
			deathMatchPhase.updateRounds(currentPhase.getRounds());
			
			// add deatchmatch phase
			phasePanel.add(deathMatchPhase.asWidget());
		} else {
			this.groups = new HashMap<String, QualifyPhase>();
			if ( currentPhaseType.equals(PhaseType.FREE_FOR_ALL.getLabel()) ) {
				phaseLabel.setText(constants.qualifyPhase());
				
				groups.put("Free-For-All", new QualifyPhase());
				groups.get("Free-For-All").updateRounds(currentPhase.getRounds());
				
				// add qualify phase
				phasePanel.add(groups.get("Free-For-All").asWidget());
				
				// request last classification
				servicePhase.requestClassification(championship.getId(), currentPhase.getNumber());
			} else {
				phaseLabel.setText(constants.groupsPhase());
				
				for ( int i = 0; i < currentPhase.getGroups().length(); i++ ) {
					Group g = currentPhase.getGroups().get(i);
					groups.put(g.getName(), new QualifyPhase());
					groups.get(g.getName()).updateRounds(g.getRounds());
					groups.get(g.getName()).setGroupName(g.getName());
					
					// add group
					phasePanel.add(groups.get(g.getName()).asWidget());
					
					// request group classification
					serviceGroup.requestClassification(championship.getId(), currentPhase.getNumber(), g.getId(), g.getName());
				}
			}
		}
		
		// update buttons state
		prevPhaseBtn.setEnabled(!championship.isFirstPhase(currentPhase.getNumber()));
		nextPhaseBtn.setEnabled(!championship.isLastPhase(currentPhase.getNumber()));
	}
	
	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void onServiceResult(JavaScriptObject records, String requestId) {
		if (ServicePhase.GET_PHASE_INFO.equals(requestId)) {
			this.currentPhase = records.cast();
			if (this.currentPhase != null) {
				buildPhase();
			}			
		} else if (ServicePhase.GET_LAST_CLASSIFICATIONS.equals(requestId)) {
			groups.get("Free-For-All").updateClassification(records.cast());			
		} else if (requestId.startsWith(ServiceGroup.GET_GROUP_CLASSIFICATIONS)) {
			String groupName = requestId.substring(requestId.indexOf(".")+1);
			if ( groupName != null ) {
				groups.get(groupName).updateClassification(records.cast());		
			}
		} 
	}
}