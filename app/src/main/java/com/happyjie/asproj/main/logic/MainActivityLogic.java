package com.happyjie.asproj.main.logic;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.happyjie.asproj.R;
import com.happyjie.asproj.main.fragment.CategoryFragment;
import com.happyjie.asproj.main.fragment.FavoriteFragment;
import com.happyjie.asproj.main.fragment.HomePageFragment;
import com.happyjie.asproj.main.fragment.RecommendFragment;
import com.happyjie.common.tab.HiFragmentTabView;
import com.happyjie.common.tab.HiTabViewAdapter;
import com.happyjie.hiui.tab.bottom.HiTabBottomInfo;
import com.happyjie.hiui.tab.bottom.HiTabBottomLayout;
import com.happyjie.hiui.tab.common.IHiTabLayout;

import org.devio.as.proj.main.fragment.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HJ on 2021/1/10.
 *
 * @link
 * @description: 将MainActivity的一些逻辑内聚在这，让MainActivity更加清爽
 */
public class MainActivityLogic {
    private HiFragmentTabView fragmentTabView;
    private HiTabBottomLayout hiTabBottomLayout;
    private List<HiTabBottomInfo<?>> infoList;
    private ActivityProvider activityProvider;
    private final static String SAVED_CURRENT_ID = "SAVED_CURRENT_ID";
    private int currentItemIndex;

    public MainActivityLogic(ActivityProvider activityProvider, Bundle savedInstanceState) {
        this.activityProvider = activityProvider;
        if (savedInstanceState != null) {
            currentItemIndex = savedInstanceState.getInt(SAVED_CURRENT_ID);
        }
        initTabBottom();
    }

    public HiFragmentTabView getFragmentTabView() {
        return fragmentTabView;
    }

    public HiTabBottomLayout getHiTabBottomLayout() {
        return hiTabBottomLayout;
    }

    public List<HiTabBottomInfo<?>> getInfoList() {
        return infoList;
    }

    private void initTabBottom() {
        hiTabBottomLayout = activityProvider.findViewById(R.id.tab_bottom_layout);
        hiTabBottomLayout.setTabAlpha(0.85f);
        infoList = new ArrayList<>();
        int defaultColor = activityProvider.getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor = activityProvider.getResources().getColor(R.color.tabBottomTintColor);
        HiTabBottomInfo homeInfo = new HiTabBottomInfo<Integer>(
                "首页",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_home),
                null,
                defaultColor,
                tintColor
        );
        homeInfo.fragment = HomePageFragment.class;
        HiTabBottomInfo infoFavorite = new HiTabBottomInfo<Integer>(
                "收藏",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_favorite),
                null,
                defaultColor,
                tintColor
        );
        infoFavorite.fragment = FavoriteFragment.class;
        HiTabBottomInfo infoCategory = new HiTabBottomInfo<Integer>(
                "分类",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_category),
                null,
                defaultColor,
                tintColor
        );
        infoCategory.fragment = CategoryFragment.class;
        HiTabBottomInfo infoRecommend = new HiTabBottomInfo<Integer>(
                "推荐",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_recommend),
                null,
                defaultColor,
                tintColor
        );
        infoRecommend.fragment = RecommendFragment.class;
        HiTabBottomInfo infoProfile = new HiTabBottomInfo<Integer>(
                "我的",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_profile),
                null,
                defaultColor,
                tintColor
        );
        infoProfile.fragment = ProfileFragment.class;
        infoList.add(homeInfo);
        infoList.add(infoFavorite);
        infoList.add(infoCategory);
        infoList.add(infoRecommend);
        infoList.add(infoProfile);

        hiTabBottomLayout.inflateInfo(infoList);
        initFragmentTabView();
        hiTabBottomLayout.addTabSelectedChangeListener(new IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HiTabBottomInfo<?> prevInfo, @NonNull HiTabBottomInfo<?> nextInfo) {
                fragmentTabView.setCurrentItem(index);
                MainActivityLogic.this.currentItemIndex = index;
            }
        });
        hiTabBottomLayout.defaultSelected(infoList.get(currentItemIndex));
    }

    private void initFragmentTabView() {
        HiTabViewAdapter tabViewAdapter = new HiTabViewAdapter(activityProvider.getSupportFragmentManager(), infoList);
        fragmentTabView = activityProvider.findViewById(R.id.fragment_tab_view);
        fragmentTabView.setAdapter(tabViewAdapter);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID, currentItemIndex);
    }

    public interface ActivityProvider {
        <T extends View> T findViewById(@IdRes int id);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);
    }
}
