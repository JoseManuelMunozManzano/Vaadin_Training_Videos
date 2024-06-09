package org.vaadin.training.views.auditing;

import org.vaadin.training.backend.AuditLogService;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class AuditingView extends VerticalLayout {

	public AuditingView() {
		final Label header = new Label("Audit Log Messages");
		header.setStyleName(ValoTheme.LABEL_H2);
		addComponent(header);
		setMargin(true);

		fetchInitialData();
	}

	private void fetchInitialData() {
		for (final String message : AuditLogService.getAuditLogMessages()) {
			addComponent(new Label(message));
		}
	}

}
