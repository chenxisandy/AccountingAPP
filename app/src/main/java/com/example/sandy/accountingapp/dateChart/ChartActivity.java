package com.example.sandy.accountingapp.dateChart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.LocalRepo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;

public class ChartActivity extends AppCompatActivity implements ChartContract.View {

    ChartContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        presenter = new ChartPresenter(LocalRepo.getInstance(), this);

        PieChart pieChart = findViewById(R.id.pie_chart);
        presenter.getPieData(pieChart, getResources());
    }

    @Override
    public void showPieChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(64f);
        Description description = new Description();
        description.setText("消费类型占比饼状图");
        pieChart.setDescription(description);
        pieChart.setDrawCenterText(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText("占比");
        pieChart.setData(pieData);
        //设置比例图
        Legend legend = pieChart.getLegend();
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);
        //animate
        pieChart.animateXY(1000, 1000);
    }
}
