package edu.iu.habahram.weathermonitoring.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Observer, DisplayElement {

    private List<Float> temperatures;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.temperatures = new ArrayList<>();
    }

    @Override
    public String display() {
        String html = "";

        float sum = 0;
        float min = 1000;
        float max = 0;
        for (Float number : temperatures) {
            sum += number;
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }

        float average = (float) ((double) sum / temperatures.size());
        html += "<h1>Weather Stats</h1>";
        html += "<li>Avg. Temp: " + average + "</li>";
        html += "<li>Min. Temp: " + min + "</li>";
        html += "<li>Max. Temp: " + max + "</li>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperatures.add(temperature);
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics-display";

    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
