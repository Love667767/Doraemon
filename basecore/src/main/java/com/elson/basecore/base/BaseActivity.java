package com.elson.basecore.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.elson.basecore.utils.common.ActivityUtil;
import com.elson.basecore.utils.common.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * 基类
 *
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView<BasePresenter> {

    protected final String TAG = this.getClass().getSimpleName();

    private View mView;
    private ProgressDialog mLoading;

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }

        StatusBarUtil.setTransparent(this);
        mPresenter = (P) initPresenter();
        initViews();

        ActivityUtil.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    protected abstract int getLayoutID();

    protected void getBundleExtras(Bundle extras){}

    protected abstract void initViews();


    /**
     * 是否接收EventBus发送的数据
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().finish(this);
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        if (mPresenter != null) {
            this.mPresenter.onDestroy();//释放资源
            this.mPresenter = null;
        }
    }

    @Override
    public void showLoading(String msg) {
        if (mLoading == null) {
//            mLoading = new LoadingProgressDialog(this, getLoadingText());
        }
        mLoading.show();
    }

    protected String getLoadingText() {
        return null;
    }

    @Override
    public void hideLoading() {
        if (mLoading != null) {
            mLoading.dismiss();
        }
    }

    @Override
    public boolean isActivityFinish() {
        return isFinishing();
    }

    @Override
    public void showToast(String msg) {
//        ToastUtils.showShortToast(getApplicationContext(), msg);
    }

}
