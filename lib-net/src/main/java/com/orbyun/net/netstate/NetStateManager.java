package com.orbyun.net.netstate;

import android.app.Application;
import android.content.IntentFilter;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2019-10-1017:02
 * desc   :
 */
public class NetStateManager {

    private static NetStateManager manager;
    private final NetworkStateReceiver receiver;
    private Application application;

    private NetStateManager() {
        receiver = new NetworkStateReceiver();
    }

    public static NetStateManager getInstance() {
        if (manager == null) {
            synchronized (NetStateManager.class) {
                if (manager == null) {
                    manager = new NetStateManager();
                }
            }
        }

        return manager;
    }
    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("please call init method in your app");
        }
        return application;
    }
    public void init(Application app) {
        this.application = app;
        //广播注册
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ANDROID_NET_ACTION);
        application.registerReceiver(receiver, filter);

    }

    public void registerObserver(Object register) {
        receiver.registerObserver(register);
    }

    public void unRegisterObserver(Object register) {
        receiver.unRegisterObserver(register);
    }

    public void unRegisterAllObserver() {
        receiver.unRegisterAllObserver();
    }

}
