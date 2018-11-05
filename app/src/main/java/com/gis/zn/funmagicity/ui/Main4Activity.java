package com.gis.zn.funmagicity.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gis.zn.funmagicity.R;

import butterknife.Bind;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.edit_description)
    EditText mEditText;

    @Bind(R.id.btn_send_msg)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mButton = (Button)findViewById(R.id.btn_send_msg);
        mEditText = (EditText)findViewById(R.id.edit_description);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_msg:
                Toast.makeText(getBaseContext(), "协助成功！", Toast.LENGTH_SHORT).show();
                mEditText.setText("");
                break;
//            case R.id.btn_album:
//                open_album();
//                break;
        }
    }
}
