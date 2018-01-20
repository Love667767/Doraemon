package com.elson.mainbundle.login;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;
import com.elson.basecore.navigator.Navigator;
import com.elson.basecore.utils.AppUtils;
import com.elson.basecore.utils.ComLogicUtil;
import com.elson.basecore.utils.RxUtil;
import com.elson.basecore.utils.common.KeyboardUtil;
import com.elson.basecore.utils.common.RegexUtil;
import com.elson.basecore.utils.common.ToastUtil;
import com.elson.mainbundle.MainActivity;
import com.elson.mainbundle.R;
import com.elson.mainbundle.R2;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 登陆界面
 * Created by elson on 2018/1/20
 */

public class LoginActivity extends BaseActivity {

    @BindView(R2.id.edt_phone)
    EditText mEdtPhone;
    @BindView(R2.id.edt_identify_code)
    EditText mEdtIdentifyCode;
    @BindView(R2.id.tv_login)
    TextView mTvLogin;
    @BindView(R2.id.tv_wait_time)
    TextView mTvWaitTime;


    private Subscription mSubscribe;

    @Override
    public int getLayoutID() {
        return R.layout.main_activity_login;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        getWindow().setBackgroundDrawable(null);

        initTitleBar(null, "注册登录");
//        KeyBoardUtil.hide(mCustomToolbar);
        Navigator.navigation(this, MainActivity.class);

    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @OnClick({R2.id.tv_wait_time, R2.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.tv_wait_time://获取验证码
                getIdentifyCode();
                break;

            case R2.id.tv_login:
                KeyboardUtil.hideSoftInput(this);
                Navigator.navigationThenKill(this, MainActivity.class);
                break;
        }
    }

    private void getIdentifyCode() {
        if (TextUtils.isEmpty(getPhoneNumber())) {
            showToast(getString(R.string.main_login_phone_not_empty));
            return;
        }
        if (!RegexUtil.isMobileSimple(getPhoneNumber())) {
            showToast("请输入正确的手机号码");
            return;
        }
        ToastUtil.showShortToast(AppUtils.getContext(), getString(R.string.main_login_identifycode_sending));
        refreshWaitTime();
    }

    private void refreshWaitTime() {
        mSubscribe = RxUtil.countDownObservable(60)
            .doOnSubscribe(() -> {mTvWaitTime.setEnabled(false);})
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Long>() {
                @Override
                public void onCompleted() {
                    mTvWaitTime.setEnabled(true);
                    mTvWaitTime.setText(R.string.register_identify_code);
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(Long aLong) {
                    mTvWaitTime.setText(aLong + "s");
                }
            });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null) {
            mSubscribe.unsubscribe();
        }
    }

    @OnTextChanged(value = {R2.id.edt_phone, R2.id.edt_identify_code}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s) {
        checkInfoAndChangeBtnStyle();
    }

    /**
     * 根据信息的填写情况更新登录按钮的状态
     */
    private void checkInfoAndChangeBtnStyle() {
        if (TextUtils.isEmpty(getPhoneNumber()) || TextUtils.isEmpty(getIdentify())) {
            mTvLogin.setEnabled(false);
            return;
        }
        mTvLogin.setEnabled(true);
    }

    @NonNull
    private String getPhoneNumber() {
        return ComLogicUtil.getViewContent(mEdtPhone);
    }

    @NonNull
    private String getIdentify() {
        return ComLogicUtil.getViewContent(mEdtIdentifyCode);
    }

}
