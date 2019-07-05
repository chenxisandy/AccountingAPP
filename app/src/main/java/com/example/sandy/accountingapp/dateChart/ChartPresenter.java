package com.example.sandy.accountingapp.dateChart;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.sandy.accountingapp.model.LocalRepo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

public class ChartPresenter implements ChartContract.Presenter {

    LocalRepo localRepo;

    ChartContract.View view;

    public ChartPresenter(LocalRepo localRepo, ChartContract.View view) {
        this.localRepo = localRepo;
        this.view = view;
    }

    @Override
    public void getPieData(PieChart pieChart, Resources resources) {
        PieData pieData = localRepo.getPieData(100); //to delete param
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        ((PieDataSet)(pieData.getDataSet())).setSelectionShift(px);
        view.showPieChart(pieChart, pieData);
    }

    @Override
    public void getLineData(LineChart lineChart, int type) {
        view.showLineChart(lineChart, localRepo.getLineData(type, lineChart), type);
    }



}
