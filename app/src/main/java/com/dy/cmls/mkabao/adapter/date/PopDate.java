package com.dy.cmls.mkabao.adapter.date;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.date.DateLogic;
import com.dy.cmls.mkabao.bean.date.GridViewData;
import com.dy.cmls.mkabao.bean.date.VDate;
import com.dy.cmls.utils.DateUtils;
import com.dy.cmls.utils.date.CalendarUtil;
import com.dy.cmls.view.CustomGridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lcjing on 2018/12/7.
 */

public class PopDate {
    private Context context;
    private TextView tvDate;
    private String huankuan = "05";
    private VDate _checkInDate_ed, _checkOutDate_ed;

    public PopDate() {
    }

    public PopDate(Context context, TextView tvDate, String huankuan, VDate _checkInDate_ed, VDate _checkOutDate_ed) {
        this.context = context;
        this.tvDate = tvDate;
        this.huankuan = huankuan;
        this._checkInDate_ed = _checkInDate_ed;
        this._checkOutDate_ed = _checkOutDate_ed;
        initDate();
    }

    private int year_start, month_start, lastShowYesr, lastShouMouse, nextShowYesr, nextShouMouse,
            countDanDay, _clickPosition = 1;
    private Dialog calendarDialog;
    private List<GridViewData> _dataList1, _dataList2, _dataList3;
    private GridViewAdapter _adapter1, _adapter2, _adapter3;
    private VDate  fistDate, lastDate;
    private CustomGridView _gridView1, _gridView2, _gridView3;
    private View include1, include2, include3;
    private TextView pop_date_tv;
    //一天的时间
    private final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private DateLogic _logic;
    private String in_startdate = "";
    private String in_clodate = "";
    //选择的日期
    private ArrayList<String> dayList;

    private void initDate() {
        //现在的年月日
        String nowDate = DateUtils.getDateToString2(System.currentTimeMillis());
        String[] strs = nowDate.split("-");
        //现在的号数
        String nowDay = strs[2];
        //现在的月份
        String nowMonth = strs[1];
        //现在的年份
        String nowYear = strs[0];

        //如果当前的号数小于账单日
        if (Double.parseDouble(nowDay) < Double.parseDouble(huankuan)) {
            in_startdate = nowDate;
            in_clodate = nowYear + "-" + nowMonth + "-" + huankuan;
        }
        //如果当前的号数大于账单日
        else {
            //开始时间是当前, 结束时间是下一个账单日
            in_startdate = nowDate;
            if (Double.parseDouble(nowMonth) == 12) {
                in_clodate =
                        String.valueOf(Integer.parseInt(nowYear) + 1) + "-" + "1" + "-" + huankuan;
            } else {
                in_clodate = nowYear + "-" + (Integer.parseInt(nowMonth) + 1) + "-" + huankuan;
            }
        }
        initPopWinwow();
    }

    private void initPopWinwow() {
        if (in_startdate.isEmpty() || in_clodate.isEmpty()) {
            showToast("获取时间数据失败");
            return;
        }
        //日历选择框
        _logic = new DateLogic();

        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_date_select, null);
        _dataList1 = new ArrayList<GridViewData>();
        _dataList2 = new ArrayList<GridViewData>();
        _dataList3 = new ArrayList<GridViewData>();
        _adapter1 = new GridViewAdapter(context, _dataList1);
        _adapter2 = new GridViewAdapter(context, _dataList2);
        _adapter3 = new GridViewAdapter(context, _dataList3);

        include1 = (View) contentView.findViewById(R.id.pop_date_include1);
        include2 = (View) contentView.findViewById(R.id.pop_date_include2);
        include3 = (View) contentView.findViewById(R.id.pop_date_include3);
        pop_date_tv = (TextView) contentView.findViewById(R.id.pop_date_tv);
        TextView tvClose = (TextView) contentView.findViewById(R.id.tvClose);
        TextView tvConfirm = (TextView) contentView.findViewById(R.id.tvConfirm);
        ImageView ivLast = (ImageView) contentView.findViewById(R.id.ivLast);
        ImageView ivNext = (ImageView) contentView.findViewById(R.id.ivNext);
        include2.setVisibility(View.GONE);
        include3.setVisibility(View.GONE);
        _gridView1 = (CustomGridView) include1.findViewById(R.id.include_date_gridview);
        _gridView2 = (CustomGridView) include2.findViewById(R.id.include_date_gridview);
        _gridView3 = (CustomGridView) include3.findViewById(R.id.include_date_gridview);
//        _month1 = (TextView) include1.findViewById(R.id.include_date_month);
//        _month2 = (TextView) include2.findViewById(R.id.include_date_month);
//        _month3 = (TextView) include3.findViewById(R.id.include_date_month);

