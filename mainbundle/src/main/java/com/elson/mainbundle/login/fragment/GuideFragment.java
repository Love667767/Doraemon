package com.elson.mainbundle.login.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;

import com.elson.basecore.base.BaseFragment;
import com.elson.basecore.base.BasePresenter;
import com.elson.basecore.navigator.Navigator;
import com.elson.basecore.utils.AppUtils;
import com.elson.basecore.utils.SPUtil;
import com.elson.mainbundle.R;
import com.elson.mainbundle.R2;
import com.elson.mainbundle.login.LoginActivity;

import butterknife.BindView;

/**
 * Created by elson on 2017/4/28
 */

public class GuideFragment extends BaseFragment {

    private static final String INDEX = "index";

    private int mIndex; // 当前页的位置

    @BindView(R2.id.guide_iv)
    ImageView mGuideIv;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mIndex = bundle.getInt(INDEX);
    }

    public static GuideFragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);

        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void goLogin() {
        // 移除全屏
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Navigator.navigation(mContext, LoginActivity.class);
        getActivity().finish();
        SPUtil.putAndApply(AppUtils.getContext(), "isFirstLogin", false);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.main_fragment_guide;
    }

    @Override
    protected void initViews() {
        switch (mIndex){
            case 0:
                mGuideIv.setBackgroundColor(Color.parseColor("#ff0000"));
                break;
            case 1:
                mGuideIv.setBackgroundColor(Color.parseColor("#00ff00"));
                break;
            case 2:
                mGuideIv.setBackgroundColor(Color.parseColor("#0000ff"));
                break;
        }

        mGuideIv.setOnClickListener(v -> {
            if (mIndex == 2) {
                goLogin();
            }
        });
    }
}
