package com.jianyushanshe.picker;

import android.content.Context;

import com.jianyushanshe.pickerlib.OptionsPickerView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Author:wangjianming
 * Time:2019/7/19 16:04
 * Description:DataPikcerView 日期选择
 */
public class DatePickerView {

    private Context context;
    private ArrayList<String> options1Items = new ArrayList<>();  //占位用
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    public DatePickerView(Context context) {
        this.context = context;
        initDatePicker(context);
    }

    public void setSelectItem(int year, int month) {
        pvOptions.setSelectOptions(0, year, month);
    }

    public interface OnDateSelectListener {
        void selectDate(String showDate, String valueDate);
    }

    public OnDateSelectListener onDateSelectListener;

    public void setOnDateSelectListener(OnDateSelectListener onDateSelectListener) {
        this.onDateSelectListener = onDateSelectListener;
    }

    private void initDatePicker(Context context) {
        //选项选择器
        pvOptions = new OptionsPickerView(context);
        //选项1
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);

        ArrayList<String> options2Items_all = new ArrayList<>();
        for (int i = year; i <= year + 10; i++) {
            options2Items_all.add(i + "");
        }
        options2Items.add(options2Items_all);

        //选项2
        ArrayList<String> options3Items_all = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            options3Items_all.add(i + "月");
        }
        for (int i = 0; i < options2Items.size(); i++) {
            ArrayList<ArrayList<String>> a3 = new ArrayList<>();
            a3.add(options3Items_all);
            options3Items.add(a3);
        }


        //二级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, false);
        pvOptions.setTitle("选择年月");
        pvOptions.setCyclic(false, false, false);
        pvOptions.setCancelable(true);
        pvOptions.setBtnSubmitColor(context.getResources().getColor(R.color.colorPrimary));
        pvOptions.setWheelOptionsColor(0, context.getResources().getColor(R.color.colorPrimary), 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String year = options2Items.get(0).get(option2);
                String month = options3Items.get(0).get(0).get(options3).replace("月", "");
                if (Integer.parseInt(month) < 10) {
                    month = "0" + month;
                }
                onDateSelectListener.selectDate(year + "年" + month + "月", year + "-" + month + "-01");
                dissmissDatePicker();
            }
        });
        //隐藏选项1
        pvOptions.setWv_option1IsShow(false);
    }

    public boolean isShowing() {
        boolean b = false;
        if (pvOptions != null) {
            b = pvOptions.isShowing();
        }
        return b;
    }

    /**
     * 显示时间选择器
     */
    public void showDatePaicker() {
        if (pvOptions != null) {
            pvOptions.show();
        }
    }

    /**
     * 隐藏时间选择器
     */
    public void dissmissDatePicker() {
        if (pvOptions != null && pvOptions.isShowing()) {
            pvOptions.dismiss();
        }
    }

    public void onDestroy() {
        if (pvOptions != null) {
            pvOptions.dismiss();
        }
        pvOptions = null;
        context = null;
    }

}
