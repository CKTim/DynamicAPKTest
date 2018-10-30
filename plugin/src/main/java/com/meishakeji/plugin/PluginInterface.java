package com.meishakeji.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by CXK on 2018/10/7.
 */

public interface PluginInterface {

    void onCreate(Bundle saveInstance);

    void attachContext(Activity context);

    void onStart();

    void onResume();

    void onRestart();

    void onDestroy();

    void onStop();

    void onPause();
}
