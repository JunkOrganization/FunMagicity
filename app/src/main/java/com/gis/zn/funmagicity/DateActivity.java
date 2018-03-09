package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.DatePicker;

import com.gis.zn.funmagicity.ui.BaseActivity;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DateActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.datePicker_start)
    DatePicker datePicker_start;
    @Bind(R.id.fab_date_start)
    FloatingActionButton fab_date_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_start);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.datePicker_start:
                break;
            case R.id.fab_date_start:
                int y=datePicker_start.getYear();
                int m=datePicker_start.getMonth()+1;
                int d=datePicker_start.getDayOfMonth();
                Date date=new Date(y,m,d);
                date_start=date;
                Intent intent = new Intent(DateActivity.this,DateEndActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initView(){
        fab_date_start.setOnClickListener(this);
    }

}
