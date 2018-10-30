package com.meishakeji.thirdapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PluginMainActivity extends BaseActivity  implements View.OnClickListener{

    @Override
    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_main);
        Button mBtnTest= (Button) findViewById(R.id.btn_test);
        mBtnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        thisContext.startActivity(new Intent(thisContext,PluginSecondActivity.class));
    }
}
