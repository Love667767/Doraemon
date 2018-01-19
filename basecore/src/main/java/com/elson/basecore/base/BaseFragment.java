package com.elson.basecore.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView<BasePresenter> {

    protected final String TAG = this.getClass().getSimpleName();

    protected View mView;
    private ProgressDialog mLoading;
    private Unbinder mUnbinder;

    protected P mPresenter;

    protected Context mContext = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutID(), null);
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = (P) initPresenter();
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        initViews();
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    protected abstract int getLayoutID();

    protected abstract void initViews();

    @Override
    public void onDetach() {
        super.onDetach();
        // 解决ViewPager嵌套引起的crash，参考：
        // http://stackoverflow.com/questions/18977923/viewpager-with-nested-fragments
        // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        if (mPresenter != null) {
            this.mPresenter.onDestroy();//释放资源
            this.mPresenter = null;
        }

        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public boolean isActivityFinish() {
        return getActivity()==null || getActivity().isFinishing();
    }

    @Override
    public void showLoading(String msg) {
        if (mLoading == null) {
//            mLoading = new LoadingProgressDialog(mContext, R.style.loading_dialog);
        }
        mLoading.show();
    }

    @Override
    public void hideLoading() {
        if (mLoading != null) {
            mLoading.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
//        ToastUtils.showShortToast(AppUtils.getContext(), msg);
    }
}
