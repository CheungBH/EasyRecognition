package com.arcsoft.sdk_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowInformation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);

        Intent intent=getIntent();
        String thename=intent.getStringExtra("names");
        String theinfor=intent.getStringExtra("infor");

        TextView show_name = (TextView) findViewById(R.id.show_name);
        TextView show_content= (TextView) findViewById(R.id.show_content);

        show_name.setText(thename);
        show_content.setText(theinfor);
    }
}
