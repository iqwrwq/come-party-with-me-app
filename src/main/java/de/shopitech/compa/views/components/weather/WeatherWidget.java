package de.shopitech.compa.views.components.weather;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;

public class WeatherWidget extends Div {

    public WeatherWidget() {
        addClassName("compa-weather-widget");

        Div circle1 = new Div();
        circle1.addClassName("Circle1");
        Div circle2 = new Div();
        circle2.addClassName("Circle2");
        Div circle3 = new Div();
        circle3.addClassName("Circle3");

        H1 condition = new H1("Sunny");
        condition.addClassName("Condition");
        H1 temp = new H1("15C");
        temp.addClassName("Sunny");
        H1 time = new H1("");
        time.addClassName("Time");
        H1 location = new H1("Location");
        location.addClassName("Location");
        Div content = new Div(condition,temp,time,location);
        Div background = new Div(circle1, circle2, circle3, content);
        add(background);
    }
}
