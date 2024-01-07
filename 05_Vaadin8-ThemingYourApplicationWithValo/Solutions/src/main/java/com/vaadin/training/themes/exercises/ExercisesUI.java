package com.vaadin.training.themes.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.Query;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.NumberRenderer;

/**
 * Main UI class
 */
@Theme("expenses")
@SuppressWarnings("serial")
public class ExercisesUI extends UI {

	private static final long serialVersionUID = 1L;
	private Grid<MonthlyExpense> expensesGrid;
	private WarningLabel warningLabel;
	private TextField limit;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		warningLabel = new WarningLabel("You have exceeded your yearly budget!");
		warningLabel.setVisible(false);
		layout.addComponent(warningLabel);

		limit = createLimitTextField();
		layout.addComponent(limit);

		expensesGrid = new Grid<MonthlyExpense>();
		expensesGrid.setCaption("Monthly expenses");

		initalizeAndPopulateTable(expensesGrid);
		layout.addComponent(expensesGrid);

		setContent(layout);

	}

	private TextField createLimitTextField() {
		TextField limit = new TextField("Limit for monthly expenses");
		limit.addValueChangeListener(event -> {
			warningLabel.setVisible(countTotalExpenses() > getMonthlyExpenseLimit() * 12);
			expensesGrid.getColumn("expenses").setStyleGenerator(monthlyExpense -> {
				return monthlyExpense.getExpenses() > getMonthlyExpenseLimit() ? "warning-grid-cell" : null;
			});
		});
		return limit;
	}

	private int getMonthlyExpenseLimit() {
		if (limit.getValue() == null || limit.getValue().toString().isEmpty()) {
			return 100000;
		}
		return Integer.parseInt((String) limit.getValue());
	}

	private void initalizeAndPopulateTable(Grid<MonthlyExpense> grid) {
		grid.addColumn(MonthlyExpense::getMonth).setCaption("Month");
		grid.addColumn(MonthlyExpense::getExpenses, new NumberRenderer()).setId("expenses").setCaption("Expenses");

		String[] monthNames = new java.text.DateFormatSymbols().getMonths();
		List<MonthlyExpense> data = new ArrayList<>();
		for (int month = 0; month < 12; month++) {
			data.add(new MonthlyExpense(monthNames[month], getExpenses()));
		}
		grid.setItems(data);
	}

	private long countTotalExpenses() {
		return expensesGrid.getDataProvider().fetch(new Query<>()).mapToLong(MonthlyExpense::getExpenses).sum();
	}

	// Randomize a value between 300 and 800
	private long getExpenses() {
		return Math.round((Math.random() * 1000) % 500 + 300);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ExercisesUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}