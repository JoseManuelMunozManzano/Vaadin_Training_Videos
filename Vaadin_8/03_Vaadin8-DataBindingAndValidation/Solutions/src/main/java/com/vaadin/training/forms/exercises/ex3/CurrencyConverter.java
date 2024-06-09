package com.vaadin.training.forms.exercises.ex3;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

import java.util.Locale;

@SuppressWarnings("serial")
public class CurrencyConverter implements Converter<String, Double> {

	public static String removeCurrencySymbols(String value) {
		String valueWithoutSymbol = value;
		if (value.endsWith("€")) {
			valueWithoutSymbol = value.substring(0, value.length() - 1).trim();
		} else if (value.endsWith("EUR")) {
			valueWithoutSymbol = value.substring(0, value.length() - 3).trim();
		}
		return valueWithoutSymbol;
	}

	@Override
	public Result<Double> convertToModel(String value, ValueContext context) {
		try {
			return Result.ok(Double.parseDouble(removeCurrencySymbols(value)));
		}
		catch (NumberFormatException ex) {
			return Result.error(ex.getMessage()) ;
		}
	}

	@Override
	public String convertToPresentation(Double value, ValueContext context) {
		return String.format(Locale.US, "%1$.2f", value);
	}
}
