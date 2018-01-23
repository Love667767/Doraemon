package com.elson.moviecomponent;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;
import com.elson.basecore.event.EventCenter;
import com.elson.basecore.navigator.Navigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/movie/activity")
public class MovieMainActivity extends BaseActivity {


    @BindView(R2.id.tv_movie)
    TextView mMovie;

    @Override
    protected int getLayoutID() {
        return R.layout.movie_activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveMsg(final EventCenter<String> event) {
        Toast.makeText(this, event.getData() + "=====" + (mMovie == null), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMovie.setText(event.getData());
                Log.d(TAG, "onReceivedMsg=====" + event.getData());
            }
        }, 500);
        Log.d(TAG, "onReceiveMsg");

    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @OnClick(R2.id.tv_movie)
    public void onViewClicked() {
        Navigator.navigation(this, MovieMain2Activity.class);
    }
}
