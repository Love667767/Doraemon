package com.elson.mainbundle.login;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;
import com.elson.mainbundle.R;
import com.elson.mainbundle.R2;
import com.elson.mainbundle.login.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 引导页
 * Created by elson on 2017/4/28
 */

public class GuideActivity extends BaseActivity {

    private List<Fragment> mFragments = new ArrayList<>();

    @BindView(R2.id.viewpager)
    ViewPager mViewpager;

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

}
