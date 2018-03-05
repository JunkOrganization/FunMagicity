package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.gis.zn.funmagicity.ui.BaseActivity;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubLabel2Activity extends BaseActivity implements View.OnClickListener {

    private boolean[] label1List = new boolean[10];
    private boolean[] label2List = new boolean[10];
    private boolean[] sub1LabelList = new boolean[10];
    private boolean[] sub2LabelList = new boolean[10];

    @Bind(R.id.sub2_label1)
    CheckBox sub2_label1;
    @Bind(R.id.sub2_label2)
    CheckBox sub2_label2;
    @Bind(R.id.sub2_label3)
    CheckBox sub2_label3;
    @Bind(R.id.sub2_label_all)
    CheckBox sub2_label_all;

    @Bind(R.id.btn_next_step_sub2)
    Button btn_next_step_sub2;
    @Bind(R.id.fab)
    FloatingActionButton btn_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_label2);
        ButterKnife.bind(this);

        initView();

        label1List = (boolean[]) getIntent().getExtras().getBooleanArray("label1_list");
        label2List = (boolean[]) getIntent().getExtras().getBooleanArray("label2_list");
        sub1LabelList = (boolean[]) getIntent().getExtras().getBooleanArray("sub1_label_list");
        showLog("SubLabel2Activity label1List: ");
        for (boolean b : label1List)
            showLog(b + " ");
        showLog("label2List: ");
        for (boolean b : label2List)
            showLog(b + " ");
        showLog("sub1_label_list: ");
        for (boolean b : sub1LabelList)
            showLog(b + " ");

    }

    private void initView() {
        sub2_label1.setOnClickListener(this);
        sub2_label2.setOnClickListener(this);
        sub2_label3.setOnClickListener(this);
        sub2_label_all.setOnClickListener(this);
        btn_next_step_sub2.setOnClickListener(this);
        btn_fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step_sub2:
                if (!sub2_label1.isChecked() && !sub2_label2.isChecked() &&
                        !sub2_label3.isChecked() && !sub2_label_all.isChecked()) {
                    toast("风景摄影请至少选择一项！");
                    break;
                }
                Intent intent = new Intent(SubLabel2Activity.this, SelectSpotsActivity.class);
                intent.putExtra("label1_list", label1List);
                intent.putExtra("label2_list", label2List);
                intent.putExtra("sub1_label_list", sub1LabelList);
                intent.putExtra("sub2_label_list", sub2LabelList);
                startActivity(intent);
                break;
            case R.id.fab:
                finish();
                break;
            case R.id.sub2_label1:
                if (sub2_label1.isChecked())
                    sub2LabelList[1] = true;
                else {
                    sub2_label_all.setChecked(false);
                    sub2LabelList[4] = false;
                    sub2LabelList[1] = false;
                }
                break;
            case R.id.sub2_label2:
                if (sub2_label2.isChecked())
                    sub2LabelList[2] = true;
                else {
                    sub2_label_all.setChecked(false);
                    sub2LabelList[4] = false;
                    sub2LabelList[2] = false;
                }
                break;
            case R.id.sub2_label3:
                if (sub2_label3.isChecked())
                    sub2LabelList[3] = true;
                else {
                    sub2_label_all.setChecked(false);
                    sub2LabelList[4] = false;
                    sub2LabelList[3] = false;
                }
                break;
            case R.id.sub2_label_all:
                if (sub2_label_all.isChecked()) {
                    sub2_label1.setChecked(true);
                    sub2_label2.setChecked(true);
                    sub2_label3.setChecked(true);
                    Arrays.fill(sub2LabelList, true);
                } else {
                    sub2_label1.setChecked(false);
                    sub2_label2.setChecked(false);
                    sub2_label3.setChecked(false);
                    Arrays.fill(sub2LabelList, false);
                }
                break;
        }
    }
}
