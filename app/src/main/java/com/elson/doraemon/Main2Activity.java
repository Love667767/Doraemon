package com.elson.doraemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.elson.basecore.event.EventCenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/host/activity2")
public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mTextView.setText("我是Host activity2");
    }


    @OnClick(R.id.textView)
    public void onViewClicked() {
        mTextView.setText("我被点击了");
//        EventBus.getDefault().postSticky(new EventCenter<String>(0, "我来自App的消息"));
//        ARouter.getsContext().build("/movie/activity").navigation();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(final EventCenter<String> event) {
        if (event.getEventCode() == 1) {
            mTextView.setText(event.getData());
        }
//        Toast.makeText(this, event.getData(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
