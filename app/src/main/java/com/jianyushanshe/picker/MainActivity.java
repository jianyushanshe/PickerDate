package com.jianyushanshe.picker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DatePickerView datePickerView;//日历选择器
    TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tv_date);
        showDatePickerPopupWindow();
    }

    /**
     * 显示选择日期的日历
     */
    public void showDatePickerPopupWindow() {
        if (datePickerView == null) {
            datePickerView = new DatePickerView(this);
            datePickerView.setOnDateSelectListener(new DatePickerView.OnDateSelectListener() {
                @Override
                public void selectDate(String showDate, String valueDate) {
                    tvData.setText(showDate);
                }
            });
        }
        datePickerView.showDatePaicker();
    }

}
