package com.elson.moviecomponent;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.elson.basecore.event.EventCenter;
import com.elson.basecore.navigator.Navigator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = "/movie/activity")
public class MovieMainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private TextView mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity_main);
        EventBus.getDefault().register(this);
        mMovie = (TextView) findViewById(R.id.tv_movie);
        mMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getsContext().build("/movie/activity2").navigation();
                Navigator.navigation(MovieMainActivity.this, MovieMain2Activity.class);
            }
        });
        Log.d(TAG, "onCreated");
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
