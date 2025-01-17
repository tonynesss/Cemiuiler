package com.sevtinge.cemiuiler.module.market;

import android.os.Build;

import com.sevtinge.cemiuiler.module.base.BaseHook;

import de.robv.android.xposed.XposedHelpers;

public class DeviceModify extends BaseHook {

    @Override
    public void init() {
        findAndHookConstructor("com.xiaomi.market.MarketApp", new MethodHook() {
            @Override
            protected void before(MethodHookParam param) throws Throwable {
                XposedHelpers.setStaticObjectField(Build.class, "DEVICE", "ishtar");
                XposedHelpers.setStaticObjectField(Build.class, "MODEL", "2304FPN6DC");
                XposedHelpers.setStaticObjectField(Build.class, "MANUFACTURER", "Xiaomi");
            }
        });
    }
}
