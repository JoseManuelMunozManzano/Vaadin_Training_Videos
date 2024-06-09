package com.vaadin.training.framework.exercises.ex1;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.training.framework.exercises.ex1.LongRunningOperation.FinishEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Polling extends VerticalLayout implements View, LongRunningOperation.FinishedListener {
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Label statusLabel;
	private ProgressBar progressIndicator;

	public Polling() {
		setSpacing(true);

		progressIndicator = new ProgressBar();
		progressIndicator.setIndeterminate(true);
		progressIndicator.setVisible(false);

		statusLabel = new Label("Not started");
		statusLabel.setSizeUndefined();

		addComponent(createStartButton());
		addComponent(createAsyncStartButton());
		addComponent(createShowNotificationButton());
		addComponent(progressIndicator);
		addComponent(statusLabel);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}

	/**
	 * Implement a button which will start the long running operation
	 * synchronously.
	 * 
	 * @return
	 */
	private Button createStartButton() {

		Button theButton = new Button("Start", event -> startOperation(false));
		theButton.setWidth("170px");

		return theButton;
	}

	/**
	 * Implement a button which will start the long running operation
	 * asynchronously.
	 * 
	 * @return
	 */
	private Button createAsyncStartButton() {

		Button theButton = new Button("Start Async", event -> startOperation(true));
		theButton.setWidth("170px");

		return theButton;
	}

	/**
	 * Implement a button which shows the text "Hello world" in a notification.
	 * 
	 * @return
	 */
	private Button createShowNotificationButton() {

		Button theButton = new Button("Show notification", event -> Notification.show("Hello world!"));
		theButton.setWidth("170px");

		return theButton;
	}

	/**
	 * Starts an operation which will run for 5 seconds.
	 * 
	 * @param isAsync
	 *            If true, then the operation is ran asynchronously in a
	 *            separate thread. Otherwise the operation is ran in the same
	 *            thread.
	 */
	private void startOperation(boolean isAsync) {
		if (!isRunning) {
			UI.getCurrent().setPollInterval(200);
			isRunning = true;
			statusLabel.setValue("Running");
			LongRunningOperation longRunningOperation = new LongRunningOperation();
			longRunningOperation.addListener(Polling.this);
			progressIndicator.setVisible(true);
			if (isAsync) {
				longRunningOperation.startAsync();
			} else {
				longRunningOperation.start();
			}
		}
	}

	public void finished(FinishEvent event) {
		getUI().access(new Runnable() {

			@Override
			public void run() {
				UI.getCurrent().setPollInterval(-1);
				statusLabel.setValue("Finished");
				isRunning = false;
				progressIndicator.setVisible(false);
			}
		});
	}
}
