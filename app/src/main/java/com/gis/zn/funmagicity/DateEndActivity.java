package com.gis.zn.funmagicity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.gis.zn.funmagicity.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DateEndActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.datePicker_end)
    DatePicker datePicker_end;
    @Bind(R.id.fab_date_end)
    FloatingActionButton fab_date_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_end);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.datePicker_end:
                break;
            case R.id.fab_date_end:
                int y = datePicker_end.getYear();
                int m = datePicker_end.getMonth() + 1;
                int d = datePicker_end.getDayOfMonth();
                Date date = new Date(y, m, d);
                date_end = date;

                long day = (date.getTime() - date_start.getTime()) / (24 * 60 * 60 * 1000) + 1;
                setDays((int) day);
                setCurrentDay(1);
                Log.e("DateEndActivity", String.valueOf(getDays()));
                Log.e("DateEndActivity", String.valueOf(getCurrentDay()));
                Intent intent = new Intent(DateEndActivity.this, Label1Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void initView() {
        fab_date_end.setOnClickListener(this);
    }
}
