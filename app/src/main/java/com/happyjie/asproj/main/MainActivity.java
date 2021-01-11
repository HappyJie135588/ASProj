package com.happyjie.asproj.main;

import android.os.Bundle;

import com.happyjie.asproj.R;
import com.happyjie.asproj.main.logic.MainActivityLogic;
import com.happyjie.common.ui.component.HiBaseActivity;

public class MainActivity extends HiBaseActivity implements MainActivityLogic.ActivityProvider {
    private MainActivityLogic activityLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityLogic = new MainActivityLogic(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        activityLogic.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
