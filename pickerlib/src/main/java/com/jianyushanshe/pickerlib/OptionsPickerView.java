package com.jianyushanshe.pickerlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 条件选择器
 * Created by Sai on 15/11/22.
 */
public class OptionsPickerView<T> extends BasePickerView implements View.OnClickListener {
    WheelOptions<T> wheelOptions;
    private Button btnSubmit, btnCancel;
    private TextView tvTitle, tvDesc, tvHint;
    private OnOptionsSelectListener optionsSelectListener;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";

    public OptionsPickerView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pickerview_options, contentContainer);
        // -----确定和取消按钮
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        //顶部提示语
        tvHint = (TextView) findViewById(R.id.tv_select_hint);
        //描述
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        // ----转轮
        final View optionspicker = findViewById(R.id.optionspicker);
        wheelOptions = new WheelOptions(optionspicker);
    }

    /**
     * 设置顶部提示语是否显示
     *
     * @param isShow
     */
    public void setHintShow(boolean isShow) {
        tvHint.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setBtnSubmitColor(int color) {
        btnSubmit.setTextColor(color);
    }

    public void setBtnCancelColor(int color) {
        btnCancel.setTextColor(color);
    }

    /**
     * 设置提示语
     *
     * @param hint
     */
    public void setHintText(String hint) {
        tvHint.setText(hint);
    }

    public void setPicker(ArrayList<T> optionsItems) {
        wheelOptions.setPicker(optionsItems, null, null, false);
    }

    public void setPicker(ArrayList<T> options1Items,
                          ArrayList<ArrayList<T>> options2Items, boolean linkage) {
        wheelOptions.setPicker(options1Items, options2Items, null, linkage);
    }

    public void setPicker(ArrayList<T> options1Items,
                          ArrayList<ArrayList<T>> options2Items,
                          ArrayList<ArrayList<ArrayList<T>>> options3Items,
                          boolean linkage) {
        wheelOptions.setPicker(options1Items, options2Items, options3Items,
                linkage);
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     */
    public void setSelectOptions(int option1) {
        wheelOptions.setCurrentItems(option1, 0, 0);
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     * @param option2 位置
     */
    public void setSelectOptions(int option1, int option2) {
        wheelOptions.setCurrentItems(option1, option2, 0);
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     * @param option2 位置
     * @param option3 位置
     */
    public void setSelectOptions(int option1, int option2, int option3) {
        wheelOptions.setCurrentItems(option1, option2, option3);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     */
    public void setLabels(String label1) {
        wheelOptions.setLabels(label1, null, null);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     * @param label2 单位
     */
    public void setLabels(String label1, String label2) {
        wheelOptions.setLabels(label1, label2, null);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     * @param label2 单位
     * @param label3 单位
     */
    public void setLabels(String label1, String label2, String label3) {
        wheelOptions.setLabels(label1, label2, label3);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wheelOptions.setCyclic(cyclic);
    }

    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        wheelOptions.setCyclic(cyclic1, cyclic2, cyclic3);
    }

    /**
     * 设置一级是否显示
     *
     * @param b
     */
    public void setWv_option1IsShow(boolean b) {
        wheelOptions.setWv_option1IsShow(b);
    }

    /**
     * 设置2级是否显示
     *
     * @param b
     */
    public void setWv_option2IsShow(boolean b) {
        wheelOptions.setWv_option2IsShow(b);
    }

    /**
     * 设置3级是否显示
     *
     * @param b
     */
    public void setWv_option3IsShow(boolean b) {
        wheelOptions.setWv_option3IsShow(b);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
        } else {
            if (optionsSelectListener != null) {
                int[] optionsCurrentItems = wheelOptions.getCurrentItems();
                optionsSelectListener.onOptionsSelect(optionsCurrentItems[0], optionsCurrentItems[1], optionsCurrentItems[2]);
            }
        }
    }


    public interface OnOptionsSelectListener {
        void onOptionsSelect(int options1, int option2, int options3);
    }

    public void setOnoptionsSelectListener(
            OnOptionsSelectListener optionsSelectListener) {
        this.optionsSelectListener = optionsSelectListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置标题颜色
     * @param color
     */
    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 设置滚轮文字颜色
     * @param colorOut
     * @param colorCenter
     * @param divider
     */
    public void setWheelOptionsColor(int colorOut, int colorCenter, int divider) {
        wheelOptions.setWheelColor(colorOut, colorCenter, divider);
    }

    public void setDesc(String desc) {
        tvDesc.setText(desc);
        tvDesc.setVisibility(View.VISIBLE);
    }
}
