package org.vaadin.training.views.auditing;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class AuditingView extends VerticalLayout implements View {

	public final static String VIEW_NAME = "auditing";

	private final AuditingPresenter presenter;

	public AuditingView() {
		final Label header = new Label("Audit Log Messages");
		header.setStyleName(ValoTheme.LABEL_H2);
		addComponent(header);
		setMargin(true);

		presenter = new AuditingPresenter();
		presenter.setView(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		presenter.fetchInitialData();
	}

	public void addAuditLog(final String message) {
		addComponent(new Label(message));
	}

}
