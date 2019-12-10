package com.rokid.glass.imusdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.rokid.glass.instruct.entity.InstructConfig;

/**
 * BaseActivity 语音功能基础类
 *
 * @date 2019-08-05
 */

public abstract class BaseActivity extends BaseInstructionActivity {
    private InstructConfig mEmptyConfig;
    private InstructConfig mInstructConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public InstructConfig configInstruct() {
        mEmptyConfig = new InstructConfig();
        mEmptyConfig.setActionKey(getClass().getName() + InstructConfig.ACTION_SUFFIX);
        mInstructConfig = initInstruct();
        return mInstructConfig;
    }

    @Override
    public boolean doReceiveCommand(String command) {
        return false;
    }

    @Override
    public boolean closeInstruction() {
        return super.closeInstruction();
    }

    @Override
    protected void onResume() {
        if (Util.getVoiceWakeup(getApplication())) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            showVoiceLayer();
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            hideVoiceLayer();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        int wakeup = BaseLibrary.getInstance().getVoiceWakeup();
//        if (wakeup == AppConfig.Settings.VOICE_OFF) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        }
    }




    public InstructConfig initInstruct() {
        return mEmptyConfig;
    }

    protected void hideVoiceLayer() {
        mInstructionManager.setInstructConfig(mEmptyConfig);
        mInstructionManager.hideTipsLayer();
    }

    protected void showVoiceLayer() {
        mInstructionManager.setInstructConfig(mInstructConfig);
        mInstructionManager.showTipsLayer();
    }

    public BaseActivity setInstructConfig(InstructConfig instructConfig) {
        this.mInstructConfig = instructConfig;
        return this;
    }

}
