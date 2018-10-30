package com.meishakeji.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by CXK on 2018/10/7.
 */

public class PluginManager {
    private static PluginManager ourInstance=new PluginManager();
    private Context context;

    private DexClassLoader pluginDexClassLoader;
    private Resources pluginResources;
    private PackageInfo pluginPackageArchiveInfo;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        return ourInstance;
    }

    public PackageInfo getPluginPackageArchiveInfo(){
        return pluginPackageArchiveInfo;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void loadApk(String dexPath) {
        //dexPath是Plugin的路径,optimizedDirectory是Plugin的缓存路径,libraryPath可以为null,parent为父类加载器
        pluginDexClassLoader = new DexClassLoader(dexPath, context.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, context.getClassLoader());

        pluginPackageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);

        //Resources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        AssetManager assets = null;
        try {
            assets = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assets, dexPath);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        pluginResources = new Resources(assets, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }


    public DexClassLoader getPluginDexClassLoader() {
        return pluginDexClassLoader;
    }

    public Resources getPluginResources() {
        return pluginResources;
    }
}
