package com.sixin.filemvc;

import android.app.Application;
import android.content.Context;

import com.sixin.filemvc.utils.LogUtils;
import com.sixin.filemvc.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends Application {

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        //控制日志的开关
        LogUtils.getConfig().setLogSwitch(true);
        mRefWatcher = setupLeakCanary();
    }

    private RefWatcher setupLeakCanary(){
        if(LeakCanary.isInAnalyzerProcess(this)){
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        App app = (App) context.getApplicationContext();
        return app.mRefWatcher;
    }
}
