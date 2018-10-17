package com.futmesa.client.module.main.viewport.classification;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Classification;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport com exemplos de utilização do SimpleGrid.
 */
public class ClassificationViewport implements ViewportInterface {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<HorizontalPanel, ClassificationViewport> {
	}

	@UiField(provided = false)
	protected HorizontalPanel panel;
	
	@UiField(provided = false)
	protected VerticalPanel leftPanel;
	
	@UiField(provided = false)
	protected VerticalPanel rightPanel;

	private ClassificationTable classification;

	/**
	 * Construtor padrão.
	 */
	public ClassificationViewport() {

		classification = new ClassificationTable();

		
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		leftPanel.add(classification.asWidget());
		
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void updateClassification(JsArray<Classification> classifications) {
		classification.updateClassification(classifications);
	}
}