        _gridView1.setAdapter(_adapter1);
        _gridView2.setAdapter(_adapter2);
        _gridView3.setAdapter(_adapter3);

        initTimeData();
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarDialog.dismiss();
                _clickPosition = 1;
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _clickPosition = 1;
                selectDate();
            }
        });
        ivLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLastMonth();
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNextMonth();
            }
        });
        calendarDialog = new Dialog(context, R.style.ShopbagDialogStyle);
        calendarDialog.setContentView(contentView);
        /*//将对话框的大小按屏幕大小的百分比设置
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = calendarDialog.getWindow().getAttributes(); //
        // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.8); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.65
        calendarDialog.getWindow().setAttributes(p);*/
        calendarDialog.setCanceledOnTouchOutside(false);
//        calendarDialog.show();
    }

    public void show(){
        calendarDialog.show();
    }

    private void initTimeData() {
        if (in_startdate.isEmpty() || in_clodate.isEmpty()) {
            return;
        }
        String[] str_start = in_startdate.split("-");
        String[] str_end = in_clodate.split("-");
        //获取后台年月，demo为当前年月
        year_start = Integer.parseInt(str_start[0].toString());
        month_start = Integer.parseInt(str_start[1].toString());
        int day_start = Integer.parseInt(str_start[2].toString());
        int year_end = Integer.parseInt(str_end[0].toString());
        int month_end = Integer.parseInt(str_end[1].toString());
        int day_end = Integer.parseInt(str_end[2].toString());
        int jobTime = 0;
        //计算工作总时间
        if (("" + month_end).equals("" + month_start)) {
            jobTime = day_end - day_start;
        } else {
            int monthCha = month_end - month_start;
            int lastMonthJobDay = CalendarUtil.getMonthMaxDay(year_start, month_start) - day_start;
            int nextMonthJobDay = day_end;
            jobTime = lastMonthJobDay + nextMonthJobDay;
        }
        //        Log.e("兼职的工作时间差", "" + jobTime);
        pop_date_tv.setText(month_start + "月，" + year_start);
        if (month_start == 12) {
            nextShouMouse = 1;
            nextShowYesr = year_start + 1;
        } else {
            nextShouMouse = month_start + 1;
            nextShowYesr = year_start;
        }
        if (month_start == 1) {
            lastShouMouse = 12;
            lastShowYesr = year_start - 1;
        } else {
            lastShouMouse = month_start - 1;
            lastShowYesr = year_start;
        }

        //中间月
        _dataList1.addAll(
                _logic.getDateList(year_start, month_start, _checkInDate_ed, _checkOutDate_ed));

        //下一月
        if (month_start == 12) {
            _dataList2.addAll(
                    _logic.getDateList(year_start + 1, 1, _checkInDate_ed, _checkOutDate_ed));
        } else {
            _dataList2.addAll(
                    _logic.getDateList(year_start, month_start + 1, _checkInDate_ed, _checkOutDate_ed));
        }
        //上一月
        if (month_start == 1) {
            _dataList3.addAll(
                    _logic.getDateList(year_start - 1, 12, _checkInDate_ed, _checkOutDate_ed));
        } else {
            _dataList3.addAll(
                    _logic.getDateList(year_start, month_start - 1, _checkInDate_ed, _checkOutDate_ed));
        }

        //Log.e("TJdebug", "兼职开始的时间;" + in_startdate);
        //在这里获取后台提供的时间间隔
        if (System.currentTimeMillis() >= (CalendarUtil.getMilliSecondsFromDate(in_startdate))) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis());
            String str = formatter.format(curDate);
            Date date1 = new Date(CalendarUtil.getMilliSecondsFromDate(str));
            fistDate = new VDate(date1);
        } else {
            Date date1 = new Date(CalendarUtil.getMilliSecondsFromDate(in_startdate));
            fistDate = new VDate(date1);
        }
        Date date2 =
                new Date(CalendarUtil.getMilliSecondsFromDate(in_startdate) + DAY_IN_MILLIS * jobTime);
        lastDate = new VDate(date2);

        _adapter1.setBetweenDate(fistDate, lastDate);
        _adapter3.setBetweenDate(fistDate, lastDate);
        _adapter2.setBetweenDate(fistDate, lastDate);

        _gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //_clickPosition = 0;
                judgeDate(_dataList1.get(position).getDay(), position, _dataList1);
            }
        });

        _gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //_clickPosition = 1;
                judgeDate(_dataList2.get(position).getDay(), position, _dataList2);
            }
        });

        _gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //_clickPosition = 3;
                judgeDate(_dataList3.get(position).getDay(), position, _dataList3);
            }
        });
    }

    private void judgeDate(int day, int position, List<GridViewData> dataList) {
        if (day == -1) {
            return;
        }
        VDate vDate = dataList.get(position).getvDate();
        if (null != vDate) {
            //设置选择的日期
            if (GridViewData.CHECK_IN != dataList.get(position).getCheckType()) {
                if (null != vDate && fistDate.compare(vDate) <= 0 && lastDate.compare(vDate) >= 0) {
                    dataList.get(position).setCheckType(GridViewData.CHECK_IN);
                }
            } else {
                dataList.get(position).setCheckType(GridViewData.CHECK_NORAML);
            }
        } else {

        }
        _adapter1.notifyDataSetChanged();
        _adapter2.notifyDataSetChanged();
        _adapter3.notifyDataSetChanged();
    }

    private void clickLastMonth() {
        if (_clickPosition <= 0) {
            return;
        } else {
            _clickPosition = _clickPosition - 1;
            if (_clickPosition == 0) {
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.VISIBLE);
                pop_date_tv.setText(lastShouMouse + "月，" + lastShowYesr);
            } else if (_clickPosition == 1) {
                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                pop_date_tv.setText(month_start + "月，" + year_start);
            } else if (_clickPosition == 2) {
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.VISIBLE);
                include3.setVisibility(View.GONE);
                pop_date_tv.setText(nextShouMouse + "月，" + nextShowYesr);
            }
        }
    }

    private void clickNextMonth() {
        if (_clickPosition >= 2) {
            return;
        } else {
            _clickPosition = _clickPosition + 1;
            if (_clickPosition == 0) {
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.VISIBLE);
                pop_date_tv.setText(lastShouMouse + "月，" + lastShowYesr);
            } else if (_clickPosition == 1) {
                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                pop_date_tv.setText(month_start + "月，" + year_start);
            } else if (_clickPosition == 2) {
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.VISIBLE);
                include3.setVisibility(View.GONE);
                pop_date_tv.setText(nextShouMouse + "月，" + nextShowYesr);
            }
        }
    }

    private void selectDate() {
        dayList = new ArrayList<>();
        List<VDate> choose = new ArrayList<VDate>();
        String data2 = "";
        String data3 = "";
        for (int i = 0; i < _dataList1.size(); i++) {
            if (_dataList1.get(i).getCheckType() == GridViewData.CHECK_IN) {
                dayList.add(_dataList1.get(i).getvDate().getYear() + "-" + _dataList1.get(i)
                        .getvDate()
                        .getMonth() + "-" + _dataList1.get(i).getvDate().getDay());
                countDanDay++;
            }
        }
        for (int i = 0; i < _dataList2.size(); i++) {
            if (_dataList2.get(i).getCheckType() == GridViewData.CHECK_IN) {
                dayList.add(_dataList2.get(i).getvDate().getYear() + "-" + _dataList2.get(i)
                        .getvDate()
                        .getMonth() + "-" + _dataList2.get(i).getvDate().getDay());
                countDanDay++;
            }
        }
        for (int i = 0; i < _dataList3.size(); i++) {
            if (_dataList3.get(i).getCheckType() == GridViewData.CHECK_IN) {
                dayList.add(_dataList3.get(i).getvDate().getYear() + "-" + _dataList3.get(i)
                        .getvDate()
                        .getMonth() + "-" + _dataList3.get(i).getvDate().getDay());
                countDanDay++;
            }
        }
        if (countDanDay == 0) {
            showToast("至少选择一天");
        } else {
            String tempSetDate = "";
            for (String str : dayList) {
                if (tempSetDate.length() > 0) {
                    tempSetDate += ",";
                }
                tempSetDate += str;
            }
            calendarDialog.dismiss();
            countDanDay = 0;
            tvDate.setText(tempSetDate);
        }
    }



    //弹出吐司
    protected void showToast(final String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
