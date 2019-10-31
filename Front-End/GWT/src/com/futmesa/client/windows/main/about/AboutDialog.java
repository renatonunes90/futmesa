package com.futmesa.client.windows.main.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * About app dialog.
 */
public class AboutDialog {

	private static final ResultsDialogUiBinder uiBinder = GWT.create(ResultsDialogUiBinder.class);

	interface ResultsDialogUiBinder extends UiBinder<VerticalPanel, AboutDialog> {
	}

	private AboutDialogConsts constants;

	private DialogBox dialogBox;

	@UiField(provided = false)
	protected VerticalPanel panel;

	@UiField(provided = false)
	protected Button closeBtn;

	public AboutDialog() {
		constants = GWT.create(AboutDialogConsts.class);

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		panel.setCellHorizontalAlignment(panel, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setCellHorizontalAlignment(closeBtn, HasHorizontalAlignment.ALIGN_CENTER);

		// Create the dialog box
		dialogBox = new DialogBox();
		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setText(constants.aboutTitle());
		dialogBox.setWidget(panel);
		dialogBox.center();
		dialogBox.setPopupPosition(dialogBox.getPopupLeft(), dialogBox.getPopupTop() - 100);

		closeBtn.addClickHandler(handler -> {
			dialogBox.hide();
		});
	}

	public Widget asWidget() {
		return panel;
	}

	public DialogBox getDialog() {
		return dialogBox;
	}

	public void show() {
		dialogBox.show();
	}
}
