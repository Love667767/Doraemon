package com.elson.moviecomponent;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;
import com.elson.basecore.event.EventCenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/movie/activity2")
public class MovieMain2Activity extends BaseActivity {


    @BindView(R2.id.tv_movie)
    TextView mMovie;


    @Override
    protected int getLayoutID() {
        return R.layout.movie_activity_main;
    }

    @Override
    protected void initViews() {
        mMovie.setText("我是MovieMain2Activity");
        mMovie.setOnClickListener(v -> {
            ARouter.getInstance().build("/host/activity2").navigation();
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new EventCenter<String>(1, "我来自MovieMain2Activity"));
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick(R2.id.tv_movie)
    public void onViewClicked() {
    }
}
