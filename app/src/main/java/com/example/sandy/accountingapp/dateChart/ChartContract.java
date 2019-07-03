package com.example.sandy.accountingapp.dateChart;

import android.content.res.Resources;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

public interface ChartContract {
    interface View {
        void showPieChart(PieChart pieChart, PieData pieData);
    }

    interface Presenter {
        void getPieData(PieChart pieChart, Resources resources);
    }
}
