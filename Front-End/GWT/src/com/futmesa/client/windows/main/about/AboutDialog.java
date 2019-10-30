package com.futmesa.client.windows.main.about;

import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

/**
 * App about dialog.
 */
public class AboutDialog {

	private static final AboutDialogUiBinder uiBinder = GWT.create(AboutDialogUiBinder.class);

	interface AboutDialogUiBinder extends UiBinder<Modal, AboutDialog> {
	}

	@UiField(provided = false)
	protected Modal modal;

	@UiField(provided = false)
	protected Button closeBtn;

	public AboutDialog() {
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		closeBtn.addClickHandler(handler -> {
			modal.hide();
		});
	}

	public Widget asWidget() {
		return modal;
	}

	public void show() {
		modal.show();
	}
}
