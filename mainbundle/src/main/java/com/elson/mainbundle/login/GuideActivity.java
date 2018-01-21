package com.elson.mainbundle.login;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;
import com.elson.basecore.navigator.Navigator;
import com.elson.mainbundle.R;
import com.elson.mainbundle.R2;
import com.elson.mainbundle.login.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * 引导页
 * Created by elson on 2017/4/28
 */

public class GuideActivity extends BaseActivity {

    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.tv_enter)
    TextView mTvEnter;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.main_activity_guide;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        GuideFragment firstPage = GuideFragment.newInstance(0);
        GuideFragment secondPage = GuideFragment.newInstance(1);
        GuideFragment thirdPage = GuideFragment.newInstance(2);
        mFragments.add(firstPage);
        mFragments.add(secondPage);
        mFragments.add(thirdPage);

        GuidePagerAdapter adapter = new GuidePagerAdapter(getSupportFragmentManager(), mFragments);
        mViewpager.setAdapter(adapter);

    }

    @OnPageChange(R2.id.viewpager)
    public void onPageSelected(int position) {
        if (position == 2) {
            mTvEnter.setVisibility(View.VISIBLE);
        } else {
            mTvEnter.setVisibility(View.GONE);
        }
    }


    @OnClick(R2.id.tv_enter)
    public void onViewClicked() {
        // 移除全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Navigator.navigationThenKill(this, LoginActivity.class);
//        SPUtil.putAndApply(AppUtils.getContext(), SplashActivity.IS_FIRST_LOGIN, false);
    }
}
