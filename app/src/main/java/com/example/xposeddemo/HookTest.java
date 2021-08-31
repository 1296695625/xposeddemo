package com.example.xposeddemo;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Random;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTest implements IXposedHookLoadPackage {
    public HookTest() {
    }

    public static String NAME = "com.example.xposeddemo";
    MainActivity mainActivity;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.v("tag", "handlemsg" + NAME);
        if (null == lpparam) {
            Log.v("hook", "lpparam ==" + lpparam);
            return;
        }
        Random random=new Random();
        XposedBridge.log("handle -" + NAME);
        if (!lpparam.packageName.equals(NAME)) {
            return;
        }
        if (lpparam.packageName.equals(NAME)) {
            XposedBridge.log("劫持app");
            XposedHelpers.findAndHookMethod("com.example.xposeddemo.MainActivity", lpparam.classLoader, "changeMsg", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
                    param.setResult(""+ random.nextFloat());
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.v("tag","handle  "+ param.getResult()+param.thisObject +param.method);
                    param.setResult("11");
                }
            });

            Field f=XposedHelpers.findFieldIfExists(com.example.xposeddemo.MainActivity.class,"s");
            Log.v("tag",""+f.getName());
        }
    }
}
