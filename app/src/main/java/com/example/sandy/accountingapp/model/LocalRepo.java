package com.example.sandy.accountingapp.model;

import android.graphics.Color;
import android.icu.util.Calendar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.example.sandy.accountingapp.dateChart.ChartContract.MONTH;

public class LocalRepo {

    private static LocalRepo INSTANCE;

    public static LocalRepo getInstance() {     //因为不用考虑多线程，故可以用这个比较简单的单例
        if (INSTANCE == null) {
            INSTANCE = new LocalRepo();
        }
        return INSTANCE;
    }

    private LocalRepo() {
    }

    ; //构造器私有化

    private List<User> userList = new ArrayList<>();

    private int currentIndexOfUser;     //用来存当前的UserIndex

    //login
    public boolean checkName(String name) {    //判断在是不是名字
        int i = userList.size();//获得list的大小
        for (int j = 0; j < i; j++) {//遍历找用户
            if (userList.get(j).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassWd(String passWord, String name) {
        User user = userList.get(getIndexByName(name));
        if (user.getPassword().equals(passWord)) {
            return true;
        } else
            return false;

    }


    //method 或许可以加上一个sort
    public String beyondMax() {   //支出超过最大值，预警,传你要预警的语句，若为null则无是
        // TODO: 2019/6/30
        return null;
    }

    public boolean isBeyondDayMax() {////判断是否超过每日最大金额
        double total = 0;
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        int i = calendar.get(Calendar.MONTH) + 1;
        String month = Integer.toString(i);
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        for (Account account : getAccountListByIndex(getCurrentIndexOfUser())) {
            if (year.equals(account.getYear())) {
                if (month.equals(account.getMonth())) {
                    if (day.equals(account.getDay())) {
                        if (!account.isSignal()) {
                            total = total + account.getMoney();
                        }
                    }
                }
            }
        }
        if (total >= getUserList().get(getCurrentIndexOfUser()).getDayMoney() &&
                getUserList().get(getCurrentIndexOfUser()).getDayMoney() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBeyondMonthMax() {//判断是否超过每月最大金额
        double total = 0;
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        for (Account account : getAccountListByIndex(getCurrentIndexOfUser())) {
            if (year.equals(account.getYear())) {
                if (!account.isSignal()) {
                    total = total + account.getMoney();
                }
            }
        }
        if (total >= getUserList().get(getCurrentIndexOfUser()).getMonthMoney() &&
                getUserList().get(getCurrentIndexOfUser()).getMonthMoney() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBeyondWeekMax() {//判断是否超过每周最大金额
        double total = 0;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        for (Account account : getAccountListByIndex(getCurrentIndexOfUser())) {
            if (year.equals(account.getYear())) {
                if (i == getWeek(account.getYear() + "-" + account.getMonth() + "-" +
                        account.getDay())) {
                    if (!account.isSignal()) {
                        total = total + account.getMoney();
                    }
                }
            }
        }
        if (total >= getUserList().get(getCurrentIndexOfUser()).getWeekMoney() &&
                getUserList().get(getCurrentIndexOfUser()).getWeekMoney() != 0.0) {
            return true;
        } else {
            return false;
        }
    }

    public int getWeek(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }


    public List<User> getUserList() {
        return userList;
    }

    public int getIndexByName(String name) {
        int i = userList.size();//获得list的大小
        for (int j = 0; j < i; j++) {//遍历找用户
            if (userList.get(j).getName().equals(name)) {
                return j;
            }
        }
        return 0;
    }

    public void createUser(String name, String passWd) {
        User user = new User(name, passWd);
        List<Account> list = new ArrayList<>(); //必须创建一个list以免为空
        user.setAccountList(list);
        userList.add(user);
        saveUser();
    }

    //list
    public List<Account> getAccountListByIndex(int index) {
        return userList.get(index).getAccountList();
    }

    public int getCurrentIndexOfUser() {
        return currentIndexOfUser;
    }

    public void setCurrentIndexOfUser(int currentIndexOfUser) {
        this.currentIndexOfUser = currentIndexOfUser;
    }

    //chart table
    public PieData getPieData(float range) {

        List<PieEntry> values = new ArrayList<>();    //存实际数据

        float clothQuarter = 0;
        float eatQuarter = 0;
        float goQuarter = 0;
        float studyQuarter = 0;
        float playQuarter = 0;

        for (Account account :
                userList.get(currentIndexOfUser).getAccountList()) {
            if (account.isSignal() == Account.NEGATIVE) {
                switch (account.getType()) {
                    case Account.CLOTH:
                        clothQuarter = (float) (account.getMoney()) + clothQuarter;
                        break;
                    case Account.EAT:
                        eatQuarter += (float) (account.getMoney());
                        break;
                    case Account.GO:
                        goQuarter += (float) (account.getMoney());
                        break;
                    case Account.STUDY:
                        studyQuarter += (float) (account.getMoney());
                        break;
                    case Account.PLAY:
                        playQuarter += (float) (account.getMoney());
                        break;
                }
            }
        }
        if (clothQuarter > 0) {
            values.add(new PieEntry(clothQuarter, "衣服"));
        }
        if (eatQuarter > 0) {
            values.add(new PieEntry(eatQuarter, "食物"));
        }
        if (goQuarter > 0) {
            values.add(new PieEntry(goQuarter, "出行"));
        }
        if (studyQuarter > 0) {
            values.add(new PieEntry(studyQuarter, "学习"));
        }
        if (playQuarter > 0) {
            values.add(new PieEntry(playQuarter, "娱乐"));
        }


        //y轴集合
        PieDataSet pieDataSet = new PieDataSet(values, "消费分类统计");
        pieDataSet.setSliceSpace(0f);   //设置饼状图地之间地距离
        pieDataSet.setColors(Color.BLUE, Color.GREEN, Color.GRAY, Color.YELLOW, Color.DKGRAY);

        return new PieData(pieDataSet);


    }

    private String[] xData = new String[100];

    public LineData getLineData(int type, LineChart lineChart) {
        List<Entry> yEntries = new ArrayList<>();
        List<Account> accounts = userList.get(currentIndexOfUser).getAccountList();
        List<Account> accountsSort = new ArrayList<>(accounts);
        accountsSort.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (Integer.parseInt(o1.getYear()) > Integer.parseInt(o2.getYear())) {
                    return 1;
                } else if (Integer.parseInt(o1.getYear()) < Integer.parseInt(o2.getYear())) {
                    return -1;
                } else {
                    if (Integer.parseInt(o1.getMonth()) > Integer.parseInt(o2.getMonth())) {
                        return 1;
                    } else if (Integer.parseInt(o1.getMonth()) < Integer.parseInt(o2.getMonth())) {
                        return -1;
                    } else {
                        if (Integer.parseInt(o1.getDay()) > Integer.parseInt(o2.getDay())) {
                            return 1;
                        } else if (Integer.parseInt(o1.getDay()) < Integer.parseInt(o2.getDay())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        });
        float[] SumMoney = new float[100];
        if (accountsSort.size() > 0) {
            xData[0] = accountsSort.get(0).getYear() + "年" + accountsSort.get(0).getMonth() + "月";
            int count = 0;
            switch (type) {
                case MONTH:
                    Account minAccount = accountsSort.get(0);
                    for (Account account :
                            accountsSort) {
                        if (account.isSignal() == Account.NEGATIVE) {
                            if (account.compareTo(minAccount) == 0) {
                                SumMoney[count] += account.getMoney();
                            } else if (account.compareTo(minAccount) > 0) {
                                count++;
                                SumMoney[count] += account.getMoney();
                                minAccount = account;
                                xData[count] = minAccount.getYear() + "年" + minAccount.getMonth() + "月";
                            }
                        }
                    }
            }
            for (int i = 0; i <= count; i++) {
                yEntries.add(new Entry(i, SumMoney[i]));
            }
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setEnabled(true);
            xAxis.setDrawAxisLine(true);
            xAxis.setDrawGridLines(true);
            xAxis.setDrawLabels(true);
            xAxis.setLabelCount(count);
            if (count == 0) {
                return null;
            }
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return xData[(int) value];
                }
            });
        } else {
            return null;
        }
        //建立Y轴
        LineDataSet lineDataSet = new LineDataSet(yEntries, "每月消费总额");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setDrawValues(true);
        lineDataSet.setCircleColor(Color.RED);

        return new LineData(lineDataSet);
    }

    public BarData getBarData(BarChart barChart) {
        List<BarEntry> yEntries = new ArrayList<>();
        List<Account> accounts = userList.get(currentIndexOfUser).getAccountList();
        List<Account> accountsSort = new ArrayList<>(accounts);
        accountsSort.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (Integer.parseInt(o1.getYear()) > Integer.parseInt(o2.getYear())) {
                    return 1;
                } else if (Integer.parseInt(o1.getYear()) < Integer.parseInt(o2.getYear())) {
                    return -1;
                } else {
                    if (Integer.parseInt(o1.getMonth()) > Integer.parseInt(o2.getMonth())) {
                        return 1;
                    } else if (Integer.parseInt(o1.getMonth()) < Integer.parseInt(o2.getMonth())) {
                        return -1;
                    } else {
                        if (Integer.parseInt(o1.getDay()) > Integer.parseInt(o2.getDay())) {
                            return 1;
                        } else if (Integer.parseInt(o1.getDay()) < Integer.parseInt(o2.getDay())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        });
        float[] SumMoney = new float[100];
        if (accountsSort.size() > 0) {
            xData[0] = accountsSort.get(0).getYear() + "年" + accountsSort.get(0).getMonth() + "月";
            int count = 0;
            Account minAccount = accountsSort.get(0);
            for (Account account :
                    accountsSort) {
                if (account.isSignal() == Account.NEGATIVE) {
                    if (account.compareTo(minAccount) == 0) {
                        SumMoney[count] += account.getMoney();
                    } else if (account.compareTo(minAccount) > 0) {
                        count++;
                        SumMoney[count] += account.getMoney();
                        minAccount = account;
                        xData[count] = minAccount.getYear() + "年" + minAccount.getMonth() + "月";
                    }
                }
            }
            for (int i = 0; i <= count; i++) {
                yEntries.add(new BarEntry(i, SumMoney[i]));
            }
            XAxis xAxis = barChart.getXAxis();
            xAxis.setEnabled(true);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(true);
            xAxis.setLabelCount(count);
            if (count == 0) {
                return null;
            }
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return xData[(int) value];
                }
            });
        } else {
            return null;
        }


        BarDataSet barDataSet = new BarDataSet(yEntries, "每月消费总额");
        barDataSet.setDrawValues(true);

        return new BarData(barDataSet);
    }

    public void saveUser() {
        User user = userList.get(currentIndexOfUser);
        for (Account account : user.getAccountList()) {
            account.setUser(user);
        }
        LitePal.saveAll(user.getAccountList());
        user.save();
    }

    public void getUsers() {
        List<User> users = LitePal.findAll(User.class, true);
        userList.addAll(users);
    }

    public void deleteAccount(Account account) {
        LitePal.deleteAll(Account.class, "money = ? and mood = ? and type = ? and year = ? and month = ? and day = ?"
                , Double.toString(account.getMoney()),
                Integer.toString(account.getMood()), Integer.toString(account.getType())
                , account.getYear(), account.getMonth(), account.getDay());
    }

}
