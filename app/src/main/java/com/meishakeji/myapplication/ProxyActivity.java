package com.meishakeji.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meishakeji.plugin.PluginInterface;

/**
 * Created by CXK on 2018/10/7.
 */

public class ProxyActivity extends Activity {

    private PluginInterface pluginInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拿到要启动的Activity
        String className = getIntent().getStringExtra("className");
        try {
            //加载该Activity的字节码对象
            Class<?> aClass = PluginManager.getInstance().getPluginDexClassLoader().loadClass(className);
            //创建该Activity的示例
            Object newInstance = aClass.newInstance();
            //程序健壮性检查
            if (newInstance instanceof PluginInterface) {
                pluginInterface = (PluginInterface) newInstance;
                //将代理Activity的实例传递给三方Activity
                pluginInterface.attachContext(this);
                //创建bundle用来与三方apk传输数据
                Bundle bundle = new Bundle();
                //调用三方Activity的onCreate，
                pluginInterface.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        pluginInterface.onStart();
        super.onStart();
    }

    @Override
    public void onResume() {
        pluginInterface.onResume();
        super.onResume();
    }

    @Override
    public void onRestart() {
        pluginInterface.onRestart();
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        pluginInterface.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        pluginInterface.onStop();
        super.onStop();
    }

    @Override
    public void onPause() {
        pluginInterface.onPause();
        super.onPause();
    }


    /**
     * 注意：三方调用拿到对应加载的三方Resources
     * @return
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent newIntent = new Intent(this, ProxyActivity.class);
        newIntent.putExtra("className", intent.getComponent().getClassName());
        super.startActivity(newIntent);
    }
}
