package com.example.sandy.accountingapp.dateChart;

import android.content.res.Resources;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;

public interface ChartContract {

    int MONTH = 0;

    int WEEK = 1;

    int DAY = 2;

    interface View {
        void showPieChart(PieChart pieChart, PieData pieData);
        void showLineChart(LineChart lineChart, LineData lineData, int type);
        void showNoChart(LineChart lineChart, BarChart barChart);
        void showBarChart(BarChart barChart, BarData barData);
    }

    interface Presenter {
        void getPieData(PieChart pieChart, Resources resources);
        void getChartData(LineChart lineChart, BarChart barChart, int type);
    }
}
