package com.rokid.glass.imusdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.rokid.glass.instruct.VoiceInstruction;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;

public class IMUSdk {

    private Application mAppContext;

    private static volatile IMUSdk mInstance;

    public static IMUSdk getInstance() {
        if (mInstance == null) {
            synchronized (IMUSdk.class) {
                if (mInstance == null) {
                    mInstance = new IMUSdk();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @param appContext
     */
    public static void init(Application appContext) {
        init(appContext, false);
    }

    /**
     * @param appContext
     * @param useDefaultGlobal 使用默认全局配置
     */
    public static void init(Application appContext, boolean useDefaultGlobal) {
        getInstance().setmAppContext(appContext);
        VoiceInstruction.init(getInstance().getmAppContext());
        if (useDefaultGlobal)
            defaultGlobal();
    }

    /**
     * 设置语音开关
     *
     * @param wakeupState
     */
    public static void setVoiceWakeupState(boolean wakeupState) {
        Util.setVoiceWakeupState(getInstance().getmAppContext(), wakeupState);
    }


    public static void defaultGlobal() {
        // 配置App内部全局指令
        VoiceInstruction.getInstance().addGlobalInstruct(
                new InstructEntity()
                        .setGlobal(true)
                        .setName(OfflineVoice.GLOBAL_BACK)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                try {
                                    if (act != null) {
                                        act.finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
        ).addGlobalInstruct(
                new InstructEntity()
                        .setGlobal(true)
                        .setName(OfflineVoice.GLOBAL_BACK_HOME)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                //TODO add app manager
                                Intent intent = new Intent();
                                // 为Intent设置Action、Category属性
                                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                                act.startActivity(intent);
                            }
                        })
        );

    }

    public Application getmAppContext() {
        return mAppContext;
    }

    public void setmAppContext(Application mAppContext) {
        this.mAppContext = mAppContext;
    }

    interface OfflineVoice {
        String GLOBAL_BACK_HOME = "回到首页";
        String GLOBAL_BACK = "回到上一级";
    }

}
