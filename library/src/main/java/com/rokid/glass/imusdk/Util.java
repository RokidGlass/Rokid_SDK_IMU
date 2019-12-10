package com.rokid.glass.imusdk;

import android.app.Application;
import android.provider.Settings;

public class Util {
    private static String DB_VOICE_WAKEUP_KEY = "rokid_wakeup_setting";
    private static int VOICE_OFF = 0;
    private static int VOICE_ON = 1;

    /**
     * 语音开关是否打开
     *
     * @param application
     * @return
     */
    public static boolean getVoiceWakeup(Application application) {
        int wakeup = Settings.Global.getInt(
                application.getContentResolver(),
                DB_VOICE_WAKEUP_KEY, VOICE_OFF);
        return (wakeup == VOICE_ON);
    }

    /**
     * 设置语音开关
     * @param application
     * @param wakeup
     */
    public static void setVoiceWakeupState(Application application, boolean wakeup) {
        int wakeupInt = wakeup ? VOICE_ON : VOICE_OFF;
        android.provider.Settings.Global.putInt(application.getContentResolver(),
                DB_VOICE_WAKEUP_KEY, wakeupInt);
    }


}
