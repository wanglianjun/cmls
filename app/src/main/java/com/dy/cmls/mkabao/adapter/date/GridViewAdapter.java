package com.dy.cmls.mkabao.adapter.date;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.date.GridViewData;
import com.dy.cmls.mkabao.bean.date.VDate;
import com.dy.cmls.utils.date.CalendarUtil;

import java.util.Date;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context _context;
    private List<GridViewData> _dataList;
    private VDate todayVdate;
    private boolean _isSelectCheckIn = false;//是否选择了入住日期，如果是，则把之前选的的日期都去掉
    private VDate _checkInVDate;
    private VDate fistDate, lastDate;

    public GridViewAdapter(Context context, VDate checkInVDate, List<GridViewData> dataList) {
        this._context = context;
        this._dataList = dataList;
        this._checkInVDate = checkInVDate;
        Date date = new Date(CalendarUtil.getYear() - 1900, CalendarUtil.getMonth() - 1, CalendarUtil.getDate());
        todayVdate = new VDate(date);
    }

    public GridViewAdapter(Context context, List<GridViewData> dataList) {
        this._context = context;
        this._dataList = dataList;
        Date date = new Date(CalendarUtil.getYear() - 1900, CalendarUtil.getMonth() - 1, CalendarUtil.getDate());
        todayVdate = new VDate(date);
    }

    @Override
    public int getCount() {
        return _dataList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return _dataList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void setSelected() {
        _isSelectCheckIn = true;
    }

    public void setBetweenDate(VDate fistDate, VDate lastDate) {
        this.fistDate = fistDate;
        this.lastDate = lastDate;
        //Log.i("----", "2222" + fistDate.getTime());
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        View itemView = null;
        if (null == view) {
            itemView = ((Activity) _context).getLayoutInflater().inflate(R.layout.item_date_gridview, null);
            view = itemView;
            view.setTag(itemView);
        } else {
            itemView = (View) (view.getTag());
        }
        TextView day = (TextView) itemView.findViewById(R.id.item_date_day);
        //        TextView type = (TextView) itemView.findViewById(R.id.item_date_type);
        //		final ImageView cancel = (ImageView)itemView.findViewById(R.id.item_date_cancel);
        RelativeLayout layout = (RelativeLayout) itemView.findViewById(R.id.item_date_layout);

        layout.setVisibility(View.VISIBLE);
        //		cancel.setVisibility(View.GONE);
        GridViewData data = _dataList.get(position);

        //周末字体用橘色
        if (position == 0 || (position % 7 == 0 || (position + 1) % 7 == 0)) {
            day.setTextColor(ContextCompat.getColor(_context, R.color.text_title1_color));
        } else {
            day.setTextColor(ContextCompat.getColor(_context, R.color.text_title1_color));
        }
        //在设置日期范围外的日子使用灰色字体
        if (null != data.getvDate() && fistDate.compare(data.getvDate()) > 0) {
            day.setTextColor(ContextCompat.getColor(_context, R.color.text_title3_color));
        }
        if (null != data.getvDate() && lastDate.compare(data.getvDate()) < 0) {
            day.setTextColor(ContextCompat.getColor(_context, R.color.text_title3_color));
        }

        day.setBackgroundResource(R.color.transparentWhite00);
        if (data.getDay() == -1) {
            day.setText(" ");
            /*type.setText("选择");
            type.setVisibility(View.INVISIBLE);*/
        } else {
            day.setText("" + data.getDay());
            switch (data.getCheckType()) {
                case GridViewData.CHECK_IN:
                    /*type.setVisibility(View.VISIBLE);
                    type.setText("选择");*/
                    //				cancel.setVisibility(View.VISIBLE);
                    day.setTextColor(ContextCompat.getColor(_context, R.color.main_red2));
                    day.setBackgroundResource(R.drawable.round_red_empty_5);
                    //                    type.setTextColor(_context.getResources().getColor(R.color.main_blue2));
                    //				layout.setBackgroundColor(_context.getResources().getColor(R.color
                    // .head_blue));
                    break;
                case GridViewData.CHECK_NORAML:
                    //                    type.setVisibility(View.INVISIBLE);
                    day.setBackgroundResource(R.color.transparentWhite00);
                    //				layout.setBackgroundColor(_context.getResources().getColor(R.color.white));
                    //判断是否是今天
                    if (data.isToday()) {
                        //					layout.setVisibility(View.INVISIBLE);
                        //					today.setVisibility(View.VISIBLE);
                        day.setText("今天");
                        day.setTextColor(ContextCompat.getColor(_context, R.color.main_blue1));
                    }
                    break;
                default:
                    break;
            }
        }
        return view;
    }

    public void setCheckInVdate(VDate checkInVDate) {
        this._checkInVDate = checkInVDate;
    }
}
