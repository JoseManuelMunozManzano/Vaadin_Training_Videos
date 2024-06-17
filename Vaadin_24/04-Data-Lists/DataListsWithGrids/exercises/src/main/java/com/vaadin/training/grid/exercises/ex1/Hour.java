package com.vaadin.training.grid.exercises.ex1;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Hour extends VerticalLayout {

    private final Text timeText;
    private final DateTimeFormatter timeFormatter;

    public Hour() {
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeText = new Text(getCurrentTime());

        add(timeText);

        // Configura el Poll Interval de la UI para que se actualice cada segundo (1000 ms)
        UI.getCurrent().setPollInterval(1000);

        // Añade un listener para actualizar el componente de hora cada segundo
        UI.getCurrent().addPollListener(ev -> updateTime());
    }

    private void updateTime() {
        timeText.setText(getCurrentTime());
    }

    private String getCurrentTime() {
        return LocalTime.now().format(timeFormatter);
    }
}
