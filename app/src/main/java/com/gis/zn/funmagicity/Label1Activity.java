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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Label1Activity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.label1_1)
    CheckBox label1_1;
    @Bind(R.id.label1_2)
    CheckBox label1_2;
    @Bind(R.id.label1_3)
    CheckBox label1_3;
    @Bind(R.id.label1_4)
    CheckBox label1_4;
    @Bind(R.id.label1_5)
    CheckBox label1_5;
    @Bind(R.id.label1_random)
    CheckBox label1_random;

    @Bind(R.id.btn_next_step_label1)
    Button btn_next_step_label1;
    @Bind(R.id.fab)
    FloatingActionButton btn_fab;

    private boolean[] label1List = new boolean[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label1);
        ButterKnife.bind(this);
        initView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step_label1:
                if(!label1_1.isChecked()&&!label1_2.isChecked()&&!label1_3.isChecked()&&
                        !label1_4.isChecked()&&!label1_5.isChecked()&&!label1_random.isChecked())
                {
                    toast("出行小伙伴请至少选择一项！");
                    break;
                }
                Intent intent = new Intent(Label1Activity.this, Label2Activity.class);
                intent.putExtra("label1_list", label1List);
                startActivity(intent);
                break;
            case R.id.fab:
                finish();
                break;
            case R.id.label1_1:
                if (label1_1.isChecked())
                    label1List[1] = true;
                else
                {
                    label1_random.setChecked(false);
                    label1List[6]=false;
                    label1List[1] = false;
                }
                break;
            case R.id.label1_2:
                if (label1_2.isChecked())
                    label1List[2] = true;
                else
                {
                    label1_random.setChecked(false);
                    label1List[6]=false;
                    label1List[2] = false;
                }
                break;
            case R.id.label1_3:
                if (label1_3.isChecked())
                    label1List[3] = true;
                else
                {
                    label1_random.setChecked(false);
                    label1List[6]=false;
                    label1List[3] = false;
                }
                break;
            case R.id.label1_4:
                if (label1_4.isChecked())
                    label1List[4] = true;
                else
                {
                    label1_random.setChecked(false);
                    label1List[6]=false;
                    label1List[4] = false;
                }
                break;
            case R.id.label1_5:
                if (label1_5.isChecked())
                    label1List[5] = true;
                else
                {
                    label1_random.setChecked(false);
                    label1List[6]=false;
                    label1List[5] = false;
                }
                break;
            case R.id.label1_random:
                if (label1_random.isChecked()) {
                    label1_1.setChecked(true);
                    label1_2.setChecked(true);
                    label1_3.setChecked(true);
                    label1_4.setChecked(true);
                    label1_5.setChecked(true);
                    Arrays.fill(label1List, true);
                } else {
                    label1_1.setChecked(false);
                    label1_2.setChecked(false);
                    label1_3.setChecked(false);
                    label1_4.setChecked(false);
                    label1_5.setChecked(false);
                    Arrays.fill(label1List, false);
                }
                break;
        }
    }

    void initView() {
        btn_next_step_label1.setOnClickListener(this);
        btn_fab.setOnClickListener(this);
        label1_1.setOnClickListener(this);
        label1_2.setOnClickListener(this);
        label1_3.setOnClickListener(this);
        label1_4.setOnClickListener(this);
        label1_5.setOnClickListener(this);
        label1_random.setOnClickListener(this);

        Arrays.fill(label1List, false);

    }
}
