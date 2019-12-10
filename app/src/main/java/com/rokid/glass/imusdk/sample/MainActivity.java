package com.rokid.glass.imusdk.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.rokid.glass.imusdk.BaseActivity;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructConfig;
import com.rokid.glass.instruct.entity.InstructEntity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public InstructConfig initInstruct() {

        InstructConfig config = new InstructConfig();
        config.setActionKey(getClass().getName() + InstructConfig.ACTION_SUFFIX);
        config.addInstructEntity(new InstructEntity()
                .setName("语音测试")
                .setShowTips(true)
                .setCallback(new IInstructReceiver() {
                    @Override
                    public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                        Toast.makeText(MainActivity.this, "语音测试", Toast.LENGTH_LONG).show();
                    }
                }));
        return config;
    }

}
