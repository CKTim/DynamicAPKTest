package com.meishakeji.thirdapk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.meishakeji.plugin.PluginInterface;

/**
 * Created by CXK on 2018/10/7.
 * 构建Plugin的BaseActivity的原因是统一上下文为ProxyActivity的实例，关于上下文的各种操作均是调用ProxyActivity的实例去进行操作。
 */

public class BaseActivity extends Activity implements PluginInterface {
    protected Activity thisContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void setContentView(int layoutResID) {
        thisContext.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        thisContext.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        thisContext.setContentView(view, params);
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return thisContext.getLayoutInflater();
    }

    @Override
    public Window getWindow() {
        return thisContext.getWindow();
    }

    @Override
    public View findViewById(int id) {
        return thisContext.findViewById(id);
    }

    @Override
    public void attachContext(Activity context) {
        thisContext = context;
    }

    @Override
    public ClassLoader getClassLoader() {
        return thisContext.getClassLoader();
    }

    @Override
    public WindowManager getWindowManager() {
        return thisContext.getWindowManager();
    }


    @Override
    public ApplicationInfo getApplicationInfo() {
        return thisContext.getApplicationInfo();
    }

    @Override
    public void finish() {
        thisContext.finish();
    }


    public void onStart() {

    }

    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void onBackPressed() {
        thisContext.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
        thisContext.startActivity(intent);
    }
}
