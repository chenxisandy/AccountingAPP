package com.example.sandy.accountingapp.dateChart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.LocalRepo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
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

        LineChart lineChartMonth = findViewById(R.id.line_chart_month);
        BarChart barChart = findViewById(R.id.chart_bar);

        presenter.getChartData(lineChartMonth, barChart, ChartContract.MONTH);
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

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setEnabled(false);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setDrawGridLines(false);
        yLAxis.setAxisMinimum(0f);
//        xAxis.setDrawGridLines(false);
    }

    @Override
    public void showNoChart(LineChart lineChart, BarChart barChart) {
        TextView textView = findViewById(R.id.one_month_remind);
        textView.setVisibility(View.VISIBLE);
        lineChart.setVisibility(View.GONE);
        barChart.setVisibility(View.GONE);
    }

    @Override
    public void showBarChart(BarChart barChart, BarData barData) {
        Description description = new Description();
        description.setText("每月消费金额");
        barChart.setDescription(description);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(4f);


        YAxis yRAxis = barChart.getAxisRight();
        yRAxis.setEnabled(false);

        YAxis yLAxis = barChart.getAxisLeft();
        yLAxis.setDrawGridLines(false);
        yLAxis.setAxisMinimum(0f);

        barChart.setData(barData);
    }

}
