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

public class Label2Activity extends BaseActivity implements View.OnClickListener{

    private boolean[] label1List = new boolean[10];
    private boolean[] label2List = new boolean[10];

    @Bind(R.id.new_label2_1)
    CheckBox new_label2_1;
    @Bind(R.id.new_label2_2)
    CheckBox new_label2_2;
    @Bind(R.id.new_label2_3)
    CheckBox new_label2_3;
    @Bind(R.id.new_label2_4)
    CheckBox new_label2_4;
    @Bind(R.id.new_label2_5)
    CheckBox new_label2_5;
    @Bind(R.id.new_label2_random)
    CheckBox new_label2_random;

    @Bind(R.id.btn_next_step_label2)
    Button btn_next_step_label2;
    @Bind(R.id.fab)
    FloatingActionButton btn_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label2);
        ButterKnife.bind(this);

        label1List = (boolean[])getIntent().getExtras().getBooleanArray("label1_list");
        showLog("Label2Activity label1List: ");
        for (boolean b:label1List)
            showLog(b+" ");

        initView();

    }

    void initView(){
        btn_next_step_label2.setOnClickListener(this);
        btn_fab.setOnClickListener(this);
        new_label2_1.setOnClickListener(this);
        new_label2_2.setOnClickListener(this);
        new_label2_3.setOnClickListener(this);
        new_label2_4.setOnClickListener(this);
        new_label2_5.setOnClickListener(this);
        new_label2_random.setOnClickListener(this);

        Arrays.fill(label2List, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next_step_label2:
                if(new_label2_1.isChecked()){
                    Intent intent = new Intent(Label2Activity.this, SubLabel1Activity.class);
                    intent.putExtra("label1_list", label1List);
                    intent.putExtra("label2_list", label2List);
                    startActivity(intent);
                    break;
                }
                else if(new_label2_2.isChecked()){
                    Intent intent = new Intent(Label2Activity.this, SubLabel2Activity.class);
                    intent.putExtra("label1_list", label1List);
                    intent.putExtra("label2_list", label2List);
                    startActivity(intent);
                    break;
                }
                else {
                    Intent intent = new Intent(Label2Activity.this, SceneryActivity.class);
                    intent.putExtra("label1_list", label1List);
                    intent.putExtra("label2_list", label2List);
                    startActivity(intent);
                    break;
                }
            case R.id.fab:
                finish();
                break;
            case R.id.new_label2_1:
                if (new_label2_1.isChecked())
                    label2List[1] = true;
                else
                {
                    new_label2_random.setChecked(false);
                    label2List[6]=false;
                    label2List[1] = false;
                }
                break;
            case R.id.new_label2_2:
                if (new_label2_2.isChecked())
                    label2List[2] = true;
                else
                {
                    new_label2_random.setChecked(false);
                    label2List[6]=false;
                    label2List[2] = false;
                }
                break;
            case R.id.new_label2_3:
                if (new_label2_3.isChecked())
                    label2List[3] = true;
                else
                {
                    new_label2_random.setChecked(false);
                    label2List[6]=false;
                    label2List[3] = false;
                }
                break;
            case R.id.new_label2_4:
                if (new_label2_4.isChecked())
                    label2List[4] = true;
                else
                {
                    new_label2_random.setChecked(false);
                    label2List[6]=false;
                    label2List[4] = false;
                }
                break;
            case R.id.new_label2_5:
                if (new_label2_5.isChecked())
                    label2List[5] = true;
                else
                {
                    new_label2_random.setChecked(false);
                    label2List[6]=false;
                    label2List[5] = false;
                }
                break;
            case R.id.new_label2_random:
                if (new_label2_random.isChecked())
                {
                    new_label2_1.setChecked(true);
                    new_label2_2.setChecked(true);
                    new_label2_3.setChecked(true);
                    new_label2_4.setChecked(true);
                    new_label2_5.setChecked(true);
                    Arrays.fill(label2List, true);
                }
                else
                {
                    new_label2_1.setChecked(false);
                    new_label2_2.setChecked(false);
                    new_label2_3.setChecked(false);
                    new_label2_4.setChecked(false);
                    new_label2_5.setChecked(false);
                    Arrays.fill(label2List, false);
                }
                break;
        }
    }
}
