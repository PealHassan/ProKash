package com.example.prokash;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class chartFacilities {
    public static XYChart.Series createSereis(Map<String,Double> data, String legend) {
        XYChart.Series sereis1 = new XYChart.Series();
        sereis1.setName(legend);

        for(String i: data.keySet()) {
            sereis1.getData().addAll(new XYChart.Data(i,data.get(i)));
        }
        return sereis1;
    }
    public static void createLinechart(LineChart linechart) {

    }
}
