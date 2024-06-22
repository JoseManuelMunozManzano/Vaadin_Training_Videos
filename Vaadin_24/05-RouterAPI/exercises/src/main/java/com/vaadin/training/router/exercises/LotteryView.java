package com.vaadin.training.router.exercises;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Route("lottery")
public class LotteryView extends Composite<VerticalLayout>
        implements HasComponents, HasUrlParameter<Integer> {

    private final Div lotteryResult = new Div();
    private final TextField numberInput = new TextField();

    public LotteryView() {
        add(new Span("Lottery View"));

        HorizontalLayout inputBar = new HorizontalLayout();
        inputBar.add(numberInput);
        numberInput.setPlaceholder("Input your number");
        Button button = new Button("Try my luck!", e -> {
            final String value = numberInput.getValue();
            if (isNotEmpty(value)) {
                final Integer number = Integer.parseInt(value);
                validate(number);
                updateContent(number);
            }
        });
        button.setEnabled(false);
        numberInput.addValueChangeListener(e -> button.setEnabled(isNotEmpty(e.getValue())));
        inputBar.add(button);
        add(inputBar);
        add(lotteryResult);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        if (parameter == null) {
            numberInput.setValue("");
            return;
        }

        validate(parameter);

        numberInput.setValue(String.valueOf(parameter));
    }

    private void updateContent(Integer number) {
        if (number == null) {
            lotteryResult.setText("");
        } else {
            final int luckyNumber = new Random().nextInt(10) + 1;
            StringBuilder builder = new StringBuilder();
            if (number.equals(luckyNumber)) {
                builder.append("Congrats, you win! ");
            } else {
                builder.append("Sorry, better luck next time. ");
            }
            builder.append("Your number is: ").append(number).append(", the lucky number is:").append(luckyNumber);

            lotteryResult.setText(builder.toString());
        }
    }

    private void validate(Integer number) {
        if (number != null) {
            if (number < 1 || number > 10) {
                throw new InvalidValueException();
            }
        }
    }
}
