package com.sevtinge.cemiuiler.module.securitycenter.beauty;

import com.sevtinge.cemiuiler.module.base.BaseHook;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.io.File;

public class BeautyPrivacy extends BaseHook {
    @Override
    public void init() {
        int appVersionCode = getPackageVersionCode(lpparam);
        if (appVersionCode == 40000749 || appVersionCode == 40000750) {
            findAndHookMethod("p5.f", "X", new BaseHook.MethodHook() {
                @Override
                protected void before(XC_MethodHook.MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
        } else if (appVersionCode == 40000754 || appVersionCode == 40000771) {
            findAndHookMethod("com.miui.gamebooster.beauty.l", "Q", new BaseHook.MethodHook() {
                @Override
                protected void before(XC_MethodHook.MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
        } else {
            findAndHookMethod("com.miui.gamebooster.beauty.i", "M", new BaseHook.MethodHook() {
                @Override
                protected void before(XC_MethodHook.MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
        }
    }

    private static String getPackageVersionName(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Class<?> parserCls = XposedHelpers.findClass("android.content.pm.PackageParser", lpparam.classLoader);
            Object parser = parserCls.newInstance();
            File apkPath = new File(lpparam.appInfo.sourceDir);
            Object pkg = XposedHelpers.callMethod(parser, "parsePackage", apkPath, 0);
            String versionName = (String) XposedHelpers.getObjectField(pkg, "mVersionName");
            XposedBridge.log("Cemiuiler: " + lpparam + " versionName is " + versionName);
            return versionName;
        } catch (Throwable e) {
            XposedBridge.log("Cemiuiler: Unknown Version.");
            XposedBridge.log(e);
            return "null";
        }
    }

    private static int getPackageVersionCode(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Class<?> parserCls = XposedHelpers.findClass("android.content.pm.PackageParser", lpparam.classLoader);
            Object parser = parserCls.newInstance();
            File apkPath = new File(lpparam.appInfo.sourceDir);
            Object pkg = XposedHelpers.callMethod(parser, "parsePackage", apkPath, 0);
            int versionCode = XposedHelpers.getIntField(pkg, "mVersionCode");
            XposedBridge.log("Cemiuiler: " + lpparam + " versionCode is " + versionCode);
            return versionCode;
        } catch (Throwable e) {
            XposedBridge.log("Cemiuiler: Unknown Version.");
            XposedBridge.log(e);
            return -1;
        }
    }
}

