package com.example.sandy.accountingapp.dateChart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.LocalRepo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity implements ChartContract.View {

    ChartContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        presenter = new ChartPresenter(LocalRepo.getInstance(), this);

        PieChart pieChart = findViewById(R.id.pie_chart);
        presenter.getPieData(pieChart, getResources());

        LineChart lineChartMonth = findViewById(R.id.line_chart_month);
        LineChart lineChartWeek = findViewById(R.id.line_chart_week);
        LineChart lineChartDay = findViewById(R.id.line_chart_day);

        presenter.getLineData(lineChartMonth, ChartContract.MONTH);
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
        pieChart.setUsePercentValues(true);
//        pieChart.setEntryLabelTextSize(20);

        //设置比例图
        Legend legend = pieChart.getLegend();
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);

//        List<LegendEntry> xValues = new ArrayList<>();   //存数据名称
//        xValues.add(new LegendEntry("衣服"));
//        xValues.add("食物");
//        xValues.add("出行");
//        xValues.add("学习");
//        xValues.add("娱乐");
//        legend.setEntries();
        //animate
        pieChart.animateXY(1000, 1000);
    }

    @Override
    public void showLineChart(LineChart lineChart, LineData lineData, int type) {

        Description description = new Description();

        switch (type) {
            case ChartContract.MONTH:
                description.setText("每月消费");
        }
        lineChart.setDescription(description);
        lineChart.setData(lineData);
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);   //字体

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
    }

}
