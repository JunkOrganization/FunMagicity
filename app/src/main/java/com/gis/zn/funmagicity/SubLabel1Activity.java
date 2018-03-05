package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.gis.zn.funmagicity.ui.BaseActivity;
import com.gis.zn.funmagicity.ui.SceneryActivity;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubLabel1Activity extends BaseActivity implements View.OnClickListener {

    private boolean[] label1List = new boolean[10];
    private boolean[] label2List = new boolean[10];
    private boolean[] sub1LabelList = new boolean[10];

    @Bind(R.id.sub1_label1)
    CheckBox sub1_label1;
    @Bind(R.id.sub1_label2)
    CheckBox sub1_label2;
    @Bind(R.id.sub1_label3)
    CheckBox sub1_label3;
    @Bind(R.id.sub1_label4)
    CheckBox sub1_label4;
    @Bind(R.id.sub1_label5)
    CheckBox sub1_label5;
    @Bind(R.id.sub1_label_all)
    CheckBox sub1_label_all;

    @Bind(R.id.btn_next_step_sub1)
    Button btn_next_step_sub1;
    @Bind(R.id.fab)
    FloatingActionButton btn_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_label1);
        ButterKnife.bind(this);
        initView();

        label1List = (boolean[])getIntent().getExtras().getBooleanArray("label1_list");
        label2List = (boolean[])getIntent().getExtras().getBooleanArray("label2_list");
        showLog("SubLabel1Activity label1List: ");
        for (boolean b:label1List)
            showLog(b+" ");
        showLog("label2List: ");
        for (boolean b:label2List)
            showLog(b+" ");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step_sub1:
                if(!sub1_label1.isChecked()&&!sub1_label2.isChecked()&&!sub1_label3.isChecked()&&
                        !sub1_label4.isChecked()&&!sub1_label5.isChecked()&&!sub1_label_all.isChecked())
                {
                    toast("特色美食请至少选择一项！");
                    break;
                }
                if(label2List[2]==true){
                    showLog("子标签一下一步（2被选中）");
                    Intent intent = new Intent(SubLabel1Activity.this, SubLabel2Activity.class);
                    intent.putExtra("label1_list", label1List);
                    intent.putExtra("label2_list", label2List);
                    intent.putExtra("sub1_label_list", sub1LabelList);
                    startActivity(intent);
                    break;
                }
                else {
                    Intent intent = new Intent(SubLabel1Activity.this, SelectSpotsActivity.class);
                    intent.putExtra("label1_list", label1List);
                    intent.putExtra("label2_list", label2List);
                    intent.putExtra("sub1_label_list", sub1LabelList);
                    startActivity(intent);
                    break;
                }
            case R.id.fab:
                finish();
                break;
            case R.id.sub1_label1:
                if (sub1_label1.isChecked())
                    sub1LabelList[1] = true;
                else
                {
                    sub1_label_all.setChecked(false);
                    sub1LabelList[6]=false;
                    sub1LabelList[1] = false;
                }
                break;
            case R.id.sub1_label2:
                if (sub1_label2.isChecked())
                    sub1LabelList[2] = true;
                else
                {
                    sub1_label_all.setChecked(false);
                    sub1LabelList[6]=false;
                    sub1LabelList[2] = false;
                }
                break;
            case R.id.sub1_label3:
                if (sub1_label3.isChecked())
                    sub1LabelList[3] = true;
                else
                {
                    sub1_label_all.setChecked(false);
                    sub1LabelList[6]=false;
                    sub1LabelList[3] = false;
                }
                break;
            case R.id.sub1_label4:
                if (sub1_label4.isChecked())
                    sub1LabelList[4] = true;
                else
                {
                    sub1_label_all.setChecked(false);
                    sub1LabelList[6]=false;
                    sub1LabelList[4] = false;
                }
                break;
            case R.id.sub1_label5:
                if (sub1_label2.isChecked())
                    sub1LabelList[5] = true;
                else
                {
                    sub1_label_all.setChecked(false);
                    sub1LabelList[6]=false;
                    sub1LabelList[5] = false;
                }
                break;
            case R.id.sub1_label_all:
                if (sub1_label_all.isChecked())
                {
                    sub1_label1.setChecked(true);
                    sub1_label2.setChecked(true);
                    sub1_label3.setChecked(true);
                    sub1_label4.setChecked(true);
                    sub1_label5.setChecked(true);
                    Arrays.fill(sub1LabelList, true);
                }
                else {
                    sub1_label1.setChecked(false);
                    sub1_label2.setChecked(false);
                    sub1_label3.setChecked(false);
                    sub1_label4.setChecked(false);
                    sub1_label5.setChecked(false);
                    Arrays.fill(sub1LabelList, false);
                }
                break;
        }
    }

    private void initView() {
        sub1_label1.setOnClickListener(this);
        sub1_label2.setOnClickListener(this);
        sub1_label3.setOnClickListener(this);
        sub1_label4.setOnClickListener(this);
        sub1_label5.setOnClickListener(this);
        sub1_label_all.setOnClickListener(this);
        btn_next_step_sub1.setOnClickListener(this);
        btn_fab.setOnClickListener(this);
        Arrays.fill(sub1LabelList, false);
    }

}
